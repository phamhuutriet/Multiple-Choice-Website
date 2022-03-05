package Multiple.Choice.multiplechoice.models.security;

import Multiple.Choice.multiplechoice.models.Deck;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    @Getter @Setter
    @Column(name = "USER_ID")
    private int id;

    @Getter @Setter
    @Column(unique = true)
    private String username;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private boolean active;

    @Getter @Setter
    private String roles;

    @Getter @Setter
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Deck> decks;

    public User() {
        this.active = true;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", roles='" + roles + '\'' +
                '}';
    }
}
