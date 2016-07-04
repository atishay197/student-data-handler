package service;

import database.DbFetch;
import database.DbStore;
import objects.StudentDetailsBo;
import objects.StudentDto;
import play.db.jpa.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by atishay197 on 6/29/16.
 */
public class Teacher {


    DbFetch dbFetch;
    DbStore dbStore;

    @Inject
    public Teacher(DbStore dbStore,DbFetch dbFetch){
        this.dbStore = dbStore;
        this.dbFetch = dbFetch;
    }

    @Transactional
    public List<StudentDto> getAllStudentDetails(){
        List<StudentDto> listStudentDto = new ArrayList();
        List<StudentDetailsBo> listStudentDetailsBo = dbFetch.getStudent();
        for(Iterator i = listStudentDetailsBo.iterator(); i.hasNext();){
            StudentDetailsBo bo = (StudentDetailsBo)i.next();
            StudentDto dto = new StudentDto(bo);
            listStudentDto.add(dto);
        }

        return listStudentDto;
    }

}
