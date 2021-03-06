package com.example.demo.dao;

import com.example.demo.entity.Pagination;
import com.example.demo.entity.Project;

import java.util.List;

public interface ProjectDao {
    int add(Project project);
    int update(Project project);
    int updateRating(int projectId);
    int deleteByProjectId(int projectId);
    int queryProjectIdByName(String projectName);
    int projectCount(Pagination pagination);
    int projectCountByName(String projectName);
    String queryPorjectNameById(int projectId);
    List<Project> queryProjectResourceByName(String projectName,int index);
    Project queryProjectByName(String projectName);
    Project queryProjectById(int projectId);
    List<String> queryProjectNameList();
    List<String> queryHomePageList();
    int typeAdd(String typeName);
    int finishAdd(String finishDateName);
    List<String> queryTypeList();
    List<String> queryFinishDateList();
    List<Project> queryProjectList(Pagination pagination);
    List<Project> queryProjectListByType(String typeName);
    List<Project> queryProjectListByTime(String finishDate);
    List<Project> queryProjectListByRating();
    List<Project> queryProjectByTeacherId(int teacherId);
}
