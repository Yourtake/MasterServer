/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yourtake.model.pojo.setup;



import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author MumbaiZone
 */
@Entity
public class Branch implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String status;
    private Boolean live;
    @ManyToOne
    private Organization branchRelation;
    @OneToOne
    private Organization masterRelation;
    //linking with view
    private Long view;
    
  
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getLive() {
        return live;
    }

    public void setLive(Boolean live) {
        this.live = live;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Organization getBranchRelation() {
        return branchRelation;
    }

    public void setBranchRelation(Organization branchRelation) {
        this.branchRelation = branchRelation;
    }

    public Organization getMasterRelation() {
        return masterRelation;
    }

    public void setMasterRelation(Organization masterRelation) {
        this.masterRelation = masterRelation;
    }

    public Long getView() {
        return view;
    }

    public void setView(Long view) {
        this.view = view;
    }

  

}
