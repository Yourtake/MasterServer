/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yourtake.controller.web;


import com.yourtake.model.pojo.admin.Admin;
import com.yourtake.model.pojo.setup.Organization;
import com.yourtake.service.AdminMaintainService;
import com.yourtake.service.OrganizationService;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
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
public class OrganizationBuildController {
    
       @Autowired
    AdminMaintainService adminMaintain;
   @Autowired
    OrganizationService orgService;

   
    @RequestMapping(value = "/admin/organizationBuilder")
    public  ModelAndView org(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("org");
                model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            return model;
    }
    
    @RequestMapping(value = "/admin/organization/search", method=RequestMethod.POST)
    public  ModelAndView orgSearch(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("org");
                model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
               model.addObject("organization", orgService.search(request.getParameter("url")));
                
            return model;
    }
    
      @RequestMapping(value = "/admin/organization/build", method=RequestMethod.POST)
    public  ModelAndView orgBuild(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("org");
                model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
               Organization org= orgService.search(request.getParameter("url"));
               if(org==null){
                    org = new Organization();
               }
                org.setName(request.getParameter("name"));
                org.setType(request.getParameter("type"));
                org.setUrl(request.getParameter("url"));
               orgService.build(org);
               adminMaintain.buildEmployee(request.getParameter("email"), "Admin", "Admin", principal.getName(),"9999999999",0,org.getName());
               model.addObject("msg","true");
               
            return model;
    }
   
}
