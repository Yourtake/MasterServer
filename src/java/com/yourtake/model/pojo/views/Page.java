/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yourtake.model.pojo.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author MumbaiZone
 */
@Entity
public class Page implements Serializable {
    
    @Id
    @GeneratedValue
     private Long id;
    @Column(unique = true,nullable=false)
    private String name;
    private String type;
    private String backgroundImageUrl;
    private String submitUrl;
    private String relativeUrl;
    @OneToMany(mappedBy="page")
    @Cascade({CascadeType.DELETE})
    private List<FormInput> forms=new ArrayList<>();
    @OneToOne(mappedBy="page")
    @Cascade({CascadeType.DELETE})
    private Header header;
    @OneToMany(mappedBy="page")
    @Cascade({CascadeType.DELETE})
    private List<Tab> tabs= new ArrayList<>();
    @ManyToOne
    private View view; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBackgroundImageUrl() {
        return backgroundImageUrl;
    }

    public void setBackgroundImageUrl(String backgroundImageUrl) {
        this.backgroundImageUrl = backgroundImageUrl;
    }

    public String getSubmitUrl() {
        return submitUrl;
    }

    public void setSubmitUrl(String submitUrl) {
        this.submitUrl = submitUrl;
    }

    public List<FormInput> getForms() {
        return forms;
    }

    public void setForms(List<FormInput> forms) {
        this.forms = forms;
    }


    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public List<Tab> getTabs() {
        return tabs;
    }

    public void setTabs(List<Tab> tabs) {
        this.tabs = tabs;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public String getRelativeUrl() {
        return relativeUrl;
    }

    public void setRelativeUrl(String relativeUrl) {
        this.relativeUrl = relativeUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    

}
