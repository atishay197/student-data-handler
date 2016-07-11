package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import exceptions.InvalidTeacherException;
import play.db.jpa.Transactional;
import objects.TeacherDto;
import objects.StudentDto;
import org.json.JSONObject;
import play.mvc.*;
import service.Student;
import service.Teacher;

import java.util.HashMap;
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
        try {
            List<StudentDto> studentDtoList = teacher.getAllStudentDetails(teacherId);
            if(studentDtoList == null){
                return badRequest("Invalid Teacher Id");
            }
            return ok(toJson(studentDtoList));
        }
        catch(InvalidTeacherException e){
            return badRequest("Teacher ID is invalid From Controller");
        }

    }

    @Transactional
    public Result storeData(){
        JsonNode obj = request().body().asJson();
        if(obj != null) {
            StudentDto dto = student.register(fromJson(obj,StudentDto.class));
            String roll = dto.getRoll();
            Map<String,String> m = new HashMap();
            m.put("roll",roll);
            String content = new JSONObject(m).toString();
            System.out.println(content);
            return ok(content);
        }
        return badRequest("JSON is empty");
    }

    @Transactional
    public Result getData() {
        JsonNode obj = request().body().asJson();
        if(obj != null) {
            try {
                StudentDto dto = fromJson(obj,StudentDto.class);
                dto = student.getStudentDetails(dto);
                String content = toJson(dto).toString();
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
                TeacherDto dto = fromJson(obj,TeacherDto.class);
                dto = teacher.setStudentGrade(dto);
                if(dto == null){
                    return badRequest("Teacher ID is not valid");
                }
                else {
                    String content = toJson(dto).toString();
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
