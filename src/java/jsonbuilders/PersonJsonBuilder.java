package jsonbuilders;

import entity.Person;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class PersonJsonBuilder {
    public JsonObject createJsonPerson(Person person){
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("id", person.getId())
          .add("name", person.getName());
        return job.build();
    }    
}
