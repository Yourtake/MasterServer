/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yourtake.controller.web;

import com.yourtake.model.pojo.admin.Admin;
import com.yourtake.model.pojo.setup.Branch;
import com.yourtake.model.pojo.setup.Organization;
import com.yourtake.service.AdminMaintainService;
import com.yourtake.service.BranchService;
import com.yourtake.service.OrganizationService;
import com.yourtake.service.ViewService;
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
public class BranchController {
    
    
       @Autowired
    AdminMaintainService adminMaintain;
   @Autowired
    OrganizationService orgService;
      @Autowired
    ViewService viewService;
  @Autowired
    BranchService bService;
   
    
    @RequestMapping(value = "/admin/branch/org/search", method=RequestMethod.POST)
    public  ModelAndView branchSearch(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("branch");
                model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
                Organization org=orgService.search(request.getParameter("url"));
                if(org!=null){
                    model.addObject("branches", orgService.fetchWithInnerList(org).getBranches());
                }
               model.addObject("orgId",org.getUrl());
                
            return model;
    }
    
     @RequestMapping(value = "/admin/branch", method=RequestMethod.GET)
    public  ModelAndView branchDet(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("branch");
                model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
                 model.addObject("orgId", request.getParameter("org"));
               model.addObject("branch", bService.getBranch(request.getParameter("branch")));
                
            return model;
    }
    
     @RequestMapping(value = "/admin/branchBuilder")
    public  ModelAndView branch(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("branch");
                model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            return model;
    }
     @RequestMapping(value = "/admin/branch/build", method=RequestMethod.POST)
    public  ModelAndView orgBuild(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("branch");
                model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
                Branch b = new Branch();
                b.setName(request.getParameter("name"));
                b.setStatus(request.getParameter("status"));
                Organization org = orgService.search(request.getParameter("orgId"));
                if(org!=null){
                    org=orgService.fetchWithInnerList(org);
                     b.setMasterRelation(org);
                    b.setView(viewService.search(request.getParameter("view_url")).getId());
                    b = bService.build(b);
                    org.getBranches().add(b);
                    orgService.update(org);
                   model.addObject("msg","true");
                }
                else{
                    model.addObject("msg","false");
                }
            return model;
    }
}
