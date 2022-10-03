package ru.gb;

import org.hibernate.cfg.Configuration;
import ru.gb.dao.StudentDao;
import ru.gb.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Main {
    private static StudentDao studentOperations;
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        EntityManager em = emFactory.createEntityManager();
        studentOperations = new StudentDao(em);

        for (int i = 0; i < 1000; i++) {
            studentOperations.saveOrUpdate(new Student("Name" + i, "Surname" + i));
        }
        System.out.println(findStudent(25L));
        studentOperations.deleteById(26L);
        editStudent(findStudent(25L), "John", "Johnson");
        em.close();
    }

    private static void editStudent(Student student, String newName, String newSurname) {
        student.setName(newName);
        student.setSurname(newSurname);
        studentOperations.saveOrUpdate(student);
        Student editedStudent = findStudent(student.getId());
        System.out.println(editedStudent);
    }

    private static Student findStudent(Long id) {
        return studentOperations.findById(25L);
    }

}