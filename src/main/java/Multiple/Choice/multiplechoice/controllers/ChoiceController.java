package Multiple.Choice.multiplechoice.controllers;

import Multiple.Choice.multiplechoice.models.Choice;
import Multiple.Choice.multiplechoice.repositories.ChoiceRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/choices")
public class ChoiceController {
    private final ChoiceRepo choiceRepo;

    public ChoiceController(ChoiceRepo choiceRepo) {
        this.choiceRepo = choiceRepo;
    }

    @GetMapping("")
    public List<Choice> fetchAll() {
        return choiceRepo.findAll();
    }
}
