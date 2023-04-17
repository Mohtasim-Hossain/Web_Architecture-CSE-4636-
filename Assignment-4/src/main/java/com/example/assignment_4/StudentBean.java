package com.example.assignment_4;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/students")
public class StudentBean {

    @PersistenceContext(unitName = "myPersistenceUnit")
    private EntityManager entityManager;


    //end point for adding new student to the database
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addStudent(@QueryParam("id") int id,
                               @QueryParam("name") String name,
                               @QueryParam("semester") int semester,
                               @QueryParam("cgpa") float cgpa) {
        Student student = new Student(id, name, semester, cgpa);
        entityManager.persist(student);
        return Response.status(Response.Status.CREATED).build();
    }

// endpoint for getting student info with a particular id
    @GET
    @Path("/{id}")
    public String getStudentName(@PathParam("id") int id) {
        Student student = entityManager.find(Student.class, id);
        if (student == null) {
            throw new NotFoundException("Student with ID " + id + " not found");
        }
        return student.getName();
    }

// end point for retrieving the student info with the higher cgpa

    @GET
    @Path("/comp_CG")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHigherCGPA(
            @QueryParam("studentId1") String studentId1,
            @QueryParam("studentId2") String studentId2) {

        Student student1 = entityManager.find(Student.class, studentId1);
        Student student2 = entityManager.find(Student.class, studentId2);

        if (student1 == null || student2 == null) {
            throw new NotFoundException("Student not found");
        }
        Student student = student1.getCgpa() >= student2.getCgpa() ? student1 : student2;

        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("name", student.getName());
        builder.add("id", student.getStudentId());
        builder.add("cgpa", student.getCgpa());

        JsonObject jsonObject = builder.build();

        return Response.ok(jsonObject).build();
    }

// endpoint for updating the student name with the given name
    private EntityManagerFactory emf;
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateStudentName(
            @PathParam("id") int id,
            @FormParam("name") String name
    ) {
        EntityManager em = emf.createEntityManager();
        Student student = em.find(Student.class, id);
        if (student == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        em.getTransaction().begin();
        student.setName(name);
        em.getTransaction().commit();
        em.close();
        return Response.ok().build();
    }


}

