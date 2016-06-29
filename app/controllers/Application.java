package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import objects.StudentDto;
import org.json.JSONException;
import org.json.JSONObject;
import play.mvc.*;


public class Application extends Controller {


    public static Result index() {
        return redirect("/hello.html");
    }

    public static Result storeData() throws JSONException {
        JsonNode obj = request().body().asJson();
        String redirectURL = request().getHeader("referer");
        JSONObject requestJSON;
        if(obj != null) {
            requestJSON = new JSONObject(obj.toString());
            StudentDto dto = new StudentDto(requestJSON);
            dto = registerStudent.register(dto);
            String roll = dto.getRoll();
            return redirect(redirectURL+"/"+roll);
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

    public static Result show(String roll) {
        response().setContentType("text/html");
        String content = "Your Roll number is : " + roll + "<br>This will not be displayed again";
        return ok(content);
    }
}
