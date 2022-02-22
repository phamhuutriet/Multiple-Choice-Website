package Multiple.Choice.multiplechoice.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Choice {
    @Id
    @GeneratedValue
    @Getter @Setter
    private int id;

    @Column
    @Getter @Setter
    private String body;

    @ManyToOne
    private Question question;

    @Getter @Setter
    private boolean isAnswer;
}
