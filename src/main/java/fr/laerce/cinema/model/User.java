package fr.laerce.cinema.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "User")
@Table(name="user", schema = "public")
public class User {
    private long id;
    private String surname;
    private String givenname;
    private String login;
    private String password;
    private List<revue> lesRevues = new ArrayList<>();

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
    @Column(name = "surname", nullable = false, length = 40)
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    @Basic
    @Column(name = "givenname", nullable = false, length = 40)
    public String getGivenname() {
        return givenname;
    }
    public void setGivenname(String surname) {
        this.givenname = givenname;
    }


    @Basic
    @Column(name = "login", nullable = true)
    public String getLogin() {
        return login;
    }
    public void setLogin(String birthYear) {
        this.login = birthYear;
    }

    @Basic
    @Column(name = "password", nullable = true)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(mappedBy = "user_id")
    public List<revue> getLesRevues() {
        return lesRevues;
    }
    public void setLesRevues(List<revue> lesRevues) {
        this.lesRevues = lesRevues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() &&
                Objects.equals(getSurname(), user.getSurname()) &&
                Objects.equals(getGivenname(), user.getGivenname()) &&
                Objects.equals(getLogin(), user.getLogin()) &&
                Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSurname(), getGivenname(), getLogin(), getPassword());
    }



}
