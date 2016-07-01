package objects;

import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by atishay197 on 6/28/16.
 */
@Entity
@Table(name = "StudentDetails")
public class StudentDetailsBo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int roll;

    @Column(name = "Name")
    private String name;

    @Column(name = "College")
    private String college;

    public void setName(String name){this.name = name;}
    public void setRoll(int roll){this.roll = roll;}
    public void setCollege(String college){this.college = college;}

    public String getName(){return name;}
    public int getRoll(){return roll;}
    public String getRollString(){return Integer.toString(roll);}
    public String getCollege(){return college;}

    public static StudentDetailsBo copyDtoToBo(StudentDto dto){
        StudentDetailsBo bo = new StudentDetailsBo();
        if(dto.getName() != null) {
            bo.setName(dto.getName());
        }
        if(dto.getCollege() != null) {
            bo.setCollege(dto.getCollege());
        }
        if(dto.getRoll() != null) {
            bo.setRoll(Integer.parseInt(dto.getRoll()));
        }
        return bo;
    }

}
