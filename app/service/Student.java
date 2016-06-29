package service;

import database.DatabaseFetch;
import database.DatabaseInsert;
import objects.StudentBo;
import objects.StudentDto;
import service.EncryptionDecryption.EncryptDecrypt;

/**
 * Created by atishay197 on 6/29/16.
 */
public class Student {

    public static StudentDto register(StudentDto dto){
        StudentBo bo = StudentBo.copyDtoToBo(dto);
        bo = DatabaseInsert.storeStudentData(bo);
        String encryptedRoll = EncryptDecrypt.encrypt(bo.getRoll());
        dto.setRoll(encryptedRoll);
        return dto;
    }

    public static StudentDto getStudentDetails(StudentDto dto){
        String roll = EncryptDecrypt.decrypt(dto.getRoll());
        dto.setRoll(roll);
        StudentBo bo = StudentBo.copyDtoToBo(dto);
        try {
            bo = DatabaseFetch.getStudentData(bo);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        String encryptedRoll = EncryptDecrypt.encrypt(bo.getRoll());
        dto = new StudentDto(bo);
        dto.setRoll(encryptedRoll);
        return dto;
    }
}
