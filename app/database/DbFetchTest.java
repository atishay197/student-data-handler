package database;


import com.google.inject.Inject;
import objects.StudentDetailsBo;

import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

import static play.libs.Json.toJson;

/**
 * Created by atishay197 on 6/30/16.
 */
public class DbFetchTest extends Controller{

    @Inject public JPAApi jpaApi;
//    public DbFetchTest(JPAApi api) {
//        this.jpaApi = api;
//    }

    @Transactional(readOnly = true)
    public List<StudentDetailsBo> getStudent(){
        List<StudentDetailsBo> students = (List<StudentDetailsBo>)jpaApi.em().createQuery("select p from StudentDetailsBo p").getResultList();
        return students;
    }

    @Transactional(readOnly = true)
    public Result getStudents(){
        List<StudentDetailsBo> students = (List<StudentDetailsBo>)jpaApi.em().createQuery("select p from StudentDetailsBo p").getResultList();
        return ok(toJson(students));
    }

    @Transactional
    public StudentDetailsBo studentDetails(StudentDetailsBo bo){
//        EntityManager em = jpaApi.em();
//        Query q = em.createNativeQuery("SELECT College FROM StudentDetails WHERE ID = ? AND Name = ?;");
//        bo.setCollege(q.getParameter(1).toString());
//        System.out.println("College : " + bo.getCollege());
        return bo;
    }
}
