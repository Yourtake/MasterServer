
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yourtake.controller.web;

import com.yourtake.model.pojo.admin.Admin;
import com.yourtake.service.AdminMaintainService;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author MumbaiZone
 */
@Controller
public class AdminBuildController {
    
    
        @Autowired
    AdminMaintainService adminMaintain;
  
    @RequestMapping(value = "/admin/assign_admin", method = RequestMethod.POST)
    public ModelAndView assignAdmin(Principal principal,HttpServletRequest request) {
            if(adminMaintain.buildEmployee(request.getParameter("username"),request.getParameter("name"),request.getParameter("work"),principal.getName(),request.getParameter("number"),Integer.parseInt(request.getParameter("power")),request.getParameter("organization"))){
                ModelAndView model=new ModelAndView("redirect:/admin/hr?msg=true"); 
                 model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
                 return model;
            }
            ModelAndView model=new ModelAndView("redirect:/admin/hr?msg=false"); 
             model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            return model;
    }
    
    @RequestMapping(value = "/admin/hr/delete/{username}", method = RequestMethod.GET)
    public ModelAndView deleteAdmin(@PathVariable("username") String username,Principal principal,HttpServletRequest request) {
    Admin destructor =adminMaintain.checkPresence(new Admin(principal.getName()));
            if(adminMaintain.checkPresence(new Admin(username.replace("$", "."))).getPower()>destructor.getPower()){
                destructor=null;
                if(adminMaintain.deleteEmployee(new Admin(username.replace("$", ".")))){
                    ModelAndView model=new ModelAndView("redirect:/admin/hr?msg=true"); 
                      model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
                     return model;
                }
                ModelAndView model= new ModelAndView("redirect:/admin/hr?msg=false"); 
                model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
                     return model;
            }
           else{
              ModelAndView model= new ModelAndView("redirect:/admin"); 
                model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
                     return model;
            }
    }
    
    @RequestMapping(value = "/admin/hr")
    public  ModelAndView adminHr(HttpServletRequest request,Principal principal) {
            Admin admin =adminMaintain.checkPresence(new Admin(principal.getName()));
            ModelAndView model= new ModelAndView("adminhr");
             if(request.getParameter("msg")!=null){
                if(request.getParameter("msg").equals("true")){
                     model.addObject("message","Processed."); 
                } 
                else if(request.getParameter("msg").equals("false")){
                    model.addObject("message","Not processed. Please try again!"); 
                } 
             }
            model.addObject("admin_list",adminMaintain.getHrList(admin.getPower()));
            model.addObject("admin",admin);
            admin=null; 
            return model;
    }
}
