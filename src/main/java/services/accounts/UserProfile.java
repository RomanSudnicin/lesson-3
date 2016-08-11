package services.accounts;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by roman on 06.08.16.
 */
@Entity
@Table(name = "UserProfiles")
public class UserProfile implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login", unique = true, updatable = true)
    private String login;

    @Column(name = "pass",updatable = true)
    private String pass;

    @Column(name = "email")
    private String email;


    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
