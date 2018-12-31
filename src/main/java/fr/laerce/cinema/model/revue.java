package fr.laerce.cinema.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "revue")
@Table(name="review")
public class revue {
    private long id;
    private Film film_id;
    private User user_id;
    private String article;
    private java.time.LocalDate datte;

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
    @JoinColumn(name = "film_id")
    public Film getFilm_id() {
        return film_id;
    }
    public void setFilm_id(Film film_id) {
        this.film_id = film_id;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser_id() {
        return user_id;
    }
    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    @Basic
    @Column(name = "article", nullable = true)
    public String getArticle() {
        return article;
    }
    public void setArticle(String article) {
        this.article = article;
    }


    @Basic
    @Column(name = "datte", nullable = true)
    public java.time.LocalDate getDatte() {
        return datte;
    }
    public void setDatte(java.time.LocalDate datte) {
        this.datte = datte;
    }


}
