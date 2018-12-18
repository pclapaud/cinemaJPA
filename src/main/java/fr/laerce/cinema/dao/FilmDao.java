package fr.laerce.cinema.dao;

import fr.laerce.cinema.model.Film;
import fr.laerce.cinema.model.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;
@Component
public class FilmDao {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void save(Film f){
        entityManager.persist(f);
    }

    public List<Film> getAll(){
        Query query = entityManager.createQuery("Select f from Film f");
        return query.getResultList();
    }

    public Film getById(long id){
        Film retVal = null;
        Query query = entityManager.createQuery("select f from Film f where f.id = :id");
        query.setParameter("id", id);
        List<Film> films = query.getResultList();
        if(!films.isEmpty()){
            retVal = films.get(0);
        }
        return retVal;
    }
    public String getFilename(){
        Query query = entityManager.createQuery("Select max(f.imagePath) from Film f");
        String filename = (String)query.getResultList().get(0);
        filename = filename.substring(1);
        Integer tempo = Integer.parseInt(filename.substring(0,filename.lastIndexOf('.')))+1;
        String resultat =String.valueOf(tempo);
        for(int i = String.valueOf(tempo).length(); i<4;i++){
            resultat = 0+resultat;
        }

        return "f"+resultat+".jpg";
    }
}
