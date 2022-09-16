package com.gantzgulch.tools.smtp;

import java.util.Collection;

public interface SMTPSender {

    
    void sendText(Collection<String> toAddresses, String fromAddress, String subject, String body);
    
    void sendRichText(Collection<String> toAddresses, String fromAddress, String replyToAddress, String subject, String body, String contentType);

}
