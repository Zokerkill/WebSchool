package jsonbuilders;

import entity.Subject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class SubjectJsonBuilder {
    public JsonObject createJsonSubject(Subject subject){
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("id", subject.getId())
          .add("name", subject.getName())
          .add("teacher", subject.getTeacher());
        return job.build();
    } 
}
