package gui;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMF {

    private static EntityManagerFactory emf;


    public static void emfInitialize() {
        emf = Persistence.createEntityManagerFactory("GUIGradleHibernate");
    }


    public static void emfDestroy() {
        emf.close();
    }
    public static boolean isEmfInitialized(){
        if(emf != null){
            return true;
        }else return false;
    }

    public static EntityManager createEntityManager() {
        if (emf == null) {
            throw new IllegalStateException("EMF is not initialized yet.");
        }

        return emf.createEntityManager();
    }

}