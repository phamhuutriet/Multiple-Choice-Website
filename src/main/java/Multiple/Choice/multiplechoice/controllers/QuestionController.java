package Multiple.Choice.multiplechoice.controllers;

import Multiple.Choice.multiplechoice.models.Question;
import Multiple.Choice.multiplechoice.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
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

    @GetMapping("/{id}")
    public ResponseEntity<Question> fetchQuestionById(@PathVariable("id") String id) throws Exception{
        int intId = Integer.parseInt(id);
        return ResponseEntity.ok(questionService.fetchQuestionById(intId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable("id") String id, @RequestBody Question updatedQuestion) throws Exception {
        int intId = Integer.parseInt(id);
        return ResponseEntity.ok(questionService.updateQuestionById(intId, updatedQuestion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable("id") String id) {
        int intId = Integer.parseInt(id);
        questionService.deleteQuestionById(intId);
        return ResponseEntity.ok().build();
    }


    // TEST API
    @GetMapping("/test/{id}")
    public ResponseEntity<Optional<Question>> testFetchById(@PathVariable("id") String id, @PathVariable("deckId") String deckId) throws Exception {
        int newId = Integer.parseInt(id);
        Optional<Question> optionalQuestion = questionService.testFetchById(newId);
        if (optionalQuestion.isEmpty()) throw new Exception("Id not found");
        Question question = optionalQuestion.get();

        System.out.println(deckId);

        return ResponseEntity.ok(questionService.testFetchById(newId));
    }
}
