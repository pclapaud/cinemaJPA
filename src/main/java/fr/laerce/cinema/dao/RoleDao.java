package fr.laerce.cinema.dao;

import fr.laerce.cinema.model.Film;
import fr.laerce.cinema.model.Role;
import fr.laerce.cinema.model.RoleId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface RoleDao extends CrudRepository<Role, RoleId> {

    List<Role> findAllByFilm_Id(Film film);
}