package fr.laerce.cinema.model;

import javax.persistence.*;
import java.util.Objects;


@Entity(name = "Role")
@Table(name = "play")
public class Role {


    public long id;
    public Personne personne;
    public String name;
    public Film film;
    public Integer rank;


    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "person_id")
    public Personne getPersonne() {
        return personne;
    }
    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    @ManyToOne
    @JoinColumn(name = "film_id")
    public Film getFilm() {
        return film;
    }
    public void setFilm(Film film) {
        this.film = film;
    }


    @Column(name = "rank")
    public Integer getRank() { return rank; }
    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }









    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return getId() == role.getId() &&
                Objects.equals(getPersonne(), role.getPersonne()) &&
                Objects.equals(getFilm(), role.getFilm()) &&
                Objects.equals(getRank(), role.getRank()) &&
                Objects.equals(getName(), role.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPersonne(), getFilm(), getRank(), getName());
    }
}