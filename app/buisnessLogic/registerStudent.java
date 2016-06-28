package buisnessLogic;

import buisnessLogic.EncryptionDecryption.EncryptDecrypt;
import database.DatabaseInsert;
import objects.StudentBo;
import objects.StudentDto;

/**
 * Created by atishay197 on 6/28/16.
 */
public class registerStudent {

    public static StudentDto register(StudentDto dto){
        StudentBo bo = StudentBo.setDetailsBo(dto);
        bo = DatabaseInsert.storeStudentData(bo);
        String encryptedRoll = EncryptDecrypt.encrypt(bo.getRoll());
        System.out.println("encrypted Roll : " + encryptedRoll);
        dto.setRoll(encryptedRoll);
        return dto;
    }


    public static String getRoll(StudentDto dto){
        return dto.getRoll();
    }

}
