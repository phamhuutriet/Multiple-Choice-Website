package Multiple.Choice.multiplechoice.service;

import Multiple.Choice.multiplechoice.models.Choice;
import Multiple.Choice.multiplechoice.models.Question;
import Multiple.Choice.multiplechoice.repositories.ChoiceRepo;
import Multiple.Choice.multiplechoice.repositories.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepo questionRepo;
    private final ChoiceRepo choiceRepo;

    // CONSTRUCTOR
    @Autowired
    public QuestionService(QuestionRepo questionRepo, ChoiceRepo choiceRepo) {
        this.questionRepo = questionRepo;
        this.choiceRepo = choiceRepo;
    }

    // METHOD
    public Question createQuestion(Question newQuestion) {
        List<Choice> choices = newQuestion.getChoices();

        return questionRepo.save(newQuestion);
    }

    public List<Question> fetchAllQuestions() {
        return questionRepo.findAll();
    }

    public Optional<Question> testFetchById(int id) {
        return questionRepo.findById(id);
    }

    public List<Question> populateData(List<Question> data) {
        return questionRepo.saveAll(data);
    }


}
