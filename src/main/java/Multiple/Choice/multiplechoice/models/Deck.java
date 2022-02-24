package Multiple.Choice.multiplechoice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deck")
    @JsonIgnoreProperties("deck")
    @Getter @Setter
    private List<Question> questions;
}
