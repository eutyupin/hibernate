package ru.gb.dao;

import ru.gb.entity.Student;

import javax.persistence.EntityManager;
import java.util.List;

public class StudentDao {
        private EntityManager entityManager;

        public StudentDao(EntityManager entityManager) {
            this.entityManager = entityManager;
        }

        public List<Student> findAll() {
            List<Student> products;
            entityManager.getTransaction().begin();
            products = entityManager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
            entityManager.getTransaction().commit();
            return products;
        }

        public Student findById(Long id) {
            Student student = entityManager.find(Student.class, id);
            return student;
        }

        public void deleteById(Long id) {
            entityManager.getTransaction().begin();
            Student student = entityManager.find(Student.class, id);
            entityManager.remove(student);
            entityManager.getTransaction().commit();
        }

        public void saveOrUpdate(Student student) {
            entityManager.getTransaction().begin();
            entityManager.merge(student);
            entityManager.getTransaction().commit();
        }
}
