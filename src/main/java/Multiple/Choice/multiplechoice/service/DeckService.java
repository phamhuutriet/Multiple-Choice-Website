package Multiple.Choice.multiplechoice.service;

import Multiple.Choice.multiplechoice.models.Deck;
import Multiple.Choice.multiplechoice.repositories.DeckRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeckService {

    private final DeckRepo deckRepo;

    // CONSTRUCTOR
    public DeckService(DeckRepo deckRepo) {
        this.deckRepo = deckRepo;
    }

    // MAIN METHODS
    public Deck createDeck(Deck newDeck) {
        return deckRepo.save(newDeck);
    }

    public Deck fetchDeckById(int id) throws Exception {
        Optional<Deck> optionalDeck = deckRepo.findById(id);
        if (optionalDeck.isEmpty()) throw new Exception("Deck's id not found");

        return optionalDeck.get();
    }

    public Deck updateDeckById(Deck updatedDeck, int id) throws Exception {
        Optional<Deck> optionalDeck = deckRepo.findById(id);
        if (optionalDeck.isEmpty()) throw new Exception("Deck's id not found");
        Deck deck = optionalDeck.get();
        deck.setQuestions(updatedDeck.getQuestions());

        return deckRepo.save(deck);
    }

    // TEST METHODS
}
