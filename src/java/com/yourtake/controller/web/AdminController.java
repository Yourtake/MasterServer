package com.yourtake.controller.web;



import com.yourtake.model.pojo.admin.Admin;
import com.yourtake.service.AdminMaintainService;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AdminController {
    @Autowired
    AdminMaintainService adminMaintain;
  

    public AdminController() {
    }
    
    
    
    @RequestMapping(value="/logout")
    public ModelAndView redirectLogOut(HttpSession session,HttpServletRequest request,Principal principal) {
            SecurityContextHolder.getContext().setAuthentication(null);
            session.invalidate();
            return new ModelAndView("redirect:/adminlogin");
    }
      @RequestMapping(value = "/admin")
    public  ModelAndView admin(HttpServletRequest request,Principal principal) {
         ModelAndView model= new ModelAndView("adminpage");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
//         model.addObject("service",adminMaintain.getAverage("service"));
//            model.addObject("menu",adminMaintain.getAverage( "menu"));
//            model.addObject("ambiance",adminMaintain.getAverage( "ambiance"));
//            model.addObject("food",adminMaintain.getAverage( "food"));
//            model.addObject("beverage",adminMaintain.getAverage("beverage"));
//            model.addObject("overall",adminMaintain.getAverage( "overall"));
//            model.addObject("refer",adminMaintain.getAverage( "refer"));
           
//            model.addObject("service_stat",adminMaintain.getAverageOnDate("service", new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
//            model.addObject("menu_stat",adminMaintain.getAverageOnDate("menu", new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
//            model.addObject("ambiance_stat",adminMaintain.getAverageOnDate("ambiance", new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
//            model.addObject("food_stat",adminMaintain.getAverageOnDate("food", new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
//            model.addObject("beverage_stat",adminMaintain.getAverageOnDate("beverage", new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
//            model.addObject("overall_stat",adminMaintain.getAverageOnDate("overall", new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
//            model.addObject("refer_stat",adminMaintain.getAverageOnDate("refer", new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
            
            
//             model.addObject("nps",adminMaintain.getNPS());
//              model.addObject("prom",adminMaintain.getProm());
//            model.addObject("pass",adminMaintain.getPass());
//              model.addObject("det",adminMaintain.getDet());
//              
//              model.addObject("client", adminMaintain.getData());
//             model.addObject("total", adminMaintain.getTodayCount());
            return model;
    }
    @RequestMapping(value = "/adminlogin")
    public  ModelAndView adminLogin(HttpServletRequest request,Principal principal) {
            return new ModelAndView("adminlogin");
    }

        
        @RequestMapping(value = "/admin/settings")
        public  ModelAndView adminSettings(HttpServletRequest request,Principal principal) {
            ModelAndView model= new ModelAndView("adminsettings");
            if(request.getParameter("msg")!=null){
                if(request.getParameter("msg").equals("true")){
                     model.addObject("message","Changed."); 
                } 
                else if(request.getParameter("msg").equals("false")){
                    model.addObject("message","Not changed. Please try again!"); 
                } 
             }
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            return model;
        }
        
       @RequestMapping(value = "/admin/settings/change_pswrd")
        public  ModelAndView adminChangePassword(HttpServletRequest request,Principal principal) {
            Admin admin=adminMaintain.checkPresence(new Admin(principal.getName()));
            Md5PasswordEncoder encoder = new Md5PasswordEncoder();
            String temPassword=encoder.encodePassword(request.getParameter("oldpassword"), null);
            encoder=null;
            ModelAndView model= new ModelAndView("adminpage");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            if(admin.getPassword().equals(temPassword)&&request.getParameter("newpassword").equals(request.getParameter("rnewpassword"))){
                if(request.getParameter("newpassword").length()>=6){
                     if(adminMaintain.setPassword(admin,request.getParameter("newpassword"))){
                         admin=null;
                            return new ModelAndView("redirect:/admin/settings?msg=true"); 
                     }
                }
            }
            admin=null;
            return new ModelAndView("redirect:/admin/settings?msg=false"); 
        }
       
     
     
    
}
