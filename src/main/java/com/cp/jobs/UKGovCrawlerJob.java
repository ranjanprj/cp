/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cp.jobs;

import com.cp.data.crawlers.UKGovCrawler;
import com.cp.data.processors.UKGovProcessor;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import utils.PUFactory;

/**
 *
 * @author PRanjan
 */
public class UKGovCrawlerJob implements Runnable{
  private Random rand;
    private void loopAndStoreData() throws IOException, InterruptedException, ParseException{
        EntityManager em = PUFactory.getEM();
        List<String> desiredPositions  = em.createQuery("SELECT t.desiredPosition FROM CPUserProfile t", String.class).getResultList();
        
        for(String desiredPos : desiredPositions){
            System.out.println("=======================================");
            System.out.println("Finding jobs for " + desiredPos);
            
            UKGovCrawler.crawl(desiredPos, 50);
            Thread.sleep(rand.nextInt(10) + 10 * 1000);            
        }
        
        // Now process all the payload
        UKGovProcessor.Processor();
    }

    @Override
    public void run() {
        try {
            loopAndStoreData();
        } catch (IOException ex) {
            Logger.getLogger(UKGovCrawlerJob.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(UKGovCrawlerJob.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(UKGovCrawlerJob.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
