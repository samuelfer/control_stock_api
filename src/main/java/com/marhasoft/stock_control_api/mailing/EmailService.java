package com.marhasoft.stock_control_api.mailing;


import jakarta.mail.MessagingException;

public interface EmailService {
    public  void sendMail(final  AbstractEmailContext emailContext) throws MessagingException;
}
