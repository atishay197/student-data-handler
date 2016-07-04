package objects;

import javax.persistence.*;

/**
 * Created by atishay197 on 7/1/16.
 */

@Entity
@Table(name = "StudentGrades")
public class StudentGradeBo {

    @Id
    @Column(name = "ID")
    private int roll;

    @Column(name = "Grade")
    private String grade;

    public String getGrade(){return grade;}
    public int getRoll(){return roll;}

    public void setGrade(String grade){this.grade = grade;}
    public void setRoll(int roll){this.roll = roll;}
}
