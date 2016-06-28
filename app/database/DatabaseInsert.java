package database;

import objects.StudentBo;
import play.db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by atishay197 on 6/28/16.
 */

public class DatabaseInsert{

    private static final String INSERT_STUDENT_DETAILS = "Insert into StudentDetails (`Name` , `Roll` , `College`) VALUES(?,?,?);";

    public static void storeStudentData(StudentBo bo){
        Connection connection = DB.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(INSERT_STUDENT_DETAILS);
            stmt.setString(1, bo.getName());
            stmt.setString(2, bo.getRoll());
            stmt.setString(3, bo.getCollege());
            stmt.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
