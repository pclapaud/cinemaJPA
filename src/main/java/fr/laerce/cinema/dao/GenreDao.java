package fr.laerce.cinema.dao;

import fr.laerce.cinema.model.Genre;
import fr.laerce.cinema.model.User;
import org.springframework.data.repository.CrudRepository;


public interface GenreDao extends CrudRepository<Genre, Long> {


}