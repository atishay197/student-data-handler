package database;

import com.google.inject.Inject;
import objects.StudentDetailsBo;

import objects.StudentDto;
import objects.StudentGradeBo;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DbFetch extends Controller{

    @Inject public JPAApi jpaApi;

    @Transactional(readOnly = true)
    public List<StudentDetailsBo> getStudent(){
        List<StudentDetailsBo> sdbList = jpaApi.em().createQuery("from StudentDetailsBo").getResultList();
        List<StudentDetailsBo> sdbListSend = new ArrayList<>();
        for(Iterator i = sdbList.iterator(); i.hasNext(); ){
            StudentDetailsBo bo = (StudentDetailsBo)i.next();
            StudentGradeBo boGrade =  jpaApi.em().find(StudentGradeBo.class,bo.getRoll());
            if(boGrade!=null && boGrade.getGrade()!=null){
                bo.setGrade(boGrade.getGrade());
            }
            sdbListSend.add(bo);
        }
        return sdbListSend;
    }

    @Transactional(readOnly = true)
    public StudentDetailsBo getstudentDetails(StudentDetailsBo bo){
        System.out.println("Bo Roll : " + bo.getRoll());
        return jpaApi.em().find(StudentDetailsBo.class,bo.getRoll());
    }
}
