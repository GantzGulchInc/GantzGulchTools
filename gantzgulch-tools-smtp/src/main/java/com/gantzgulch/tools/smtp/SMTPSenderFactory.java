package com.gantzgulch.tools.smtp;

import com.gantzgulch.tools.smtp.impl.SMTPSenderImpl;

public class SMTPSenderFactory {

    public static SMTPSender create(final String smtpHost) {
        return new SMTPSenderImpl(smtpHost);
    }
    
}
