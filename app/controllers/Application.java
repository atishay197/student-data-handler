package controllers;

import buisnessLogic.EncryptionDecryption.EncryptDecrypt;
import buisnessLogic.registerStudent;
import com.fasterxml.jackson.databind.JsonNode;
import objects.StudentBo;
import objects.StudentDto;
import org.json.JSONException;
import org.json.JSONObject;
import play.mvc.*;

public class Application extends Controller {


    public static Result index() {
        return redirect("/hello.html");
    }

    public static Result storeData(StudentDto studentDto) throws JSONException {
        System.out.println("Called storeData class");
        JsonNode obj = request().body().asJson();
        if(obj != null) {
            System.out.println("OBJ : " + obj.toString());
        }
        else {
            System.out.println("obj empty");
        }
        String student = "{\"name\" : \"Shyam\",\"college\" : \"NIT-K\",\"redirectURL\" : \"/DisplayRoll\"}";
        JSONObject studentJSON = new JSONObject(student);
        String redirectURL = studentJSON.get("redirectURL").toString();
        //StudentDto dto = new StudentDto(studentJSON);
        StudentDto dto = registerStudent.register(studentDto);
        String roll = registerStudent.getRoll(dto);
        return redirect(redirectURL+"/"+roll);
    }

    public static Result getData() {
        System.out.println("Called test class");
        return ok("testClass was called");
    }

    public static Result show(String roll) {
        response().setContentType("text/html");
        String content = "Your Roll number is : " + roll + "<br>This will not be displayed again";
        return ok(content);
    }
}
