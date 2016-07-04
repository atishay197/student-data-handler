package objects;

import javax.persistence.*;

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

    private String grade;

    public void setName(String name){this.name = name;}
    public void setRoll(int roll){this.roll = roll;}
    public void setCollege(String college){this.college = college;}
    public void setGrade(String grade){this.grade = grade;}

    public String getName(){return name;}
    public int getRoll(){return roll;}
    public String getRollString(){return Integer.toString(roll);}
    public String getCollege(){return college;}
    public String getGrade(){return grade;}

}
