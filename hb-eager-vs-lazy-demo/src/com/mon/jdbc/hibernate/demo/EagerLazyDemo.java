package com.mon.jdbc.hibernate.demo;

import com.mon.jdbc.entity.Course;
import com.mon.jdbc.entity.Instructor;
import com.mon.jdbc.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerLazyDemo {
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
            Instructor instructor = session.get(Instructor.class, instructorId);

            System.out.println("mon: instructor: " + instructor);

            // get course for instructor
            System.out.println("mon: Courses: " + instructor.getCourses());

            // commit transaction
            session.getTransaction().commit();
            session.close();

            System.out.println("\nThe session is now closed\n");
            System.out.println("mon: Courses: " + instructor.getCourses());


            System.out.println("mon: Done!");
        }finally {
            // add clean up
            factory.close();
        }
    }
}
