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
import javax.persistence.OneToMany;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author MumbaiZone
 */
@Entity
public class View implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String type;
    private String metadata;
    private String barcodeImageUrl;
    @Column(unique = true,nullable=false)
    private String url;
    @OneToMany(mappedBy="view")
    @Cascade({CascadeType.DELETE})
    private List<Page> pages = new ArrayList<>();

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getBarcodeImageUrl() {
        return barcodeImageUrl;
    }

    public void setBarcodeImageUrl(String barcodeImageUrl) {
        this.barcodeImageUrl = barcodeImageUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }
    
    

    
}
