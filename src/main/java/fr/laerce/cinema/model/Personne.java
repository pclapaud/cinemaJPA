package fr.laerce.cinema.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Personne")
@Table(name= "persons")
public class Personne {
    private Long id;
    private String nom;
    private String prenom;
    private java.time.LocalDate naissance;
    private String photoPath;
    private List<Film> lesFilms;
    private List<Role> lesRoles = new ArrayList<>();


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
    @Column(name = "birthday", nullable = true)
    public java.time.LocalDate getNaissance() {
        return naissance;
    }
    public void setNaissance(java.time.LocalDate birthYear) {
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
        return lesFilms;
    }
    public void setLesfilms(List<Film> lesfilms) {
        this.lesFilms = lesfilms;
    }
    @OneToMany(mappedBy = "personne")
    public List<Role> getLesRoles() {
        return lesRoles;
    }
    public void setLesRoles(List<Role> lesroles) {
        this.lesRoles = lesroles;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Personne)) return false;
        Personne personne = (Personne) o;
        return Objects.equals(getId(), personne.getId()) &&
                Objects.equals(getNom(), personne.getNom()) &&
                Objects.equals(getPrenom(), personne.getPrenom()) &&
                Objects.equals(getNaissance(), personne.getNaissance()) &&
                Objects.equals(getPhotoPath(), personne.getPhotoPath()) &&
                Objects.equals(lesFilms, personne.lesFilms) &&
                Objects.equals(getLesRoles(), personne.getLesRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNom(), getPrenom(), getNaissance(), getPhotoPath(), lesFilms, getLesRoles());
    }
}
