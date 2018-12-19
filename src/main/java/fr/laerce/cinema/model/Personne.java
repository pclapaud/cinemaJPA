package fr.laerce.cinema.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
//si le nom de la table est diff que la class
@Table(name="persons")
public class Personne {
    private Long id;
    private String nom;
    private String prenom;
    private Integer naissance;
    private String photoPath;
    private List<Film> lesfilms;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "surname", nullable = false, length = 60)
    public String getNom() {
        return nom;
    }

    public void setNom(String surname) {
        this.nom = surname;
    }

    @Basic
    @Column(name = "givenname", nullable = true, length = 40)
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String givenname) {
        this.prenom = givenname;
    }

    @Basic
    @Column(name = "birth_year", nullable = true)
    public Integer getNaissance() {
        return naissance;
    }

    public void setNaissance(Integer birthYear) {
        this.naissance = birthYear;
    }

    @Basic
    @Column(name = "image_path", nullable = true, length = 80)
    public String getPhotoPath() {
        return photoPath;
    }
    public void setPhotoPath(String imagePath) {
        this.photoPath = imagePath;
    }

    @OneToMany(mappedBy = "director")
    public List<Film> getLesfilms() {
        return lesfilms;
    }
    public void setLesfilms(List<Film> lesfilms) {
        this.lesfilms = lesfilms;
    }
    public void addLesfilms(Film film){
        if(!lesfilms.contains(film)){
            lesfilms.add(film);
            film.setDirector(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Personne persons = (Personne) o;

        if (id != persons.id) return false;


        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNom(), getPrenom(), getNaissance(), getPhotoPath());
    }
}
