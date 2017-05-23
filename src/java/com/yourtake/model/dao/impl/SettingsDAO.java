/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yourtake.model.dao.impl;

import com.yourtake.model.dao.plan.GenericDAOAbstract;
import com.yourtake.model.pojo.admin.AdminSettings;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MumbaiZone
 */
@Repository
public class SettingsDAO extends GenericDAOAbstract<AdminSettings,Object> implements java.io.Serializable {

    public SettingsDAO() {
        setClassType(AdminSettings.class);
    }
   
    
}
