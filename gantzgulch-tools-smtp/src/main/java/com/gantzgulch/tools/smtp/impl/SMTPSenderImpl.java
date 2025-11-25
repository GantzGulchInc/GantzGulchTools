package com.gantzgulch.tools.smtp.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;

import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.tools.smtp.SMTPSender;

public class SMTPSenderImpl implements SMTPSender {

    private static final GGLogger LOG = GGLogger.getLogger(SMTPSenderImpl.class);

    private final String smtpHost;

    private final Session session;

    public SMTPSenderImpl(final String smtpHost) {

        this.smtpHost = smtpHost;

        this.session = createSession();
    }

    @Override
    public void sendText(final Collection<String> toAddresses, final String fromAddress, final String subject, final String body) {

    }

    @Override
    public void sendRichText(final Collection<String> toAddresses,
                             final String fromAddress,
                             final String replyToAddress,
                             final String subject,
                             final String body,
                             final String contentType) {

        try {

            final MimeMessage msg = new MimeMessage(session);

            msg.addHeader("Content-type", contentType);
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(toInternetAddress(fromAddress));

            if (!StringUtils.isBlank(replyToAddress)) {
                msg.setReplyTo(toInternetAddresses(replyToAddress));
            }

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, toInternetAddresses(toAddresses));

            Transport.send(msg);

        } catch (final MessagingException e) {

            LOG.warn(e, "sendRichText: Exception: %s", e.getMessage());

            throw new RuntimeException(e);
        }

    }

    private Address toInternetAddress(final String address) {
        try {
            return new InternetAddress(address);
        } catch (final AddressException e) {
            LOG.warn(e, "toInternetAddress: %s / %s", address, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private Address[] toInternetAddresses(final String... addresses) {

        return Arrays
                .stream(addresses)
                .map(this::toInternetAddress)
                .toArray(Address[]::new);

    }

    private Address[] toInternetAddresses(final Collection<String> addresses) {

        return addresses
                .stream()
                .map(this::toInternetAddress)
                .toArray(Address[]::new);

    }

    private Session createSession() {

        final Properties props = new Properties();

        props.putAll(System.getProperties());

        props.put("mail.smtp.host", this.smtpHost);

        return Session.getInstance(props, null);
    }

}
