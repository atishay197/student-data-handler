package buisnessLogic;

import buisnessLogic.EncryptionDecryption.EncryptDecrypt;
import database.DatabaseFetch;
import objects.StudentBo;
import objects.StudentDto;

/**
 * Created by atishay197 on 6/28/16.
 */
public class studentDetails {

    public static StudentDto getStudentDetails(StudentDto dto){
        String roll = EncryptDecrypt.decrypt(dto.getRoll());
        dto.setRoll(roll);
        StudentBo bo = StudentBo.getGradeBo(dto);
        try {
            bo = DatabaseFetch.getStudentData(bo);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        String encryptedRoll = EncryptDecrypt.encrypt(bo.getRoll());
        dto.setRoll(encryptedRoll);
        dto = new StudentDto(bo);
        return dto;
    }
}
