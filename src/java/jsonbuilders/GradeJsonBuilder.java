package jsonbuilders;

import entity.Grade;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class GradeJsonBuilder {
    public JsonObject createJsonGrade(Grade grade){
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("id", grade.getId())
        .add("name", grade.getName())
        .add("text", grade.getText());
        return job.build();
    }
}
