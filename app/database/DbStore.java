package database;

import com.google.inject.Inject;
import objects.StudentDetailsBo;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;

import java.util.List;

/**
 * Created by atishay197 on 7/4/16.
 */
public class DbStore {
    @Inject
    public JPAApi jpaApi;

    @Transactional
    public StudentDetailsBo storeStudent(StudentDetailsBo bo){
        try{
            jpaApi.em().persist(bo);
            bo = jpaApi.em().find(bo.getClass(),bo.getRoll());
            return bo;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
