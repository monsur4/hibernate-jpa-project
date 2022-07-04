package com.mon.jdbc.hibernate.demo;

import com.mon.jdbc.entity.Instructor;
import com.mon.jdbc.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetailDemo {
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
            int instructorDetailId = 29;

            // start the transaction
            session.beginTransaction();

            InstructorDetail instructorDetail = session.get(InstructorDetail.class, instructorDetailId);

            System.out.println("instructorDetail: " + instructorDetail);

            System.out.println("the associated instructor: " + instructorDetail.getInstructor());

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
