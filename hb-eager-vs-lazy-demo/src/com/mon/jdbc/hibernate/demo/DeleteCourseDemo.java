package com.mon.jdbc.hibernate.demo;

import com.mon.jdbc.entity.Course;
import com.mon.jdbc.entity.Instructor;
import com.mon.jdbc.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseDemo {
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

            // get a course
            int courseId = 12;
            Course course = session.get(Course.class, courseId);

            // delete course
            System.out.println("Deleting course: " + course);
            session.delete(course);

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
