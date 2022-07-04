package com.mon.jdbc.hibernate.demo;

import com.mon.jdbc.entity.Course;
import com.mon.jdbc.entity.Instructor;
import com.mon.jdbc.entity.InstructorDetail;
import com.mon.jdbc.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndReviewsDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        System.out.println();

        try {
            // create the objects

            // start the transaction
            session.beginTransaction();

            // create a new course
            Course tempCourse = new Course("Pacman - How to score one million points");

            // add some reviews
            tempCourse.addReview(new Review("Great course... loved it!"));
            tempCourse.addReview(new Review("Cool course, job well done"));
            tempCourse.addReview(new Review("What a dump course, you are an idiot!"));

            // save the course, leveraging cascadeAll
            System.out.println("Saving the course");
            System.out.println(tempCourse);
            System.out.println(tempCourse.getReviews());
            session.save(tempCourse);

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
