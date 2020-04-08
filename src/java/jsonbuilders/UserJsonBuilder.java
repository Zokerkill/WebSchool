package jsonbuilders;

import entity.User;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import util.RoleManager;

public class UserJsonBuilder {

    public String createJsonUser(User user) throws IOException {
        String userJson = null;
        RoleManager rm = new RoleManager();
        PersonJsonBuilder personJsonBuilder = new PersonJsonBuilder();
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("id", user.getId())
            .add("login", user.getLogin())
            .add("role", rm.getTopRoleName(user))
            .add("person", personJsonBuilder.createJsonPerson(user.getPerson()));
        String jserJson = null;
        try (Writer writer = new StringWriter()) {
            Json.createWriter(writer).write(job.build());
            userJson = writer.toString();
        }
        return userJson;
    }

    public JsonObject createJsonObjectUser(User user) throws IOException {
        String userJson = null;
        RoleManager rm = new RoleManager();
        PersonJsonBuilder readerJsonBuilder = new PersonJsonBuilder();
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("id", user.getId())
            .add("login", user.getLogin())
            .add("role", rm.getTopRoleName(user))
            .add("person", readerJsonBuilder.createJsonPerson(user.getPerson()));

        return job.build();
    }
}
