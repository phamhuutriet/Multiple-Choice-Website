package Multiple.Choice.multiplechoice.controllers;

import Multiple.Choice.multiplechoice.models.Deck;
import Multiple.Choice.multiplechoice.service.DeckService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/decks")
public class DeckController {

    private final DeckService deckService;

    // CONSTRUCTOR
    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    // MAIN METHODS
    @PostMapping("")
    public ResponseEntity<Deck> createDeck(@RequestBody Deck newDeck) {
        return ResponseEntity.ok(deckService.createDeck(newDeck));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deck> fetchDeckById(@PathVariable("id") String id) throws Exception{
        int intId = Integer.parseInt(id);
        return ResponseEntity.ok(deckService.fetchDeckById(intId));
    }


    // TEST METHODS
}
