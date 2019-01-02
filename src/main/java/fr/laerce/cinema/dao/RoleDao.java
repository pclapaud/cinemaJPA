package fr.laerce.cinema.dao;

import fr.laerce.cinema.model.Film;
import fr.laerce.cinema.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface RoleDao extends CrudRepository<Role, Long> {


}