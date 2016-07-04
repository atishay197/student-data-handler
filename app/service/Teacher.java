package service;

import database.DbFetch;
import database.DbStore;
import objects.StudentDetailsBo;
import objects.StudentDto;
import objects.StudentGradeBo;
import objects.TeacherDto;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.BeanUtils;
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
    public List<StudentDto> getAllStudentDetails(String teacherId){
        if(teacherisValid(teacherId)) {
            List<StudentDto> listStudentDto = new ArrayList();
            List<StudentDetailsBo> listStudentDetailsBo = dbFetch.getStudent();
            for (Iterator i = listStudentDetailsBo.iterator(); i.hasNext(); ) {
                StudentDetailsBo bo = (StudentDetailsBo) i.next();
                StudentDto dto = new StudentDto(bo);
                listStudentDto.add(dto);
            }
            return listStudentDto;
        }
        else{
            return null;
        }
    }

    @Transactional
    public TeacherDto setStudentGrade(TeacherDto dto){
        if(teacherisValid(dto.getTeacherId())) {
            StudentGradeBo bo = new StudentGradeBo();
            BeanUtils.copyProperties(dto,bo);
            bo = dbStore.storeGrade(bo);
            BeanUtils.copyProperties(bo,dto);
            return dto;
        }
        else {
            return null;
        }
    }

    public boolean teacherisValid(String teacherId){
        if(teacherId.equals("1"))
            return true;
        else
            return false;
    }
}
