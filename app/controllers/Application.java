package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import play.db.jpa.Transactional;
import objects.StudentDto;
import org.json.JSONObject;
import play.mvc.*;
import service.Student;
import service.Teacher;
import views.html.studentDetail;

import java.util.HashMap;
import java.util.Map;

import static play.libs.Json.fromJson;
import static play.libs.Json.toJson;

public class Application extends Controller {

    private final Student student;
    private final Teacher teacher;
    @Inject
    public Application(Student student,Teacher teacher) {
        this.student = student;
        this.teacher = teacher;
    }

    public Result index() {
        return redirect("/home");
    }

    @Transactional
    public Result showAllData(){
        return ok(toJson(teacher.getAllStudentDetails()));
    }

    @Transactional
    public Result storeData(){
        JsonNode obj = request().body().asJson();
        if(obj != null) {
            StudentDto dto = new StudentDto();
            dto = student.register(fromJson(obj,dto.getClass()));
            String roll = dto.getRoll();
            Map<String,String> m = new HashMap();
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

    @Transactional
    public Result getData() {
        JsonNode obj = request().body().asJson();
        if(obj != null) {
            try {
                StudentDto dto = new StudentDto();
                dto = fromJson(obj,dto.getClass());
                dto = student.getStudentDetails(dto);
                String content = toJson(dto).toString();
                response().setContentType("application/json");
                return ok(content);
            }
            catch(Exception e){
                e.printStackTrace();
                return badRequest("Error in Getting Student Details");
            }
        }
        else{
            return badRequest("JSON is empty");
        }
    }


    @Transactional
    public Result submitGrades() {
        System.out.println("Called test class");
        return ok("testClass was called");
    }


    @Transactional
    public Result showDetails(){
        Map<String,String[]> m = Controller.request().queryString();
        Map<String,String> n = new HashMap();
        for (Map.Entry<String, String[]> entry: m.entrySet()) {
            n.put(entry.getKey(), entry.getValue()[0]);
        }
        JSONObject obj = new JSONObject(n);
        System.out.println("Recieved to Show" + obj.toString());
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
