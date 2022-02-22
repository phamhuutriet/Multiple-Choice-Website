package Multiple.Choice.multiplechoice.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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

    @JsonProperty("isAnswer")
    @Getter @Setter
    private boolean isAnswer;
}
