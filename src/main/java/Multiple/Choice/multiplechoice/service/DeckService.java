package Multiple.Choice.multiplechoice.service;

import Multiple.Choice.multiplechoice.models.Choice;
import Multiple.Choice.multiplechoice.models.Deck;
import Multiple.Choice.multiplechoice.models.PriorityComparator;
import Multiple.Choice.multiplechoice.models.Question;
import Multiple.Choice.multiplechoice.repositories.DeckRepo;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public Deck addQuestion(Question newQuestion, int id) throws Exception {
        Optional<Deck> optionalDeck = deckRepo.findById(id);
        if (optionalDeck.isEmpty()) throw new Exception("Deck's id not found");
        Deck deck = optionalDeck.get();
        deck.getQuestions().add(newQuestion);

        return deckRepo.save(deck);
    }

    public Deck removeQuestion(int deckId, int questionId) throws Exception {
        Optional<Deck> optionalDeck = deckRepo.findById(deckId);
        if (optionalDeck.isEmpty()) throw new Exception("Deck's id not found");
        Deck deck = optionalDeck.get();
        deck.getQuestions().removeIf(question -> question.getId() == questionId);

        return deckRepo.save(deck);
    }

    public Deck updateQuestion(Question updatedQuestion, int deckId, int questionId) throws Exception {
        Optional<Deck> optionalDeck = deckRepo.findById(deckId);
        if (optionalDeck.isEmpty()) throw new Exception("Deck's id not found");
        Deck deck = optionalDeck.get();

        for (Question question: deck.getQuestions()) {
            if (question.getId() == questionId) {
                question.setDescription(updatedQuestion.getDescription());
                question.setChoices(updatedQuestion.getChoices());
                question.setPriorityScore(updatedQuestion.getPriorityScore());
            }
        }

        return deckRepo.save(deck);
    }

    public List<Question> fetchAllQuestion(int id, String shuffleQuestion, String shuffleChoice, String sortByPriority) throws Exception {
        Optional<Deck> optionalDeck = deckRepo.findById(id);
        if (optionalDeck.isEmpty()) throw new Exception("Deck's id not found");
        Deck deck = optionalDeck.get();
        List<Question> questions = deck.getQuestions();

        if (!(shuffleQuestion==null || shuffleQuestion.equals(""))) Collections.shuffle(questions);

        if (!(shuffleChoice==null || shuffleChoice.equals(""))) {
            for (Question question: deck.getQuestions()) {
                shuffleChoices(question);
            }
        }

        if (!(sortByPriority != null && sortByPriority.equals("false"))) {
            questions.sort(new PriorityComparator());
        }

        return questions;
    }

    // HELPER METHODS
    public void shuffleChoices(Question question) {
        List<Choice> choices = question.getChoices();
        Collections.shuffle(choices);
        question.setChoices(choices);
    }
}
