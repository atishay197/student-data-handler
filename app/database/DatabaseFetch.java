package database;

import objects.StudentBo;
import play.db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by atishay197 on 6/28/16.
 */
public class DatabaseFetch {

    private static final String FETCH_STUDENT_DETAILS = "SELECT College FROM StudentDetails WHERE ID = ? AND Name = ?;";
    private static final String FETCH_STUDENT_GRADE = "SELECT Grade FROM StudentGrades WHERE ID = ?;";

    public static  void closeEverything(ResultSet rs, Connection con,PreparedStatement stmt){
        try {
            rs.close();
            stmt.close();
            con.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static StudentBo getStudentData(StudentBo bo) throws SQLException{
        Connection connection = DB.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement(FETCH_STUDENT_DETAILS);
            stmt.setString(1, bo.getRoll());
            stmt.setString(2, bo.getName());
            rs = stmt.executeQuery();
            if(rs.next()) {
                bo.setCollege(rs.getString("College"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }finally {
            closeEverything(rs,connection,stmt);
        }
        connection = DB.getConnection();
        stmt = null;
        rs = null;
        try {
            stmt = connection.prepareStatement(FETCH_STUDENT_GRADE);
            stmt.setString(1, bo.getRoll());
            rs = stmt.executeQuery();
            if(rs.next()) {
                bo.setGrade(rs.getString("Grade"));
            }
            else{
                bo.setGrade("Not Graded");
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
