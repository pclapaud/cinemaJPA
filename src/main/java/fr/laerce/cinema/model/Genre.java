package fr.laerce.cinema.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="genres")
public class Genre {
    private long id;
    private String name;
    private List<Film> filmGenre;


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 40)
    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
    }
    @ManyToMany(mappedBy = "lesGenres")
    public List<Film> getFilmGenre() { return filmGenre; }
    public void setFilmGenre(List<Film> filmGenre) { this.filmGenre = filmGenre; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre)) return false;
        Genre genre = (Genre) o;
        return getId() == genre.getId() &&
                Objects.equals(getName(), genre.getName()) &&
                Objects.equals(getFilmGenre(), genre.getFilmGenre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getFilmGenre());
    }
}
