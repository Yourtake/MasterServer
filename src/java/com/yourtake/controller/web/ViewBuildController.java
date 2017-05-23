
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yourtake.controller.web;

import com.yourtake.model.pojo.admin.Admin;
import com.yourtake.service.AdminMaintainService;
import com.yourtake.service.ViewService;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.yourtake.model.pojo.views.View;

/**
 *
 * @author MumbaiZone
 */
@Controller
public class ViewBuildController {
        @Autowired
    AdminMaintainService adminMaintain;
        @Autowired
        ViewService viewService;
        
  
    
     @RequestMapping(value = "/admin/view/search", method=RequestMethod.POST)
    public  ModelAndView search(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("view");
                model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
               model.addObject("view", viewService.search(request.getParameter("url")));
            return model;
    }
    
    @RequestMapping(value = "/admin/viewBuilder")
    public  ModelAndView view(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("view");
                model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            return model;
    }
    @RequestMapping(value = "/admin/view/build", method=RequestMethod.POST)
    public  ModelAndView build(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("view");
            View v=viewService.search(request.getParameter("url"));
            if(v==null){
                v = new View();
            }
                v.setName(request.getParameter("name"));
                v.setType(request.getParameter("type"));
                v.setUrl(request.getParameter("url"));
                viewService.build(v);
            
            model.addObject("msg", "true");
                model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            return model;
    }
}
