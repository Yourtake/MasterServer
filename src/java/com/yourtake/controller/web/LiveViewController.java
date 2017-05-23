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
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author MumbaiZone
 */
@Controller
public class LiveViewController {   
 

    @RequestMapping(value = "/space/{value}")
    public  ModelAndView org(HttpServletRequest request,@PathVariable("value") String value) {
            ModelAndView model= new ModelAndView("custom");
            return model;
    }
}
