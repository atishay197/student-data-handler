package service;

import database.DbFetch;
import database.DbStore;
import objects.StudentDetailsBo;
import objects.StudentDto;
import org.springframework.beans.BeanUtils;

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
    public Student(DbStore dbStore,DbFetch dbFetch){
        this.dbStore = dbStore;
        this.dbFetch = dbFetch;
    }

    @Transactional
    public StudentDto getStudentDetails(StudentDto dto){
        int roll = EncryptDecrypt.decrypt(dto.getRoll());
        StudentDetailsBo bo= new StudentDetailsBo();
        bo.setRoll(roll);
        try {
            bo = dbFetch.getstudentDetails(bo);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        dto = new StudentDto(bo);
        dto.setRoll(EncryptDecrypt.encrypt(bo.getRollString()));
        return dto;
    }

    @Transactional
    public StudentDto register(StudentDto dto){
        StudentDetailsBo bo = new StudentDetailsBo();
        System.out.println("DTO Name : " + dto.getName() + " College : " + dto.getCollege());
        BeanUtils.copyProperties(dto,bo);
        System.out.println("BO  Name : " + bo.getName() + " College : " + bo.getCollege());
        bo = dbStore.storeStudent(bo);
        String encryptedRoll = EncryptDecrypt.encrypt(bo.getRollString());
        dto.setRoll(encryptedRoll);
        return dto;
    }

}
