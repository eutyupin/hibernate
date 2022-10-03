package ru.gb;

import org.hibernate.cfg.Configuration;
import ru.gb.dao.StudentDao;
import ru.gb.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        EntityManager em = emFactory.createEntityManager();
        StudentDao studentOperations = new StudentDao(em);

        for (int i = 0; i < 1000; i++) {
            studentOperations.saveOrUpdate(new Student("Name" + i, "Surname" + i));
        }

        for (Student student : studentOperations.findAll()) {
            System.out.println(student);
        }
        em.close();
    }
}