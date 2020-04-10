package br.uece.eesdevop.bancodedados.example;

import br.uece.eesdevop.bancodedados.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class HibernateExample {

    public static void main(String[] args) {
        try {
            EntityManagerFactory factory = HibernateUtil.INSTANCE.getEntityManagerFactory();
            EntityManager manager = factory.createEntityManager();
            if (manager.isOpen()) {
                System.out.println("Connected!");
                manager.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to open a Hibernate session: " + e.getMessage());
        }
    }

}
