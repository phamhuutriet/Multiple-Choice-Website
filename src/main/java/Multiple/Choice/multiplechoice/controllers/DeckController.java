package Multiple.Choice.multiplechoice.controllers;

import Multiple.Choice.multiplechoice.models.Deck;
import Multiple.Choice.multiplechoice.models.Question;
import Multiple.Choice.multiplechoice.service.DeckService;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.rmi.server.ExportException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/decks")
public class DeckController {

    private final DeckService deckService;

    // CONSTRUCTOR
    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    // MAIN METHODS

    // Handle a whole deck
    @PostMapping("")
    public ResponseEntity<Deck> createDeck(@RequestBody Deck newDeck) {
        return ResponseEntity.ok(deckService.createDeck(newDeck));
    }

    @GetMapping("")
    public ResponseEntity<List<Deck>> fetchAllDeck() {
        System.out.println("FETCH ALL DECK");
        return ResponseEntity.ok(deckService.fetchAllDeck());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deck> fetchDeckById(@PathVariable("id") String id) throws Exception{
        int intId = Integer.parseInt(id);
        return ResponseEntity.ok(deckService.fetchDeckById(intId));
    }

    @GetMapping("/{deckId}/questions")
    public ResponseEntity<List<Question>> fetchAllQuestionByDeck(@PathVariable("deckId") String deckId,
                                                                 @Nullable @RequestParam("shuffleQuestion") String shuffleQuestion,
                                                                 @Nullable @RequestParam("shuffleChoice") String shuffleChoice,
                                                                 @Nullable @RequestParam("sortByPriority") String sortByPriority) throws Exception {
        int intDeckId = Integer.parseInt(deckId);
        return ResponseEntity.ok(deckService.fetchAllQuestion(intDeckId, shuffleQuestion, shuffleChoice, sortByPriority));
    }

    @DeleteMapping("/{deckId}")
    public ResponseEntity<Void> deleteDeckById(@PathVariable("deckId") String deckId) {
        int intDeckId = Integer.parseInt(deckId);
        deckService.deleteDeckById(intDeckId);
        return ResponseEntity.ok().build();
    }

    // Modify each deck
    @PostMapping("/{deckId}/questions")
    public ResponseEntity<Deck> addQuestion(@PathVariable("deckId") String deckId, @RequestBody Question newQuestion) throws Exception{
        int intDeckId = Integer.parseInt(deckId);
        return ResponseEntity.ok(deckService.addQuestion(newQuestion, intDeckId));
    }

    @PatchMapping("/{deckId}/questions/remove/{questionId}")
    public ResponseEntity<Deck> removeQuestion(@PathVariable("deckId") String deckId, @PathVariable("questionId") String questionId) throws Exception{
        int intDeckId = Integer.parseInt(deckId);
        int intQuestionId = Integer.parseInt(questionId);
        return ResponseEntity.ok(deckService.removeQuestion(intDeckId, intQuestionId));
    }

    // TEST METHODS
}
