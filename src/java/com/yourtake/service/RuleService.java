/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yourtake.service;

import com.yourtake.model.dao.plan.GenericDAOAbstract;
import com.yourtake.model.pojo.setup.Contact;
import com.yourtake.model.pojo.setup.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MumbaiZone
 */
@Service
@Transactional
public class RuleService {
   @Autowired
     @Qualifier("ruleDAO")
    GenericDAOAbstract rdao;
      @Autowired
     @Qualifier("contactDAO")
    GenericDAOAbstract cdao;
      
    public Rule build(Rule r, String email){
        String e[]=email.split("|");
       Rule rule =(Rule) rdao.buildEntity(r);
                List<Contact> clist= new ArrayList<>();
                 for(String val:e){
                     Contact c = new Contact();
                     c.setUserId(val.split(",")[0]);
                     c.setNumber(val.split(",")[1]);
                     clist.add((Contact)cdao.buildEntity(c));
                 }
                 rule =(Rule) rdao.readPropertyEagerly(rule.getId());
                 for(Contact co:clist){
                     co.setRule(rule);
                     rule.getContacts().add(co);
                     cdao.updateProperty(co);
                 }
                 rdao.updateProperty(rule);
                 
                 return rule;
    }

    public Rule getRule(String parameter) {
        return (Rule) rdao.readProperty(Long.valueOf(parameter));
        
    }
    
    
}
