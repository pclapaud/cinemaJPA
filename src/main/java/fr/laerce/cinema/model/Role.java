package fr.laerce.cinema.model;

import javax.persistence.*;
import java.util.Objects;


@Entity(name = "Role")
@Table(name = "play")
public class Role {

    @EmbeddedId
    public RoleId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("personId")
    public Personne person;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("filmId")
    public Film film;

    @Column(name = "rank")
    public Integer rank;
    @Column(name = "name")
    public String name;


    public RoleId getId() {
        return id;
    }

    public void setId(RoleId id) {
        this.id = id;
    }

    public Personne getPerson() {
        return person;
    }

    public void setPerson(Personne personne) {
        this.person = personne;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role(Personne personne, Film film) {
        this.person = personne;
        this.film = film;
        this.id = new RoleId(personne.getId(), film.getId());

    }

    public Role() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Role that = (Role) o;
        return Objects.equals(person, that.person) &&
                Objects.equals(film, that.film);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, film);
    }
}