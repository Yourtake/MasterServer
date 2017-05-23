/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yourtake.service;

import com.yourtake.model.dao.plan.GenericDAOAbstract;
import com.yourtake.model.pojo.views.FormInput;
import com.yourtake.model.pojo.views.Header;
import com.yourtake.model.pojo.views.Page;
import com.yourtake.model.pojo.views.Tab;
import java.util.List;
import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MumbaiZone
 */
@Service
@Transactional
public class PageService {

    
    @Autowired
    @Qualifier("pageDAO")
    GenericDAOAbstract pdao;
    
    @Autowired
    @Qualifier("headerDAO")
    GenericDAOAbstract hdao;
    
     @Autowired
    @Qualifier("formInputDAO")
    GenericDAOAbstract fidao;
     
     @Autowired
    @Qualifier("tabDAO")
    GenericDAOAbstract tdao;
    
    public Page read(String parameter) {
        return (Page) pdao.readProperty(Long.valueOf(parameter));
    }

    public Page build(Page page) {
        return (Page) pdao.buildEntity(page);
    }

    public Header buildHeader(Header header) {
        return (Header) hdao.buildEntity(header);
    }


    public Boolean update(Page page, Header header) {
        page.setHeader(header);
        header.setPage(page);
        return pdao.updateProperty(page)&&hdao.updateProperty(header);
    }
public Page searchPage(String name){
     List<Page> list=pdao.fetchByNative("name", name, null, null, MatchMode.EXACT);
       if(list.size()>0){
           return list.get(0);
       }
       return null;
}

public FormInput getForm(String id){
   return (FormInput) fidao.readProperty(Long.valueOf(id));
}
public Tab getTab(String id){
   return (Tab) tdao.readProperty(Long.valueOf(id));
}

public FormInput  buildForm(FormInput form){
    return (FormInput) fidao.buildEntity(form);
}
public Tab  buildTab(Tab tab){
    return (Tab) tdao.buildEntity(tab);
}
public Page fetchEagerly(Page page){
    return (Page) pdao.readPropertyEagerly(page.getId());
}

    public void update(Page page) {
   
    pdao.updateProperty(page);
    }
    
    
}
