package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import objects.StudentDto;
import org.json.JSONException;
import org.json.JSONObject;
import play.mvc.*;
import service.Student;
import views.html.roll;
import views.html.studentDetail;

import java.util.HashMap;
import java.util.Map;


public class Application extends Controller {


    public static Result index() {
        return redirect("/home");
    }

    public static Result storeData() throws JSONException {
        JsonNode obj = request().body().asJson();
        String redirect = request().getHeader("referer");
        if(obj != null) {
            JSONObject requestJSON = new JSONObject(obj.toString());
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
            return badRequest("JSON is empty");
        }
    }

    public static Result getData() {
        JsonNode obj = request().body().asJson();
        if(obj != null) {
            try {
                JSONObject requestJSON = new JSONObject(obj.toString());
                StudentDto dto = new StudentDto(requestJSON);
                dto = Student.getStudentDetails(dto);
                JSONObject dtoJSON = StudentDto.dtoToJSON(dto);
                Map<String, String> m = new HashMap();
                m.put("finalURL", "/showDetails");
                m.put("response", dtoJSON.toString());
                String content = new JSONObject(m).toString();
                System.out.println(content);
                response().setContentType("application/json");
                return ok(content);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            return badRequest("JSON is empty");
        }
        return ok("testClass was called");
    }


    public static Result submitGrades() {
        System.out.println("Called test class");
        return ok("testClass was called");
    }

    public static Result showRoll(String rollNo){
        String returnURL = request().getHeader("referer");
        return ok(roll.render(rollNo,returnURL));
    }

    public static Result showDetails(){
        Map<String,String[]> m = Controller.request().queryString();
        Map<String,String> n = new HashMap();
        for (Map.Entry<String, String[]> entry: m.entrySet()) {
            n.put(entry.getKey(), entry.getValue()[0]);
        }
        JSONObject obj = new JSONObject(n);
        System.out.println(obj.toString());
        String redirect = request().getHeader("referer");
        if(obj != null) {
            try {
                return ok(studentDetail.render(obj.toString(),redirect));
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            return badRequest("JSON is empty");
        }
        return ok("testClass was called");
    }
}
