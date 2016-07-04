package database;

import com.google.inject.Inject;
import objects.StudentDetailsBo;

import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;

import java.util.List;

public class DbFetch extends Controller{

    @Inject public JPAApi jpaApi;

    @Transactional(readOnly = true)
    public List<StudentDetailsBo> getStudent(){
        return (List<StudentDetailsBo>)jpaApi.em().createQuery("from StudentDetailsBo").getResultList();
    }

    @Transactional(readOnly = true)
    public StudentDetailsBo getstudentDetails(StudentDetailsBo bo){
        System.out.println("Bo Roll : " + bo.getRoll());
        return jpaApi.em().find(StudentDetailsBo.class,bo.getRoll());
    }
}
