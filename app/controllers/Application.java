package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import objects.StudentDto;
import org.json.JSONException;
import org.json.JSONObject;
import play.mvc.*;
import service.Student;
import views.html.roll;

import java.util.HashMap;
import java.util.Map;


public class Application extends Controller {


    public static Result index() {
        return redirect("/home");
    }

    public static Result storeData() throws JSONException {
        JsonNode obj = request().body().asJson();
        String redirect = request().getHeader("referer");
        JSONObject requestJSON;
        if(obj != null) {
            requestJSON = new JSONObject(obj.toString());
            StudentDto dto = new StudentDto(requestJSON);
            dto = Student.register(dto);
            String roll = dto.getRoll();
            Map<String,String> m = new HashMap();
            m.put("finalURL",redirect+"/"+roll);
            m.put("response",roll);
            String content = new JSONObject(m).toString();
            System.out.println(content);
            response().setContentType("application/json");
            return ok(content);
        }
        else{
            System.out.println("Post Request is empty");
            return badRequest("JSON is empty");
        }
    }

    public static Result getData() {
        System.out.println("Called test class");
        return ok("testClass was called");
    }
    public static Result show(String rollNo){
        String returnURL = request().getHeader("referer");
        return ok(roll.render(rollNo,returnURL));
    }
}
