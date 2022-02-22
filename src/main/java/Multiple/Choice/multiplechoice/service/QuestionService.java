package Multiple.Choice.multiplechoice.service;

import Multiple.Choice.multiplechoice.models.Choice;
import Multiple.Choice.multiplechoice.models.Question;
import Multiple.Choice.multiplechoice.repositories.ChoiceRepo;
import Multiple.Choice.multiplechoice.repositories.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.UpdatableSqlQuery;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepo questionRepo;

    // CONSTRUCTOR
    @Autowired
    public QuestionService(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }

    // MAIN METHODS
    public Question createQuestion(Question newQuestion) {
        List<Choice> choices = newQuestion.getChoices();

        return questionRepo.save(newQuestion);
    }

    public List<Question> fetchAllQuestions() {
        return questionRepo.findAll();
    }

    public Question fetchQuestionById(int id) throws Exception {
        Optional<Question> optionalQuestion = questionRepo.findById(id);
        if (optionalQuestion.isEmpty()) throw new Exception("Id not found");
        return optionalQuestion.get();
    }

    public Question updateQuestionById(int id, Question updatedQuestion) throws Exception {
        Optional<Question> optionalQuestion = questionRepo.findById(id);
        if (optionalQuestion.isEmpty()) throw new Exception("Id not found");
        Question question = optionalQuestion.get();

        question.setDescription(updatedQuestion.getDescription());
        question.setChoices(updatedQuestion.getChoices());
        question.setPriorityScore(updatedQuestion.getPriorityScore());

        return questionRepo.save(question);
    }

    public void deleteQuestionById(int id) {
        questionRepo.deleteById(id);
    }


    // TEST METHODS
    public List<Question> populateData(List<Question> data) {
        return questionRepo.saveAll(data);
    }

    public Optional<Question> testFetchById(int id) {
        return questionRepo.findById(id);
    }
}
