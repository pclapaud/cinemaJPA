package fr.laerce.cinema.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Film")
@Table(name="films")
public class Film {
    private long id;
    private String title;
    private Double rating;
    private String imagePath;
    private String summary;
    private Personne director;
    private java.time.LocalDate dateSortie;
    private List<Role> lesRoles = new ArrayList<>();
    private List<revue> lesRevues = new ArrayList<>();
    private List<Genre> lesGenres;

    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
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

    @ManyToOne
    @JoinColumn(name = "film_director")
    public Personne getDirector() {
        return director;
    }
    public void setDirector(Personne realisateur) {
        this.director = realisateur;
    }
    @OneToMany(mappedBy = "film")
    public List<Role> getLesRoles() {
        return lesRoles;
    }
    public void setLesRoles(List<Role> lesroles) {
        this.lesRoles = lesroles;
    }
    @OneToMany(mappedBy = "film_id")
    public List<revue> getLesRevues() {
        return lesRevues;
    }
    public void setLesRevues(List<revue> lesRevues) {
        this.lesRevues = lesRevues;
    }

    @ManyToMany(mappedBy = "filmGenre")
    public List<Genre> getLesGenres() { return lesGenres; }
    public void setLesGenres(List<Genre> genre) {
        this.lesGenres = genre;
    }

    @Basic
    @Column(name = "release_date", nullable = true)
    public java.time.LocalDate getDateSortie() {
        return dateSortie;
    }
    public void setDateSortie(java.time.LocalDate dateSortie) {
        this.dateSortie = dateSortie;
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
                Objects.equals(getDirector(), film.getDirector()) &&
                Objects.equals(getDateSortie(), film.getDateSortie()) &&
                Objects.equals(getLesRoles(), film.getLesRoles()) &&
                Objects.equals(getLesRevues(), film.getLesRevues()) &&
                Objects.equals(getLesGenres(), film.getLesGenres());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getRating(), getImagePath(), getSummary(), getDirector(), getDateSortie(), getLesRoles(), getLesRevues(), getLesGenres());
    }
}
