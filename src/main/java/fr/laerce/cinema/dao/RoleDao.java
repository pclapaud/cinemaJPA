package fr.laerce.cinema.dao;

import fr.laerce.cinema.model.Personne;
import fr.laerce.cinema.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

@Component
public class RoleDao {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void save(Role r){
        entityManager.persist(r);
    }

    public List<Role> getAll(){
        Query query = entityManager.createQuery("Select r from Role r");
        return query.getResultList();
    }

    public Role getById(BigInteger id){
        Role retVal = null;
        Query query = entityManager.createQuery("select r from Role r where r.id = :id");
        query.setParameter("id", id);
        List<Role> rols = query.getResultList();
        if(!rols.isEmpty()){
            retVal = rols.get(0);
        }
        return retVal;
    }
}
