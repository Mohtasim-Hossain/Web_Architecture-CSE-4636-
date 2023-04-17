package com.example.assignment_4;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.sql.Connection;
import java.util.List;

@Path("/hello-world")
public class HelloResource {
    Connection a =  Database.getConnection();
    @GET
    @Produces("text/plain")
    public String hello() {
       return "Hello, world!";
    }
}