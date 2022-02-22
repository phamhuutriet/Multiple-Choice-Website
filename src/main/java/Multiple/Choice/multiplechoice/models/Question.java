package Multiple.Choice.multiplechoice.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="Q_ID")
    @Getter @Setter
    private List<Choice> choices;

}
