package com.mon.jdbc.hibernate.demo;

import com.mon.jdbc.entity.Course;
import com.mon.jdbc.entity.Instructor;
import com.mon.jdbc.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {
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

            Instructor instructor = new Instructor("Susan", "Public", "public@me.com");
            InstructorDetail instructorDetail = new InstructorDetail("http://www.youtube.com", "Video games");
            instructor.setInstructorDetail(instructorDetail);

            // start the transaction
            session.beginTransaction();

            // save the object
            // this will also save the instructor detail because of the CascadeType.ALL
            System.out.println("Saving instructor: " + instructor);
            session.save(instructor);


            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }finally {
            // add clean up
            session.close();
            factory.close();
        }
    }
}
