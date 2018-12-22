package fr.laerce.cinema.dao;

import fr.laerce.cinema.model.Role;
import fr.laerce.cinema.model.RoleId;
import org.springframework.data.repository.CrudRepository;


public interface RoleDao extends CrudRepository<Role, RoleId> {

}