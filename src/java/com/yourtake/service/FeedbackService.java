/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yourtake.service;

import com.yourtake.backend.service.core.email.SimpleEmailService;
import com.yourtake.backend.service.core.sms.SMSSending;

/**
 *
 * @author MumbaiZone
 */
public class FeedbackService {
     public void sendReviewDetails(String emailId,String number,Float rating) {
            new SimpleEmailService("Thank you for you time",
                    "Hi, "
                    + "<br/>"
                    + "<br/>"
                    +" Hi , <br/><br/>Received a request with "+rating+" score<br/>"
                    + "<br/>"
                    + "<br/>"
                    + "Regards,<br/>Zapang Support",emailId).start();
           new SMSSending(number,"Hi, Received a request with "+rating+" score").start();
    }
  
 

    public Object getAverageOnDate(String type, String format) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
