package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.mvc.*;
import static database.DatabaseInsert.storeStudentData;

public class Application extends Controller {


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
            storeStudentData(name,roll,college);
            return redirect(redirectURL);
        }
    }

    public static Result getData() {
        System.out.println("Called test class");
        return ok("testClass was called");
    }

}
