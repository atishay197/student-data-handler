package objects;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by atishay197 on 6/28/16.
 */
public class StudentDto {
    private String name;
    private int roll;
    private String college;
    private String grade;

    public void setName(String name){this.name = name;}
    public void setRoll(int roll){this.roll = roll;}
    public void setCollege(String college){this.college = college;}
    public void setGrade(String grade){this.grade = grade;}

    public String getName(){return name;}
    public int getRoll(){return roll;}
    public String getCollege(){return college;}
    public String getGrade(){return grade;}

    public static StudentDto getDto(JSONObject studentDetails) throws JSONException{
        StudentDto dto = new StudentDto();
        dto.setName(studentDetails.get("Name").toString());
        dto.setRoll(Integer.parseInt(studentDetails.get("Roll").toString()));
        dto.setCollege(studentDetails.get("College").toString());
        dto.setGrade(studentDetails.get("Grade").toString());
        return dto;
    }

    public static StudentDto setDto(JSONObject studentDetails) throws JSONException{
        StudentDto dto = new StudentDto();
        dto.setName(studentDetails.get("name").toString());
        dto.setRoll(Integer.parseInt(studentDetails.get("roll").toString()));
        dto.setCollege(studentDetails.get("college").toString());
        return dto;
    }
}
