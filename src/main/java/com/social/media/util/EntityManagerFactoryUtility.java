package com.social.media.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;

public class EntityManagerFactoryUtility {
    public static EntityManager createEntityManger(){
        HashMap props= new HashMap();
        props.put("packagesToScan", "com.social.media.model");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit", props);
        return emf.createEntityManager();
    }

}
