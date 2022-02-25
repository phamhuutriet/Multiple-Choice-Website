package Multiple.Choice.multiplechoice.service;

import Multiple.Choice.multiplechoice.models.Choice;
import Multiple.Choice.multiplechoice.models.Question;
import Multiple.Choice.multiplechoice.repositories.ChoiceRepo;
import Multiple.Choice.multiplechoice.repositories.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.UpdatableSqlQuery;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepo questionRepo;
    private final ChoiceRepo choiceRepo;

    // CONSTRUCTOR
    @Autowired
    public QuestionService(QuestionRepo questionRepo, ChoiceRepo choiceRepo) {
        this.choiceRepo = choiceRepo;
        this.questionRepo = questionRepo;
    }

    // MAIN METHODS
    public Question createQuestion(Question newQuestion) {
        for (Choice choice: newQuestion.getChoices()) {
            choice.setQuestion(newQuestion);
        }

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

        question.setDescription(updatedQuestion.getDescription() != null ? updatedQuestion.getDescription() : question.getDescription());
        question.setChoices(updatedQuestion.getChoices().isEmpty() ? updatedQuestion.getChoices() : question.getChoices());
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
