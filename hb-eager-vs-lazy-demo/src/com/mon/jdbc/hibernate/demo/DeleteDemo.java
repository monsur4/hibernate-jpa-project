package com.mon.jdbc.hibernate.demo;

import com.mon.jdbc.entity.Instructor;
import com.mon.jdbc.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        System.out.println();

        try {
            // get the instructor by the primary key
            int instructorId = 1;

            // start the transaction
            session.beginTransaction();

            Instructor instructor = session.get(Instructor.class, instructorId);

            System.out.println("Found instructor: " + instructor);

            // delete the instructor
            if(instructor != null){
                System.out.println("Deleting: " + instructor);

                // will also delete the instructor details object
                session.delete(instructor);
            }


            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }finally {
            factory.close();
        }
    }
}
