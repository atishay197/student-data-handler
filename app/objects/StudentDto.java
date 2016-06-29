package objects;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by atishay197 on 6/28/16.
 */
public class StudentDto {
    private String name;
    private String roll;
    private String college;
    private String grade;

    public void setName(String name){this.name = name;}
    public void setRoll(String roll){this.roll = roll;}
    public void setCollege(String college){this.college = college;}
    public void setGrade(String grade){this.grade = grade;}

    public String getName(){return name;}
    public String getRoll(){return roll;}
    public String getCollege(){return college;}
    public String getGrade(){return grade;}

    public static StudentDto getDto(JSONObject studentDetails) throws JSONException{
        StudentDto dto = new StudentDto();
        dto.setName(studentDetails.get("Name").toString());
        dto.setRoll(studentDetails.get("Roll").toString());
        dto.setCollege(studentDetails.get("College").toString());
        dto.setGrade(studentDetails.get("Grade").toString());
        return dto;
    }
    public StudentDto(){
        this.name = "";
        this.college = "";
        this.roll = "";
        this.grade = "";
    }
    public StudentDto(JSONObject studentDetails) throws JSONException{
        if(studentDetails.has("name")) {
            this.name = studentDetails.get("name").toString();
        }
        if(studentDetails.has("college")) {
            this.college = studentDetails.get("college").toString();
        }
        if(studentDetails.has("roll")) {
            this.roll = studentDetails.get("roll").toString();
        }
        if(studentDetails.has("grade")) {
            this.grade = studentDetails.get("grade").toString();
        }
    }

    public StudentDto(StudentBo bo){
        this.name = bo.getName();
        this.college = bo.getCollege();
        this.roll = bo.getRoll();
        this.grade = bo.getGrade();
    }

    public static JSONObject dtoToJSON(StudentDto dto){
        Map<String,String> m = new HashMap();
        m.put("name",dto.getName());
        m.put("college",dto.getCollege());
        m.put("roll",dto.getRoll());
        m.put("grade",dto.getGrade());
        return (new JSONObject(m));
    }

}
