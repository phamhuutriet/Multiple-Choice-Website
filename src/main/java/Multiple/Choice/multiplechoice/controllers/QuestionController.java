package Multiple.Choice.multiplechoice.controllers;

import Multiple.Choice.multiplechoice.models.Choice;
import Multiple.Choice.multiplechoice.models.Question;
import Multiple.Choice.multiplechoice.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionService questionService;

    // CONSTRUCTOR
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("")
    public ResponseEntity<Question> createQuestion(@RequestBody Question newQuestion) {
        return ResponseEntity.ok(questionService.createQuestion(newQuestion));
    }

    @GetMapping("")
    public ResponseEntity<List<Question>> fetchAllQuestion() {
        return ResponseEntity.ok(questionService.fetchAllQuestions());
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<Optional<Question>> testFetchById(@PathVariable("id") String id) throws Exception {
        int newId = Integer.parseInt(id);
        Optional<Question> optionalQuestion = questionService.testFetchById(newId);
        if (optionalQuestion.isEmpty()) throw new Exception("Id not found");
        Question question = optionalQuestion.get();

        for (Choice choice: question.getChoices()) {
            System.out.print(choice.isAnswer() + " ");
        }

        return ResponseEntity.ok(questionService.testFetchById(newId));
    }
}
