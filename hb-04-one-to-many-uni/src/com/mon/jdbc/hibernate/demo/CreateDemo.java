package com.mon.jdbc.hibernate.demo;

import com.mon.jdbc.entity.Instructor;
import com.mon.jdbc.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        System.out.println();

        try {
            // create the objects
            /*Instructor instructor = new Instructor("Chad", "Darby", "darby@me.com");
            InstructorDetail instructorDetail = new InstructorDetail("http://www.darby.com/youtube", "Loves to code");
            instructor.setInstructorDetail(instructorDetail);*/

            Instructor instructor = new Instructor("Madhu", "Patel", "patel@me.com");
            InstructorDetail instructorDetail = new InstructorDetail("http://www.patel.com/youtube", "Guitar");
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
            factory.close();
        }
    }
}
