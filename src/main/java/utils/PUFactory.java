/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author PRanjan3
 */
public class PUFactory {
//    private static final String PU = "CP_PU";
      private static final String PU = "CP_PU_AWS";
    
    private PUFactory(){
        
    }
    
    public static EntityManager getEM(){
           return Persistence.createEntityManagerFactory(PU).createEntityManager();
    }
    
    public static EntityManagerFactory getEMF(){
        return Persistence.createEntityManagerFactory(PU);
    }
    
}
