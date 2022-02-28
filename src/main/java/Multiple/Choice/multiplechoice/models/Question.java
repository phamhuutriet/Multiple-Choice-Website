package Multiple.Choice.multiplechoice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;

@Entity
public class Question {
    @Id
    @GeneratedValue
    @Column(name = "Q_ID")
    @Getter @Setter
    private int id;

    @Column
    @Getter @Setter
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    @JsonIgnoreProperties("question")
    @Getter @Setter
    private List<Choice> choices;

    @ManyToOne
    @JoinColumn(name = "DECK_ID")
    @JsonIgnoreProperties("questions")
    @Getter @Setter
    private Deck deck;

    @Getter @Setter
    private int priorityScore = 1;

    @Getter @Setter
    private Date createdAt;

    public Question() {
        this.createdAt = new Date();
    }
}
