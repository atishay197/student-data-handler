package service;

import database.DbFetch;
import database.DbStore;
import objects.StudentDetailsBo;
import objects.StudentDto;
import play.db.jpa.Transactional;
import service.EncryptionDecryption.EncryptDecrypt;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by atishay197 on 6/29/16.
 */

public class Student {

    DbFetch dbFetch;
    DbStore dbStore;

    @Inject
    public Student(DbFetch dbFetch){
        this.dbFetch = dbFetch;
    }

    @Inject
    public Student(DbStore dbStore){
        this.dbStore = dbStore;
    }

    public StudentDto register(StudentDto dto){
        StudentDetailsBo bo = StudentDetailsBo.copyDtoToBo(dto);
        bo = dbStore.storeStudent(bo);
        String encryptedRoll = EncryptDecrypt.encrypt(bo.getRollString());
        dto.setRoll(encryptedRoll);
        return dto;
    }

    public StudentDto getStudentDetails(StudentDto dto){
        String roll = EncryptDecrypt.decrypt(dto.getRoll());
        dto.setRoll(roll);
        StudentDetailsBo bo = StudentDetailsBo.copyDtoToBo(dto);
        try {
            //bo = DatabaseFetch.getStudentData(bo);
            //bo = dbft.studentDetails(bo);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        String encryptedRoll = EncryptDecrypt.encrypt(bo.getRollString());
        dto = new StudentDto(bo);
        dto.setRoll(encryptedRoll);
        return dto;
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
