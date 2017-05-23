/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yourtake.model.pojo.setup;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author MumbaiZone
 */
@Entity
public class Feedback implements Serializable{
     @Id
    @GeneratedValue
    private Long id;
     private Long orgnization;
     private Long branch;
     @Column(name="question")
    private String question;
    @Column(name="type")
    private String type;
    @Column(name="answer", length=300)
    private String answer;
    @Column(name="date", length=300)
    private String date;
     
    public Feedback(String type,String question, String answer,Long organization) {
        this.type=type;
        this.question = question;
        this.answer = answer;
        this.orgnization=organization;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrgnization() {
        return orgnization;
    }

    public void setOrgnization(Long orgnization) {
        this.orgnization = orgnization;
    }

    public Long getBranch() {
        return branch;
    }

    public void setBranch(Long branch) {
        this.branch = branch;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

 
}
