/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yourtake.service;

import com.yourtake.model.dao.plan.GenericDAOAbstract;
import com.yourtake.model.pojo.setup.Organization;
import com.yourtake.model.pojo.views.View;
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
public class ViewService {
     @Autowired
     @Qualifier("viewDAO")
    GenericDAOAbstract dao;
    
    public View  search(String url){
        List<View> listView=dao.fetchByNative("url", url, null, null, MatchMode.EXACT);
       if(listView.size()>0){
           return listView.get(0);
       }
       return null;
    }
    
    
       public View fetchWithInnerList(View  view){
        return (View) dao.readPropertyEagerly(view.getId());
    }

    public void update(View view) {
        dao.updateProperty(view);
    }

    public void build(View v) {
        dao.buildEntity(v);
    }
}
