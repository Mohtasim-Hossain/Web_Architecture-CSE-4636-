package com.example.assignment_4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Startup
@Path("/students ")
public class SingletonStudent {

    @PersistenceContext(unitName = "student-persistence-unit")
    private EntityManager entityManager;

    @GET
    @Path("/highestcgpa")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentWithHighestCGPA() {
        TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s ORDER BY s.cgpa DESC", Student.class);
        query.setMaxResults(1);
        Student student = query.getSingleResult();
        JsonObject result = Json.createObjectBuilder()
                .add("id", student.getStudentId())
                .add("name", student.getName())
                .build();
        return Response.ok(result).build();
    }
}

