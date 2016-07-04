package objects;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by atishay197 on 7/4/16.
 */
public class TeacherDto {
    private String teacherId;
    private int roll;
    private String grade;

    public int getRoll(){return roll;}
    public String getGrade(){return grade;}
    public String getTeacherId(){return teacherId;}

}
