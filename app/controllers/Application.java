package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import objects.TeacherDto;
import play.db.jpa.Transactional;
import objects.StudentDto;
import org.json.JSONObject;
import play.mvc.*;
import service.Student;
import service.Teacher;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
    public Result showAllData(String teacherId){
        List<StudentDto> studentDtoList= teacher.getAllStudentDetails(teacherId);
        if(studentDtoList == null){
            return badRequest("Invalid Teacher Id");
        }
        return ok(toJson(studentDtoList));
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
        return badRequest("JSON is empty");
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
    public Result storeGrade() {
        JsonNode obj = request().body().asJson();
        if(obj != null) {
            try {
                TeacherDto dto = new TeacherDto();
                dto = fromJson(obj,dto.getClass());
                dto = teacher.setStudentGrade(dto);
                if(dto == null){
                    return badRequest("Teacher ID is not valid");
                }
                else {
                    String content = toJson(dto).toString();
                    response().setContentType("application/json");
                    return ok(content);
                }
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
}
