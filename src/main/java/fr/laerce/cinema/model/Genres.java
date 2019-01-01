package fr.laerce.cinema.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="genres")
public class Genres {
    private long id;
    private String name;
    private List<Film> filmGenre;


    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
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

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "film_genre",
            joinColumns = {@JoinColumn(name = "genre_id")},
            inverseJoinColumns = { @JoinColumn(name = "film_id")}
            )
    public List<Film> getFilmGenre() { return filmGenre; }
    public void setFilmGenre(List<Film> filmGenre) { this.filmGenre = filmGenre; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genres)) return false;
        Genres genres = (Genres) o;
        return getId() == genres.getId() &&
                Objects.equals(getName(), genres.getName()) &&
                Objects.equals(getFilmGenre(), genres.getFilmGenre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getFilmGenre());
    }
}
