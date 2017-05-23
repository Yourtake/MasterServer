/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yourtake.controller.web;

import com.yourtake.model.pojo.admin.Admin;
import com.yourtake.model.pojo.views.FormInput;
import com.yourtake.model.pojo.views.Page;
import com.yourtake.model.pojo.views.Tab;
import com.yourtake.service.AdminMaintainService;
import com.yourtake.service.PageService;
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
public class PageDetailsController {
        @Autowired
    AdminMaintainService adminMaintain;
     @Autowired
        PageService pageService;
    
     @RequestMapping(value = "/admin/pageDetailsBuilder")
    public  ModelAndView pageDetails(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("pageDetails");
                model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            return model;
    }
    @RequestMapping(value = "/admin/pages/det/search",method=RequestMethod.POST)
    public  ModelAndView pageSearch(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("pageDetails");
            Page page=pageService.fetchEagerly(pageService.searchPage(request.getParameter("name")));
            model.addObject("formInputs",page.getForms());
            model.addObject("tabs", page.getTabs());
            model.addObject("pageId", page.getName());
                model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            return model;
    }
    
     @RequestMapping(value = "/admin/fi")
    public  ModelAndView fi(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("pageDetails");
            model.addObject("fi", pageService.getForm(request.getParameter("fi")));
            model.addObject("pageId", request.getParameter("page"));
                model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            return model;
    }
    
     @RequestMapping(value = "/admin/tab")
    public  ModelAndView tab(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("pageDetails");
            model.addObject("tab", pageService.getTab(request.getParameter("tab")));
            model.addObject("pageId", request.getParameter("page"));
                model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            return model;
    }
    
      @RequestMapping(value = "/admin/fi/build",method=RequestMethod.POST)
    public  ModelAndView fiBuild(HttpServletRequest request,Principal principal){
            ModelAndView model= new ModelAndView("pageDetails");
            FormInput fi = new FormInput();
            fi.setMandatory(Boolean.valueOf(request.getParameter("freq")));
            fi.setText(request.getParameter("ftext"));
            fi.setType(request.getParameter("ftype"));
            Page page =pageService.searchPage(request.getParameter("pageId"));
            fi.setPage(page);
            pageService.buildForm(fi);
            page=pageService.fetchEagerly(page);
            page.getForms().add(fi);
            pageService.update(page);
            model.addObject("msg","true");
                model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            return model;
    }
    
     @RequestMapping(value = "/admin/tab/build",method=RequestMethod.POST)
    public  ModelAndView tabBuild(HttpServletRequest request,Principal principal){
            ModelAndView model= new ModelAndView("pageDetails");
            Tab tab = new Tab();
            tab.setImageUrl(request.getParameter("timage"));
            tab.setSubText(request.getParameter("tstext"));
            tab.setText(request.getParameter("ttext"));
            tab.setUrl(request.getParameter("turl"));
            Page page =pageService.searchPage(request.getParameter("pageId"));
            tab.setPage(page);
            pageService.buildTab(tab);
            page=pageService.fetchEagerly(page);
            page.getTabs().add(tab);
            pageService.update(page);
            model.addObject("msg","true");
                model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            return model;
    }
}

