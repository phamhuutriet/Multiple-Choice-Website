package Multiple.Choice.multiplechoice.service;

import Multiple.Choice.multiplechoice.models.Choice;
import Multiple.Choice.multiplechoice.models.Question;
import Multiple.Choice.multiplechoice.repositories.ChoiceRepo;
import Multiple.Choice.multiplechoice.repositories.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.UpdatableSqlQuery;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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

    public Question updateQuestionById(int id, Question updatedQuestion, String setPriority) throws Exception {
        Optional<Question> optionalQuestion = questionRepo.findById(id);
        System.out.println("updated question by id");
        if (optionalQuestion.isEmpty()) throw new Exception("Id not found");
        Question question = optionalQuestion.get();

        // UPDATE BASIC INSTANCES
        question.setDescription(updatedQuestion.getDescription() != null ? updatedQuestion.getDescription() : question.getDescription());
        question.setPriorityScore(updatedQuestion.getPriorityScore());

        // UPDATE SPACED REPETITION
        if (setPriority.equals("true")) {
            Date newSpacedRepDate = getSpacedRepDate(question.getSpacedRepetition(), question.getPriorityScore());
            question.setSpacedRepetition(newSpacedRepDate);
        }

        // UPDATE CHOICES
        HashSet<Integer> newIdSet = new HashSet<>();

        for (Choice choice: updatedQuestion.getChoices()) {
            Choice updatedChoice = choiceRepo.save(choice);
            newIdSet.add(updatedChoice.getId());
            updatedChoice.setQuestion(question);
            question.getChoices().add(updatedChoice);
        }

        question.getChoices().removeIf(choice -> !newIdSet.contains(choice.getId()));

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

    // HELPER METHODS
    public Date getSpacedRepDate(Date date, int priorityScore) {
        Calendar calendar = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        calendar.setTime(date);
        System.out.println("Before " + df.format(calendar.getTime()));
        System.out.println("pscore: " + priorityScore);
        if (priorityScore >= 0) calendar.add(Calendar.DATE, 1);
        else calendar.add(Calendar.DATE, (int) Math.pow(2, -priorityScore));
        System.out.println("After " + df.format(calendar.getTime()));
        return calendar.getTime();
    }

}
