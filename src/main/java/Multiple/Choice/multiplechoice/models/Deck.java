package Multiple.Choice.multiplechoice.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.List;
import java.util.PriorityQueue;

@Entity
public class Deck {

    @Id
    @GeneratedValue
    @Getter @Setter
    @Column(name = "DECK_ID")
    private int id;

    @Getter @Setter
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "DECK_ID")
    @Getter @Setter
    private List<Question> questions;
}
