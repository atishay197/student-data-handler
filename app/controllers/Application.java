package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONObject;
import play.data.DynamicForm;
import play.data.Form;
import play.db.DB;
import play.mvc.*;


import java.sql.Connection;
import java.sql.PreparedStatement;

public class Application extends Controller {


    private static final String INSERT_STUDENT_DETAILS = "Insert into StudentDetails (`Name` , `Roll` , `College`) VALUES(?,?,?);";

    public static Result index() {
        return redirect("/hello.html");
    }
    public static Result storeData() {
        System.out.println("Called storeData class");
        JsonNode recievedData = request().body().asJson();
        if (recievedData == null) {
            return badRequest("Expceting some data");
        }
        else{
            String name = recievedData.findPath("name").asText();
            String roll = recievedData.findPath("roll").asText();
            String college = recievedData.findPath("college").asText();
            String response = "Name : " + name + " Roll : " + roll + " College : " + college;
            String redirectURL = recievedData.findPath("redirect").asText();
            System.out.println("Recieved Data : " + response);
            System.out.println("Recieved Data : " + redirectURL + " ");
            Connection connection = DB.getConnection();
            try {
                PreparedStatement stmt = connection.prepareStatement(INSERT_STUDENT_DETAILS);
                stmt.setString(1, name);
                stmt.setString(2, roll);
                stmt.setString(3, college);
                stmt.executeUpdate();
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return redirect(redirectURL);
        }
    }

    public static Result getData() {
        System.out.println("Called test class");
        return ok("testClass was called");
    }

}
