package Multiple.Choice.multiplechoice.controllers;

import Multiple.Choice.multiplechoice.models.Deck;
import Multiple.Choice.multiplechoice.models.security.User;
import Multiple.Choice.multiplechoice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<List<User>> fetchAllUsers() {
        return ResponseEntity.ok(userService.fetchAllUsers());
    }

    @PostMapping("{userId}/decks")
    public ResponseEntity<Deck> addNewDeck(@RequestBody Deck newDeck,
                                           @PathVariable("userId") String userId) throws Exception {
        int intUserId = Integer.parseInt(userId);
        return ResponseEntity.ok(userService.addNewDeck(intUserId, newDeck));
    }

    @DeleteMapping("{userId}/decks/{deckId}")
    public ResponseEntity<User> removeDeck(@PathVariable("userId") String userId,
                                           @PathVariable("deckId") String deckId) throws Exception {
        int intUserId = Integer.parseInt(userId);
        int intDeckId = Integer.parseInt(deckId);
        return ResponseEntity.ok(userService.removeDeck(intUserId, intDeckId));
    }

    @GetMapping("{userId}/decks")
    public ResponseEntity<List<Deck>> fetchAllDeckOfUser(@PathVariable("userId") String userId) throws Exception {
        int intUserId = Integer.parseInt(userId);
        return ResponseEntity.ok(userService.fetchAllDeckOfUser(intUserId));
    }
}
