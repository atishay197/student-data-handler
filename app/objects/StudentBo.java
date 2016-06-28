package objects;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by atishay197 on 6/28/16.
 */
public class StudentBo {
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

    public static StudentBo getBo(ResultSet rsDetails,ResultSet rsGrade) throws SQLException {
        StudentBo bo = new StudentBo();
        bo.setName(rsDetails.getString("Name"));
        bo.setRoll(rsDetails.getString("Roll"));
        bo.setCollege(rsDetails.getString("College"));
        bo.setGrade(rsGrade.getString("Grade").toString());
        return bo;
    }

    public static StudentBo setDetailsBo(JSONObject studentDetails) throws JSONException{
        StudentBo bo = new StudentBo();
        bo.setName(studentDetails.get("Name").toString());
        bo.setRoll(studentDetails.get("roll").toString());
        bo.setGrade(studentDetails.get("college").toString());
        return bo;
    }


    public static StudentBo setGradeBo(JSONObject studentGrades) throws JSONException{
        StudentBo bo = new StudentBo();
        bo.setRoll(studentGrades.get("roll").toString());
        bo.setGrade(studentGrades.get("grades").toString());
        return bo;
    }
}