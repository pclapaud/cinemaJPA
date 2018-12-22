package fr.laerce.cinema.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RoleId
        implements Serializable {

    @Column(name = "film_id")
    public Long filmId;

    @Column(name = "person_id")
    public Long personId;



    public RoleId(Long filmId, Long personId) {
        this.filmId = filmId;
        this.personId = personId;
    }

    public RoleId() {
    }

    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        RoleId that = (RoleId) o;
        return Objects.equals(filmId, that.filmId) &&
                Objects.equals(personId, that.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId, personId);
    }
}