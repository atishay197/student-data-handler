package database;

import com.google.inject.Inject;
import objects.StudentDetailsBo;
import objects.StudentGradeBo;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;


/**
 * Created by atishay197 on 7/4/16.
 */
public class DbStore {
    @Inject
    public JPAApi jpaApi;

    @Transactional
    public StudentDetailsBo storeStudent(StudentDetailsBo bo){
        try{
            bo = jpaApi.em().merge(bo);
            return bo;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public StudentGradeBo storeGrade(StudentGradeBo bo){
        try {
            jpaApi.em().merge(bo);
            bo = jpaApi.em().find(StudentGradeBo.class, bo.getRoll());
            return bo;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
