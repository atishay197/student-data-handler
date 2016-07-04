package database;

import com.google.inject.Inject;
import objects.StudentDetailsBo;

import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;

import java.util.List;


/**
 * Created by atishay197 on 6/30/16.
 */
public class DbFetch extends Controller{

    @Inject public JPAApi jpaApi;

    @Transactional(readOnly = true)
    public List<StudentDetailsBo> getStudent(){
        List<StudentDetailsBo> students = (List<StudentDetailsBo>)jpaApi.em().createQuery("from StudentDetailsBo").getResultList();
        return students;
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
