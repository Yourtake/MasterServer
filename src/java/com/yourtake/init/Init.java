package com.yourtake.init;





import com.yourtake.model.dao.impl.AdminDAO;
import com.yourtake.model.pojo.admin.Admin;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;


/**
 *
 * @author Welcome
 */
public class Init {
   
    public static void main(String[] args) {
       
       
        
         AdminDAO adao = new AdminDAO();
       Admin admin= new Admin("support@yourtake.in","abcdef","ROLE_ADMINISTRATOR","Support",0,"Administrator","9999999999","Your Take",true);
         Md5PasswordEncoder encoder = new Md5PasswordEncoder();
            admin.setPassword(encoder.encodePassword(admin.getPassword(), null));
        adao.buildInitEntity(admin,HibernateUtil.getSessionFactory());

        System.err.println("Constructed:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        
     
        
        
    }
}
