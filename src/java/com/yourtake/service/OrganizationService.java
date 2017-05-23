/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yourtake.service;

import com.yourtake.model.dao.plan.GenericDAOAbstract;
import com.yourtake.model.pojo.setup.Organization;
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
public class OrganizationService {
    
    @Autowired
     @Qualifier("organizationDAO")
    GenericDAOAbstract odao;
    
    public Organization  search(String url){
        List<Organization> listOrg=odao.fetchByNative("url", url, null, null, MatchMode.EXACT);
       if(listOrg.size()>0){
           return listOrg.get(0);
       }
       return null;
    }
    
    public Organization fetchWithInnerList(Organization org){
        return (Organization) odao.readPropertyEagerly(org.getId());
    }
    
    public Organization build(Organization org){
        return (Organization) odao.buildEntity(org);
    }

    public void update(Organization org) {
        odao.updateProperty(org);
    }
    
}
