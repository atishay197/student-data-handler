package controllers;

import buisnessLogic.EncryptionDecryption.EncryptDecrypt;
import buisnessLogic.registerStudent;
import com.fasterxml.jackson.databind.JsonNode;
import objects.StudentBo;
import objects.StudentDto;
import org.json.JSONException;
import org.json.JSONObject;
import play.mvc.*;

import java.util.Collection;

public class Application extends Controller {


    public static Result index() {
        return redirect("/hello.html");
    }

    public static Result storeData() throws JSONException {
        System.out.println("Called storeData class");
        JsonNode obj = request().body().asJson();
        if(obj != null)
            System.out.println("OBJ : " + obj.toString());
        else
            System.out.println("obj empty");
        String redirectURL = request().getHeader("referer");
        System.out.println("Request Header : " + redirectURL);
        String student = "{\"name\" : \"Shyam\",\"college\" : \"NIT-K\"}";
        JSONObject studentJSON = new JSONObject(student);
        StudentDto dto = new StudentDto(studentJSON);
        dto = registerStudent.register(dto);
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
