package com.ritacle.mhistory.persistence.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Listener"
       ,uniqueConstraints = {@UniqueConstraint( name = "mail_constraint", columnNames = {"mail"}),
        @UniqueConstraint( name = "username_constraint", columnNames = {"username"})}
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String mail;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 1)
    private String gender;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id", foreignKey = @ForeignKey(name = "COUNTRY_ID_FK"))
    private Country country;

    public User() {
    }

    public User(String mail) {
        this.mail = mail;
    }

    public User(String userName, String nickName, String mail, String password, String gender, Date birthDate) {
        this.userName = userName;
        this.nickName = nickName;
        this.mail = mail;
        this.password = password;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public User(String userName, String nickName, String mail, String password, String gender, Date birthDate, Country country) {
        this.userName = userName;
        this.nickName = nickName;
        this.mail = mail;
        this.password = password;
        this.gender = gender;
        this.birthDate = birthDate;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "User: id = " + id + ", username = " + userName + ", nickname = " + nickName + ", mail = " + mail +
                ", password = " + password + ", gender = " + gender + ", birthDate = " + birthDate + ", country = " + country;
    }
}
