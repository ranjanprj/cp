/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cp.dto;

import com.cp.entities.CPUserProfile;
import com.cp.entities.UserProfileJpaController;
import java.util.List;
import javax.persistence.EntityManager;
import utils.PUFactory;

/**
 *
 * @author PRanjan3
 */
public class UserProfileService {

    private UserProfileJpaController userProfileJpaController;

    public UserProfileService() {
        userProfileJpaController = new UserProfileJpaController(PUFactory.getEMF());
    }

    public CPUserProfile getUserProfile(String emailAddress) {
        CPUserProfile cpup = userProfileJpaController.getEntityManager().createNamedQuery("up.findByEmailAddress", CPUserProfile.class).setParameter("emailAddress", emailAddress).getSingleResult();
        return cpup;
    }

    public List<String[]> getUkGovDesiredSkillsRating() {
        EntityManager em = PUFactory.getEM();
        em.getTransaction().begin();
        em.createNativeQuery("create or replace view skill_count_view as \n" +
"SELECT \n" +
"word, count(*) as cnt\n" +
"FROM ( \n" +
"  SELECT regexp_split_to_table(jobdescription, ' ') as word\n" +
"  FROM ukgovjob  \n" +
") t\n" +
"\n" +
"WHERE lower(word) in \n" +
"(SELECT regexp_split_to_table('r python c++ c sql sas perl java nodejs ocaml golang matlab mathematica tensorflow', ' ') as word)\n" +
"GROUP BY word order by cnt desc").executeUpdate();
        em.getTransaction().commit();
        
        List<String[]> skillCount =  PUFactory.getEM().createNativeQuery("SELECT word,cnt FROM skill_count_view").getResultList();
        return skillCount;
    }

    public List<Double[]> getAvgUKGovSalary() {
        List<Double[]> avgSal = userProfileJpaController.getEntityManager().createNativeQuery("SELECT \n"
                + "	avg(starting_salary) as avg_strt_sal,\n"
                + "	avg(ending_salary) as avg_end_sal	\n"
                + " FROM\n"
                + "(\n"
                + "SELECT \n"
                + "regexp_replace(sal[1], ',' ,'')::float as starting_salary,\n"
                + "regexp_replace(sal[2], ',' ,'')::float as ending_salary\n"
                + "\n"
                + "FROM (\n"
                + "  \n"
                + "  SELECT string_to_array(\n"
                + "		unnest(\n"
                + "\n"
                + "			\n"
                + "			regexp_matches(salary, '\\d{2}\\,*\\d{3}\\.*\\d{2}\\s-\\s\\d{2}\\,*\\d{3}\\.*\\d{2}')\n"
                + "			\n"
                + "		)\n"
                + "		,' - ')\n"
                + "\n"
                + "		as sal\n"
                + "  FROM ukgovjob  \n"
                + ") t\n"
                + ")u").getResultList();

        return avgSal;
    }

    public static void main(String[] args) {
        for (Object[] obj : new UserProfileService().getAvgUKGovSalary()) {
            System.out.println(obj[0] + " " + obj[1]);
        }
        
        System.out.println(new UserProfileService().getUkGovDesiredSkillsRating());
    }
}
