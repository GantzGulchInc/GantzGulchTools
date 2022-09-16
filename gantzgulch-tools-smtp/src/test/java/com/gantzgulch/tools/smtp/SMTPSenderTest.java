package com.gantzgulch.tools.smtp;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SMTPSenderTest {

    
    @Test
    public void sendSimple() {
        
        final SMTPSender send = SMTPSenderFactory.create("smtp-a.gantzgulch.com");
        
        final List<String> toAddresses = new ArrayList<>();
        toAddresses.add("gantzm@gantzgulch.com");
        
        final String fromAddress = "gantzm@gantzgulch.com";
        final String replyToAddress = null;
        
        final String subject = "Test 001";
        final String body = "This is a test";
        final String contentType = "text/HTML; charset=UTF-8";
        
        send.sendRichText(toAddresses, fromAddress, replyToAddress, subject, body, contentType);
        
    }
}
