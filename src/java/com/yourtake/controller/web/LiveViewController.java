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
import com.yourtake.model.pojo.setup.Rule;
import com.yourtake.model.pojo.views.FormInput;
import com.yourtake.model.pojo.views.Page;
import com.yourtake.model.pojo.views.View;
import com.yourtake.service.AdminMaintainService;
import com.yourtake.service.BranchService;
import com.yourtake.service.FeedbackService;
import com.yourtake.service.PageService;
import com.yourtake.service.ViewService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
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
public class LiveViewController {   
 
    @Autowired
    PageService pageService;
    @Autowired
    BranchService bService;
    @Autowired
    ViewService vService;
           @Autowired
    FeedbackService fs;
       
    @RequestMapping(value = "/show/{value}/{branch}")
    public  ModelAndView show(HttpServletRequest request,@PathVariable("value") String value,@PathVariable("branch") String branch  ){
            ModelAndView model= new ModelAndView("custom");
           
                Branch b = bService.getBranch(value);
                if(b!=null){
                    View view=vService.search(b.getViewPage());
                    view=vService.fetchWithInnerList(view);
                    for(Page p:view.getPages()){
                        if(p.getRelativeUrl().trim().equals("")){
                            p=pageService.fetchEagerly(p);
                            for(FormInput f:p.getForms()){
                                f.setText(f.getText().trim().toLowerCase().replaceAll("[^A-Za-z0-9]", "_"));
                            }
                            model.addObject("page",p);
                            model.addObject("branchId",branch);
                            break;
                        }
                    }
            }
            return model;
    }
    
 
    
    
    @RequestMapping(value = "/fill/{value}/{branch}")
    public  ModelAndView fill(HttpServletRequest request,@PathVariable("value") String value,@PathVariable("branch") String branchId ) {
                ModelAndView model=null;
          System.out.println("Got a request");
            List<Feedback> replyList = new ArrayList<>();
            Branch branch = bService.getBranch(branchId);
           
                   View view= vService.fetchWithInnerList(vService.search(branch.getViewPage()));
                   Float rating=5.0f;
                   for(Page p:view.getPages()){
                       if(p.getName().equals(value)){
                                p = pageService.fetchEagerly(p);
                                model.addObject("page", p);
                                Float valu=0f;
                                for(FormInput form:p.getForms()){
                                    String val=form.getText().trim().toLowerCase().replaceAll("[^A-Za-z0-9]", "_");
                                    replyList.add(new Feedback(form.getType(),val,request.getParameter(val),branch.getMasterRelation().getId()));
                                    if(form.getType().contains("Rating")){
                                        
                                        valu+= Float.parseFloat(request.getParameter(val));
                                    }
                                }
                                rating=valu/rating;
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
