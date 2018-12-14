package fr.laerce.cinema.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
//si le nom de la table est diff que la class
@Table(name="films")
public class Film {
    private long id;
    private String title;
    private Double rating;
    private String imagePath;
    private String summary;
    private Integer idRealisateur;

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
    @Column(name = "title", nullable = false, length = 60)
    public String getTitle() {
        return title;
    }

    public void setTitle(String surname) {
        this.title = surname;
    }

    @Basic
    @Column(name = "rating", nullable = true, length = 40)
    public Double getRating() {
        return rating;
    }

    public void setRating(Double givenname) {
        this.rating = givenname;
    }

    @Basic
    @Column(name = "summary", nullable = true)
    public String getSummary() {
        return summary;
    }

    public void setSummary(String birthYear) {
        this.summary = birthYear;
    }

    @Basic
    @Column(name = "image_path", nullable = true)
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    @Basic
    @Column(name = "film_director", nullable = true)
    public Integer getIdRealisateur() {
        return idRealisateur;
    }
    public void setIdRealisateur(Integer idRealisateur) {
        this.idRealisateur = idRealisateur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film)) return false;
        Film film = (Film) o;
        return getId() == film.getId() &&
                Objects.equals(getTitle(), film.getTitle()) &&
                Objects.equals(getRating(), film.getRating()) &&
                Objects.equals(getImagePath(), film.getImagePath()) &&
                Objects.equals(getSummary(), film.getSummary()) &&
                Objects.equals(getIdRealisateur(), film.getIdRealisateur());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getRating(), getImagePath(), getSummary(), getIdRealisateur());
    }
}
