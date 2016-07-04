package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import play.db.jpa.Transactional;
import objects.StudentDto;
import org.json.JSONException;
import org.json.JSONObject;
import play.mvc.*;
import service.Student;
import views.html.roll;
import views.html.studentDetail;

import java.util.HashMap;
import java.util.Map;

import static play.libs.Json.fromJson;
import static play.libs.Json.toJson;

public class Application extends Controller {

    private final Student student;
    @Inject
    public Application(Student student) {
        this.student = student;
    }

    public Result index() {
        return redirect("/home");
    }

    @Transactional
    public Result showAllData(){
        return ok(toJson(student.getAllStudentDetails()));
    }

    public Result storeData() throws JSONException {
        JsonNode obj = request().body().asJson();
        String redirect = request().getHeader("referer");
        if(obj != null) {
            StudentDto dto = new StudentDto();
            dto = fromJson(obj,dto.getClass());
            //dto = student.register(dto);
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

    public Result getData() {
        JsonNode obj = request().body().asJson();

        if(obj != null) {
            try {
                StudentDto dto = new StudentDto();
                dto = fromJson(obj,dto.getClass());
                //dto = student.getStudentDetails(dto);
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


    public Result submitGrades() {
        System.out.println("Called test class");
        return ok("testClass was called");
    }

    public Result showRoll(String rollNo){
        String returnURL = request().getHeader("referer");
        return ok(roll.render(rollNo,returnURL));
    }

    public Result showDetails(){
        Map<String,String[]> m = Controller.request().queryString();
        Map<String,String> n = new HashMap();
        for (Map.Entry<String, String[]> entry: m.entrySet()) {
            n.put(entry.getKey(), entry.getValue()[0]);
        }
        JSONObject obj = new JSONObject(n);
        String redirect = request().getHeader("referer");
        if(obj != null) {
            try { return ok(studentDetail.render(obj.toString(),redirect)); }
            catch(Exception e){
                e.printStackTrace();
                return badRequest("Rendering Error");
            }
        }
        else { return badRequest("JSON is empty"); }
    }
}
