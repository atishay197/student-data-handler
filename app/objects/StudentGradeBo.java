package objects;

import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;

/**
 * Created by atishay197 on 7/1/16.
 */

@Entity
@Table(name = "StudentGrades")
public class StudentGradeBo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int roll;

    @Column(name = "Grade")
    private String grade;

    public void setRoll(int roll){this.roll = roll;}
    public void setGrade(String grade){this.grade = grade;}

    public String getGrade(){return grade;}
    public int getRoll(){return roll;}

    public static StudentGradeBo setGradeBo(JSONObject studentGrades) throws JSONException {
        StudentGradeBo bo = new StudentGradeBo();
        bo.setRoll(Integer.parseInt(studentGrades.get("roll").toString()));
        bo.setGrade(studentGrades.get("grades").toString());
        return bo;
    }
}
