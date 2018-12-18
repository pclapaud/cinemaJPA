package fr.laerce.cinema.dao;

import fr.laerce.cinema.model.Personne;
import org.springframework.data.repository.CrudRepository;


public interface PersonneDao extends CrudRepository<Personne, Long> {

}
