/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yourtake.controller.web;

import com.yourtake.model.pojo.admin.Admin;
import com.yourtake.model.pojo.setup.Organization;
import com.yourtake.model.pojo.setup.Rule;
import com.yourtake.service.AdminMaintainService;
import com.yourtake.service.OrganizationService;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.yourtake.service.RuleService;
import com.yourtake.model.pojo.setup.Contact;

/**
 *
 * @author MumbaiZone
 */
@Controller
public class RulesController {
    
       @Autowired
    AdminMaintainService adminMaintain;
   @Autowired
    OrganizationService orgService;
       @Autowired
    RuleService ruleService;
    
       
           @RequestMapping(value = "/admin/rules", method=RequestMethod.GET)
    public  ModelAndView branchDet(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("rules");
                model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
               model.addObject("rule", ruleService.getRule(request.getParameter("rule")));
                
            return model;
    }
       
     @RequestMapping(value = "/admin/rules")
    public  ModelAndView rules(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("rules");
            model.addObject("orgId",request.getParameter("org"));
                model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            return model;
    }
    
    
        @RequestMapping(value = "/admin/rules/orgnization/search", method=RequestMethod.POST)
    public  ModelAndView search(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("rules");
                model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
                Organization org=orgService.search(request.getParameter("url"));
                model.addObject("orgId", org.getUrl());
               model.addObject("rules", orgService.fetchWithInnerList(org).getRules());
                
            return model;
    }
    
     @RequestMapping(value = "/admin/rules/build", method=RequestMethod.POST)
    public  ModelAndView Build(HttpServletRequest request,Principal principal) {
                ModelAndView model= new ModelAndView("rules");
                model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
                Rule r = new Rule();
                r.setLowerCap(Double.valueOf(request.getParameter("lower")));
                r.setUpperCap(Double.valueOf(request.getParameter("upper")));
                r.setFormInputName(request.getParameter("name"));
                Organization org = orgService.search(request.getParameter("orgId"));
                if(org!=null){
                    r.setOrganization(org);
                    r= ruleService.build(r, request.getParameter("emails"));
                    org= orgService.fetchWithInnerList(org);
                    org.getRules().add(r);
                    orgService.update(org);
                     model.addObject("msg","true");
                }
                else{
                    model.addObject("msg","false");
                }
                return model;
    }
}
