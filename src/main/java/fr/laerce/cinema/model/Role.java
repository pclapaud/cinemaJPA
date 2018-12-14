package fr.laerce.cinema.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
//si le nom de la table est diff que la class
@Table(name="play")
public class Role {
    private long id;
    private long personneId;
    private long filmId;
    private Integer ordre;
    private String nom;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "person_id", nullable = false)
    public long getPersonneId() {
        return personneId;
    }
    public void setPersonneId(long personneId) {
        this.personneId = personneId;
    }
    @Basic
    @Column(name = "film_id", nullable = false)
    public long getFilmId() {
        return filmId;
    }
    public void setFilmId(long filmId) {
        this.filmId = filmId;
    }
    @Basic
    @Column(name = "rank", nullable = true)
    public Integer getOrdre() {
        return ordre;
    }
    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 80)
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return getId() == role.getId() &&
                getPersonneId() == role.getPersonneId() &&
                getFilmId() == role.getFilmId() &&
                Objects.equals(getOrdre(), role.getOrdre()) &&
                Objects.equals(getNom(), role.getNom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPersonneId(), getFilmId(), getOrdre(), getNom());
    }
}
