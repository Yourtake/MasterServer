/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yourtake.service;


import com.yourtake.backend.service.core.email.SimpleEmailService;
import com.yourtake.model.dao.plan.GenericDAOAbstract;
import com.yourtake.model.pojo.admin.Admin;
import com.yourtake.model.pojo.admin.AdminSettings;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Welcome
 */
@Service
@Transactional
public class AdminMaintainService {
    

    @Autowired
    @Qualifier("adminDAO")
    private GenericDAOAbstract adao;   
    @Autowired
    @Qualifier("adminDAO")
    private GenericDAOAbstract asdao;   
String settings ="settings";
@Transactional(propagation = Propagation.REQUIRED)
    public Admin checkPresence(Admin admin){
       admin = (Admin) adao.readProperty(admin.getUsername());
       return admin;
    }
@Transactional(propagation = Propagation.REQUIRED)
    public boolean buildEmployee(String username, String name, String work, String initiatorName,String number,Integer power,String organization) {
        Admin creator =  checkPresence(new Admin(initiatorName));
        
        
            Integer passcode=null;
            Random r = new Random();
            passcode=r.nextInt(9)*1000+r.nextInt(9)*100+r.nextInt(9)*10+r.nextInt(9);
            Md5PasswordEncoder encoder = new Md5PasswordEncoder();
            String pass=encoder.encodePassword(passcode.toString(), null);
            new SimpleEmailService("Welcome to Jubination!!",
                    "Hi, "
                    + "<br/>"
                    + "<br/>"
                    +" We welcome you to YourTake Launchpad. Please note your following credentials. You can change your password through settings.<br/>"
                    + "<br/> "
                    + "<br/>"
                    + "Your user id is "+username+" and password is : "+pass
                    + "<br/>"
                    + "<br/>"
                    + "Regards,<br/>Jubination Support",
                    username).start();
             System.err.println(pass);
            passcode=null;
            encoder=null;
            
        
        if(creator==null){
            return false;
        }
      
        Admin admin=new Admin(username);
       admin.setPassword(pass);
       admin.setRole("ROLE_ADMINISTRATOR");
       admin.setWork(work);
       admin.setPower(power);
       admin.setOrganization(organization);
       admin.setName(name);
       admin.setNumber(number);
        pass=null;
        encoder = new Md5PasswordEncoder();
            admin.setPassword(encoder.encodePassword(admin.getPassword(), null));
                if(adao.buildEntity(admin)!=null){
                    admin=null;
                    return true;
                }
    return false;
    }
@Transactional(propagation = Propagation.REQUIRED)
      public List<Admin> getHrList(int power){
          return (List<Admin>) adao.fetchAll("power", null);
      }      
@Transactional(propagation = Propagation.REQUIRED)
    public boolean setPassword(Admin admin, String parameter) {
        String password=new Md5PasswordEncoder().encodePassword(parameter, null);
                    admin.setPassword(password);
       return adao.updateProperty(admin);
    }
@Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteEmployee(Admin admin) {
       return adao.deleteEntity(admin);
    }


    
    
    public AdminSettings readSettings(String settingsName){
        return (AdminSettings) asdao.readProperty(settingsName);
    }
    
    public boolean setSettings(AdminSettings settings){
        return asdao.updateProperty(settings);
    }
    public AdminSettings  buildSettings(AdminSettings settings){
        return (AdminSettings) asdao.buildEntity(settings);
    }


    

}
