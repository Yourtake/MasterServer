/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yourtake.service;

import com.yourtake.model.dao.plan.GenericDAOAbstract;
import com.yourtake.model.pojo.setup.Branch;
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
public class BranchService {
  @Autowired
     @Qualifier("branchDAO")
    GenericDAOAbstract dao;
    public Branch build(Branch b) {
         return (Branch) dao.buildEntity(b);
    }

    public Branch getBranch(String parameter) {
        return (Branch) dao.readProperty(Long.valueOf(parameter));
    }

    public void update(Branch branch) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
