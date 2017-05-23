/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yourtake.controller.web;

import com.yourtake.model.pojo.admin.Admin;
import com.yourtake.model.pojo.views.Header;
import com.yourtake.model.pojo.views.Page;
import com.yourtake.model.pojo.views.View;
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
public class PageController {
        @Autowired
    AdminMaintainService adminMaintain;
        
        @Autowired
        PageService pageService;
        @Autowired
        ViewService viewService;
    
    @RequestMapping(value = "/admin/pageBuilder")
    public  ModelAndView page(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("page");
                model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            return model;
    }
    
       @RequestMapping(value = "/admin/page/views/search",method=RequestMethod.POST)
    public  ModelAndView pageViewSearch(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("page");
            View view=viewService.search(request.getParameter("url"));
            if(view!=null){
                view = viewService.fetchWithInnerList(view);
                model.addObject("viewId", view.getUrl());
                model.addObject("pages",view.getPages());
            }
                model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            return model;
    }
    
     @RequestMapping(value = "/admin/page")
    public  ModelAndView getPage(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("page");
            model.addObject("page",pageService.read(request.getParameter("page")));
            model.addObject("viewId", request.getParameter("view"));
                model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            return model;
    }
    @RequestMapping(value = "/admin/page/build",method=RequestMethod.POST)
    public  ModelAndView getPageB(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("page");
            Page page=pageService.searchPage(request.getParameter("name"));
            if(page==null){
                page = new Page();
            }
            page.setName(request.getParameter("name"));
            page.setType(request.getParameter("type"));
            page.setRelativeUrl(request.getParameter("rurl"));
            page.setSubmitUrl(request.getParameter("surl"));
            page.setBackgroundImageUrl(request.getParameter("burl"));
            View view = viewService.search(request.getParameter("viewId"));
            page.setView(view);
            page=pageService.build(page);
            Header header = new Header();
            header.setImage(request.getParameter("himage"));
            header.setUrl(request.getParameter("hurl"));
            header.setText(request.getParameter("htext"));
            header.setSubText(request.getParameter("hstext"));
            header=pageService.buildHeader(header);
           view=viewService.fetchWithInnerList(view);
           view.getPages().add(page);
           viewService.update(view);
           pageService.update(page,header);
          model.addObject("msg","true");
            
                model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            return model;
    }
}
