package fr.laerce.cinema.dao;

import fr.laerce.cinema.model.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

@Component
public class PersonsDao {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void save(Persons p){
        entityManager.persist(p);
    }

    public List<Persons> getAll(){
        Query query = entityManager.createQuery("Select p from Persons p");
        return query.getResultList();
    }

    public Persons getById(BigInteger id){
        Persons retVal = null;
        Query query = entityManager.createQuery("select p from Persons p where p.id = :id");
        query.setParameter("id", id);
        List<Persons> persons = query.getResultList();
        if(!persons.isEmpty()){
            retVal = persons.get(0);
        }
        return retVal;
    }
}
