package database;

import objects.StudentBo;
import play.db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by atishay197 on 6/28/16.
 */

public class DatabaseInsert{

    private static final String INSERT_STUDENT_DETAILS = "Insert into StudentDetails (`Name`, `College`) VALUES(?,?);";

    public static  void closeEverything(ResultSet rs, Connection con, PreparedStatement stmt){
        try {
            rs.close();
            stmt.close();
            con.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static StudentBo storeStudentData(StudentBo bo){
        Connection connection = DB.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement(INSERT_STUDENT_DETAILS, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, bo.getName());
            stmt.setString(2, bo.getCollege());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if(rs.next()) {
                int k = rs.getInt(1);
                bo.setRoll(Integer.toString(k));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }finally {
            closeEverything(rs,connection,stmt);
        }
        return bo;
    }
}
