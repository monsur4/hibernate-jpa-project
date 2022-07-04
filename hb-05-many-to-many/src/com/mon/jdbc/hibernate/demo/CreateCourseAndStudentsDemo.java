package com.mon.jdbc.hibernate.demo;

import com.mon.jdbc.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentsDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        System.out.println();

        try {
            // create the objects

            // start the transaction
            session.beginTransaction();

            // create a new course
            Course tempCourse = new Course("Pacman - How to score one million points");

            // save the course
            System.out.println("\nSaving the course...");
            session.save(tempCourse);
            System.out.println("\nSaved the course: " + tempCourse);

            // create the students
            Student tempStudent1 = new Student("John", "Doe", "john@me.com");
            Student tempStudent2 = new Student("Mary", "Public", "mary@me.com");

            // add students to the course
            tempCourse.addStudent(tempStudent1);
            tempCourse.addStudent(tempStudent2);

            // save the students
            System.out.println("\nSaving students...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            System.out.println("\nSaved students: " + tempCourse.getStudents());

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
