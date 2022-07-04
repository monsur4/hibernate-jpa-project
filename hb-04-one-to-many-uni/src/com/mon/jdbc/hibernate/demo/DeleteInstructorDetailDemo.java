package com.mon.jdbc.hibernate.demo;

import com.mon.jdbc.entity.Instructor;
import com.mon.jdbc.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetailDemo {
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
            int instructorDetailId =3;

            // start the transaction
            session.beginTransaction();

            InstructorDetail instructorDetail = session.get(InstructorDetail.class, instructorDetailId);

            System.out.println("Found instructor: " + instructorDetail);

            System.out.println("the associated instructor: " + instructorDetail.getInstructor());

            System.out.println("Deleting instructor detail: "+ instructorDetail);
            // remove associated object reference
            // break bi-directional link
            instructorDetail.getInstructor().setInstructorDetail(null);
            session.delete(instructorDetail);

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
