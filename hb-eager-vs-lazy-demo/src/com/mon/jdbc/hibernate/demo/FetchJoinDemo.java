package com.mon.jdbc.hibernate.demo;

import com.mon.jdbc.entity.Course;
import com.mon.jdbc.entity.Instructor;
import com.mon.jdbc.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoinDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        System.out.println();

        try {
            // create the objects

            // start the transaction
            session.beginTransaction();

            // get the instructor from the database
            int instructorId = 2;

            Query<Instructor> query = session.createQuery("select i from Instructor i " +
                                                    "JOIN FETCH i.courses " +
                                                    "where i.id=:theInstructorId", Instructor.class);

            // set parameter of query
            query.setParameter("theInstructorId", instructorId);

            // execute query and get instructor
            Instructor instructor = query.getSingleResult();

            System.out.println("mon: instructor: " + instructor);

            // commit transaction
            session.getTransaction().commit();
            session.close();

            System.out.println("\nThe session is now closed\n");

            // Option 2: loading the data using HQl query
            // get course for instructor
            System.out.println("mon: Courses: " + instructor.getCourses());


            System.out.println("mon: Done!");
        }finally {
            // add clean up
            factory.close();
        }
    }
}
