/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yourtake.controller.web;

import com.yourtake.model.pojo.admin.Admin;
import com.yourtake.model.pojo.setup.Contact;
import com.yourtake.model.pojo.setup.Feedback;
import com.yourtake.model.pojo.setup.Organization;
import com.yourtake.model.pojo.setup.Rule;
import com.yourtake.service.AdminMaintainService;
import com.yourtake.service.FeedbackService;
import com.yourtake.service.OrganizationService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author MumbaiZone
 */
@Controller
public class IoTTrackController {
       @Autowired
    FeedbackService fs;
       
       @Autowired
       OrganizationService orgService;
       
       @Autowired
       AdminMaintainService adminMaintain;
  

    @RequestMapping(value = "/admin/iotTrackPanel")
    public  ModelAndView org(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("iot");
                model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            return model;
    }
    
    
    
    
      @RequestMapping(value="/feedback/response",method=RequestMethod.POST)
        public ModelAndView response(HttpServletRequest request ,HttpServletResponse response) {
           
            ModelAndView model=null;



          System.out.println("Got a request");
          
            List<Feedback> replyList = new ArrayList<>();
            
           Organization org =orgService.search(request.getParameter("org"));
           org=orgService.fetchWithInnerList(org);
//            replyList.add(new Feedback("options","visit",request.getParameter("visit")));
//            replyList.add(new Feedback("rating","service",request.getParameter("service")));
//            replyList.add(new Feedback("rating","menu",request.getParameter("menu")));
//            replyList.add(new Feedback("rating","ambiance",request.getParameter("ambiance")));
//            replyList.add(new Feedback("rating","food",request.getParameter("food")));
//            replyList.add(new Feedback("rating","beverage",request.getParameter("beverage")));
//            replyList.add(new Feedback("rating","overall",request.getParameter("overall")));

            replyList.add(new Feedback("Rating","refer",request.getParameter("refer"),org.getId()));
            replyList.add(new Feedback("Text","better",request.getParameter("better"),org.getId()));
         
//                Float service= Float.parseFloat(request.getParameter("service"));
//                Float menu= Float.parseFloat(request.getParameter("menu"));
//                Float ambiance= Float.parseFloat(request.getParameter("ambiance"));
//                Float food= Float.parseFloat(request.getParameter("food"));
//                Float beverage= Float.parseFloat(request.getParameter("beverage"));
//                Float overall= Float.parseFloat(request.getParameter("overall"));
                Float rating= Float.parseFloat(request.getParameter("refer"));
          
                
                for(Rule rule:org.getRules()){
              
                        if(rating<rule.getLowerCap())
                                for(Contact contact:rule.getContacts()){
                                   fs.sendReviewDetails(contact.getUserId(),contact.getNumber(),rating);
                                }
                         else    if(rating>rule.getLowerCap())
                                for(Contact contact:rule.getContacts()){
                                   fs.sendReviewDetails(contact.getUserId(),contact.getNumber(),rating);
                                }
                   }
              
                        model= new ModelAndView("thankyou");
                        model.addObject("message", "Thank You");
       
            return model;
        }
    
}
