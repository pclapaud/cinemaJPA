package fr.laerce.cinema.model;

import javax.persistence.*;
import java.util.Objects;


@Entity(name = "Role")
@Table(name = "play")
public class Role {

    @EmbeddedId
    public RoleId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    @MapsId("personId")
    public Personne personne;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id")
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

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
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
        this.personne = personne;
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
        return Objects.equals(personne, that.personne) &&
                Objects.equals(film, that.film);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personne, film);
    }
}