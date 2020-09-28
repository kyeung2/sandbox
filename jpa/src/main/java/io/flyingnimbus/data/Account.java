package io.flyingnimbus.data;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "created_on")
    private LocalDateTime createdOn;// default support for LocalDateTime

    // only needed by JPA, protected = following Spring guide
    protected Account() {
    }

    public Account(String userName, String password, String email, LocalDateTime createdOn) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.createdOn = createdOn;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", createdOn=" + createdOn +
                '}';
    }
}

