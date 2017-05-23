/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yourtake.model.pojo.setup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author MumbaiZone
 */
@Entity
public class Rule implements Serializable {
    @Id 
    @GeneratedValue
    private Long id;
    private String formInputName;
    private Double lowerCap;
    private Double upperCap;
    @ManyToOne
    private Organization organization;
    @OneToMany(mappedBy="rule")
    @Cascade({CascadeType.DELETE})
    private List<Contact> contacts = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormInputName() {
        return formInputName;
    }

    public void setFormInputName(String formInputName) {
        this.formInputName = formInputName;
    }

    public Double getLowerCap() {
        return lowerCap;
    }

    public void setLowerCap(Double lowerCap) {
        this.lowerCap = lowerCap;
    }

    public Double getUpperCap() {
        return upperCap;
    }

    public void setUpperCap(Double upperCap) {
        this.upperCap = upperCap;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    
    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

   
    

    
}
