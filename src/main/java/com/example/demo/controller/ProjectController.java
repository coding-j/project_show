package com.example.demo.controller;

import com.example.demo.entity.*;
import org.json.JSONObject;
import com.example.demo.dao.impl.FileDaoImpl;
import com.example.demo.dao.impl.PictureDaoImpl;
import com.example.demo.dao.impl.ProjectDaoImpl;
import com.example.demo.dao.impl.VideoDaoImpl;
import com.example.demo.service.impl.FileServiceImpl;
import com.example.demo.service.impl.PictureServiceImpl;
import com.example.demo.service.impl.VideoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectDaoImpl projectDao;
    @Autowired
    private FileDaoImpl fileDao;
    @Autowired
    private FileServiceImpl fileService;
    @Autowired
    private PictureServiceImpl pictureService;
    @Autowired
    private VideoServiceImpl videoService;
    @Autowired
    private PictureDaoImpl pictureDao;
    @Autowired
    private VideoDaoImpl videoDao;

    @RequestMapping("/projecNametList")
    public List<String> getProjectNameList(){      //向前端传项目名列表
        List<String> projectNameList = projectDao.queryProjectNameList();
        return projectNameList;
    }

    @RequestMapping(value = "/projectList",method = RequestMethod.POST)
    public List<Project> getProjectList(@RequestBody Pagination pagination){  // 项目列表
//        System.out.println("projectList");
        List<Project> projectList = projectDao.queryProjectList(pagination);
        return projectList;
    }

    @RequestMapping("/typeList")
    public List<String> getTypeList(){      //项目列表中的类型列表
//        System.out.println("typeList");
        List<String> typeList = projectDao.queryTypeList();
        return typeList;
    }

    @RequestMapping("/finishDateList")
    public List<String> getfinishDateList(){        //项目列表中的完成时间列表
//        System.out.println("finishDateList");
        List<String> finishDateList = projectDao.queryFinishDateList();
        return finishDateList;
    }

    @RequestMapping(value = "/searchName", method = RequestMethod.POST)
    public List<Project> getProjectByName(@RequestBody Map<String,Object> map){         //根据项目名称搜索
        String projectName = (String) map.get("projectName");
        int index = (int) map.get("index");
        System.out.println("projectName:"+projectName);
        System.out.println("index:"+ index);
        return projectDao.queryProjectResourceByName(projectName,index);
    }

    @RequestMapping(value = "/searchType", method = RequestMethod.POST)
    public List<Project> getProjectListByType(String typeName){      //根据项目类型搜索
        System.out.println(typeName);
        typeName = typeName.replace("<span>","");
        typeName = typeName.replace("</span>","");
        System.out.println(typeName);
        List<Project> projectList = projectDao.queryProjectListByType(typeName);
        return projectList;
    }

    @RequestMapping(value = "/searchTime", method = RequestMethod.POST)
    public List<Project> getProjectListByTime(String finishDate){       //根据项目时间搜索
        List<Project> projectList = projectDao.queryProjectListByTime(finishDate);
        return projectList;
    }

    @RequestMapping(value = "/searchProjectByTeacherId", method = RequestMethod.POST)
    public List<Project> getProjectListByTeacherName(int teacherId){       //根据老师姓名搜索项目列表
        List<Project> projectList = projectDao.queryProjectByTeacherId(teacherId);
        return projectList;
    }

//    @RequestMapping(value = "/uploadProject", method = RequestMethod.POST)
//    @ResponseBody
//    public String uploadProject(Project project){   //老师project内容上传至数据库
//        List<File> files = project.getFileList();
//        List<Picture> pictures = project.getPictureList();
//        List<Video> videos = project.getVideoList();
//        for(File file : files){
//            fileService.FileAdd(file,project.getProjectName());
//        }
//        for(Picture picture : pictures){
//            pictureService.PictureAdd(picture,project.getProjectName());
//        }
//        for(Video video : videos){
//            videoService.VideoAdd(video,project.getProjectName());
//        }
////        String projectName = project.getProjectName();
////        int typeId = project.getTypeId();
////        String finishDate = project.getFinishDate();
////        String studentName = project.getStudentName();
////        int teacherId = project.getTeacherId();
////        String projectDetail = project.getProjectDetail();
////        String teamDetail = project.getTeamDetail();
//        projectDao.add(project);
//        return "上传成功";
//    }

    @RequestMapping(value = "/projectShow", method = RequestMethod.POST)
    @ResponseBody
    public Project ProjectShow(int projectId){   //    项目展示，将project的数据传到前端
        System.out.println("getId:"+projectId);
        Project project = projectDao.queryProjectById(projectId);
//        System.out.println("projectName:"+project.getProjectName());
        System.out.println("getName:"+project.getProjectName());
        return project;
    }

    @RequestMapping(value = "/projectFile", method = RequestMethod.POST)
    public List<File> getFileListById(int projectId){
        List<File> list = fileService.queryFileByProjectId(projectId);
        return list;
    }

    @RequestMapping(value = "/projectPicture", method = RequestMethod.POST)
    public List<Picture> getPictureListById(int projectId){
        List<Picture> list = pictureService.queryPictureByProjectId(projectId);
        System.out.println(list);
        return list;
    }

    @RequestMapping(value = "/projectVideo", method = RequestMethod.POST)
    public List<Video> getVideoListById(int projectId){
        List<Video> list = videoService.queryVideoByProjectId(projectId);
        return list;
    }

//    @RequestMapping("/projectEdit")
//    @ResponseBody
//    public String projectEdit(Project project){     //管理员修改项目内容
//        projectDao.update(project);
//        return "编辑成功";
//    }
//
//    public String projectDel(String projectName){       //管理员删除项目
//        projectDao.deleteByProjectName(projectName);
//        return "删除成功";
//    }
//
//    public String projectFileAdd(Project project){      //管理员修改文件（上传）
//        List<File> fileList = project.getFileList();
//        for(File file : fileList){
//            fileService.FileAdd(file,project.getProjectName());
//        }
//        return "文件上传成功";
//    }
//
//    public String projectFileDel(String fileName){      //管理员修改文件（删除）
//        fileDao.deleteFileByName(fileName);
//        return "文件删除成功";
//    }
//
//    public String projectPictureAdd(Project project){      //管理员修改图片（上传）
//        List<Picture> pictureList = project.getPictureList();
//        for(Picture picture : pictureList){
//            pictureService.PictureAdd(picture,project.getProjectName());
//        }
//        return "文件上传成功";
//    }
//
//    public String projectPictureDel(String pictureName){      //管理员修改图片（删除）
//        pictureDao.deletePictureByName(pictureName);
//        return "文件删除成功";
//    }
//
//    public String projectVideoAdd(Project project){      //管理员修改视频（上传）
//        List<Video> videoList = project.getVideoList();
//        for(Video video : videoList){
//            videoService.VideoAdd(video,project.getProjectName());
//        }
//        return "文件上传成功";
//    }
//
//    public String projectVideoDel(String videoName){      //管理员修改视频（删除）
//        videoDao.deleteVideoByName(videoName);
//        return "文件删除成功";
//    }

    public List<String> getHomePageList(){          //导航栏
        List<String> list = projectDao.queryHomePageList();
        return list;
    }

    @RequestMapping(value = "/getHomePage")
    public List<Project> getHomePageProjectShow(){      //首页展示rating排名前三的项目
        List<Project> list = projectDao.queryProjectListByRating();
        return list;
    }

    @RequestMapping("/addRating")
    public int projectRatingAdd(int projectId){       //打分++
        projectDao.updateRating(projectId);
        int rating = projectDao.queryProjectById(projectId).getRating();
        System.out.println("rating:"+rating);
        return rating;
    }

    @RequestMapping("/ProjectCount")
    public int getProjectCount(@RequestBody Pagination pagination){
        return projectDao.projectCount(pagination);
    }

    @RequestMapping(value = "/ProjectCountByName",method = RequestMethod.POST)
    public int getProjectCountByName(String projectName){
        return projectDao.projectCountByName(projectName);
    }

}
