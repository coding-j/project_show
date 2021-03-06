package com.example.demo.entity;

import javax.persistence.Transient;
import java.util.List;

public class Teacher {
    private int teacherId;
    private String teacherName;
    private Picture picture;
    private String pictureUrl;
    private String uidName;
    private String working;
    private String address;
    private String introduction;
    private String workExperience;
    @Transient
    private List<String> workList;  //BeanPropertyRowMapper映射可能会出问题

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getWorking() {
        return working;
    }

    public void setWorking(String working) {
        this.working = working;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }


    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public List<String> getWorkList() {
        return workList;
    }

    public void setWorkList(List<String> workList) {
        this.workList = workList;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getUidName() {
        return uidName;
    }

    public void setUidName(String uidName) {
        this.uidName = uidName;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
