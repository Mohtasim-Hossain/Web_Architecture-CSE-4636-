package com.example.assignment_4;

import java.util.List;
import org.hibernate.Session;

public class StudentDAO {

    public List<Student> getAllStudents() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> students = session.createQuery("FROM Student ", Student.class).list();
        session.close();
        return students;
    }
}
