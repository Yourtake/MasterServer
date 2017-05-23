/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yourtake.controller.web;

import com.yourtake.model.pojo.admin.Admin;
import com.yourtake.service.AdminMaintainService;
import com.yourtake.service.FeedbackService;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author MumbaiZone
 */
@Controller
public class FeedbackViewController {
       @Autowired
    AdminMaintainService adminMaintain;
    @Autowired
    FeedbackService fs;

     @RequestMapping(value = "/admin/feedbackPanel/{value}")
    public  ModelAndView adminHr(HttpServletRequest request,@PathVariable("value") String type,Principal principal) {
            ModelAndView model= new ModelAndView("adminpast7");
               Calendar cal = Calendar.getInstance();
              model.addObject("t0",fs.getAverageOnDate(type, new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime())) );
              cal.add(Calendar.DATE, -1); 
                 model.addObject("t1",fs.getAverageOnDate(type,  new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime())));
                 cal.add(Calendar.DATE, -1); 
                 model.addObject("t2",fs.getAverageOnDate(type,  new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime())));
                 cal.add(Calendar.DATE, -1); 
                 model.addObject("t3",fs.getAverageOnDate(type,  new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime())));
                 cal.add(Calendar.DATE, -1); 
                 model.addObject("t4",fs.getAverageOnDate(type,  new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime())));
                 cal.add(Calendar.DATE, -1); 
                 model.addObject("t5",fs.getAverageOnDate(type,  new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime())));
                 cal.add(Calendar.DATE, -1); 
                 model.addObject("t6",fs.getAverageOnDate(type,  new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime())));
                 model.addObject("subject",type);
                model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            return model;
    }
    
}
