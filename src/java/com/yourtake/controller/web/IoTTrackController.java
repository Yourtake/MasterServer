/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yourtake.controller.web;

import com.yourtake.model.pojo.admin.Admin;
import com.yourtake.model.pojo.setup.Branch;
import com.yourtake.model.pojo.setup.Contact;
import com.yourtake.model.pojo.setup.Feedback;
import com.yourtake.model.pojo.setup.Organization;
import com.yourtake.model.pojo.setup.Rule;
import com.yourtake.model.pojo.views.FormInput;
import com.yourtake.model.pojo.views.Page;
import com.yourtake.model.pojo.views.View;
import com.yourtake.service.AdminMaintainService;
import com.yourtake.service.BranchService;
import com.yourtake.service.FeedbackService;
import com.yourtake.service.OrganizationService;
import com.yourtake.service.PageService;
import com.yourtake.service.ViewService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
       BranchService bService;
       @Autowired
       OrganizationService orgService;
       
       @Autowired
       ViewService viewService;
       
       @Autowired
       PageService pService;
       
       @Autowired
       AdminMaintainService adminMaintain;
  

    @RequestMapping(value = "/admin/iotTrackPanel")
    public  ModelAndView org(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("iot");
            Admin admin = adminMaintain.checkPresence(new Admin(principal.getName()));
            Organization org =orgService.search(admin.getOrganization());
            org=orgService.fetchWithInnerList(org);
            model.addObject("branches",org.getBranches());
            model.addObject("admin",admin);
            return model;
    }
    
    
       @RequestMapping(value = "/feedback/present")
    public  ResponseEntity getLive(HttpServletRequest request,HttpServletResponse response) {
            Branch branch = bService.getBranch(request.getParameter("branchId"));
            branch.setStatus(new Date().toString());
            bService.update(branch);
              return new ResponseEntity(HttpStatus.OK);
    }
    
      @RequestMapping(value="/feedback/response",method=RequestMethod.POST)
        public ModelAndView response(HttpServletRequest request ,HttpServletResponse response) {
           
            ModelAndView model=null;
          System.out.println("Got a request");
            List<Feedback> replyList = new ArrayList<>();
            Branch branch = bService.getBranch(request.getParameter("branchId"));
                   View view= viewService.fetchWithInnerList(viewService.search(branch.getViewPage()));
                   Float rating=5.0f;
                   for(Page p:view.getPages()){
                       if(p.getName().equals(request.getParameter("pageName"))){
                                p = pService.fetchEagerly(p);
                                Float value=0f;
                                for(FormInput form:p.getForms()){
                                    String val=form.getText().trim().toLowerCase().replaceAll("[^A-Za-z0-9]", "_");
                                    replyList.add(new Feedback(form.getType(),val,request.getParameter(val),branch.getMasterRelation().getId()));
                                    if(form.getType().contains("Rating")){
                                        
                                        value+= Float.parseFloat(request.getParameter(val));
                                    }
                                }
                                rating=value/rating;
                                
                                break;
                       }
                   }
                for(Rule rule:branch.getMasterRelation().getRules()){
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
