/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yourtake.model.dao.impl;


import com.yourtake.model.dao.plan.GenericDAOAbstract;
import com.yourtake.model.pojo.setup.Organization;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MumbaiZone
 */
@Repository
public class OrganizationDAO extends GenericDAOAbstract {

    public OrganizationDAO() {
        setClassType(Organization.class);
    }
    
}