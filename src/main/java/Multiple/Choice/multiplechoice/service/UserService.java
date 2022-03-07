package Multiple.Choice.multiplechoice.service;

import Multiple.Choice.multiplechoice.models.Deck;
import Multiple.Choice.multiplechoice.models.security.User;
import Multiple.Choice.multiplechoice.repositories.DeckRepo;
import Multiple.Choice.multiplechoice.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final DeckService deckService;

    public UserService(UserRepo userRepo, DeckService deckService) {
        this.userRepo = userRepo;
        this.deckService = deckService;
    }

    public User createNewUser(User newUser) {
        return userRepo.save(newUser);
    }

    public List<User> fetchAllUsers() {
        return userRepo.findAll();
    }

    public List<Deck> fetchAllDeckOfUser(int userId) throws Exception{
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isEmpty()) throw new Exception("Id not found");
        User user = optionalUser.get();

        return user.getDecks();
    }

    public Deck addNewDeck(int userId, Deck newDeck) throws  Exception{
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isEmpty()) throw new Exception("Id not found");
        User user = optionalUser.get();

        Deck savedDeck = deckService.createDeck(newDeck);
        savedDeck.setUser(user);
        user.getDecks().add(savedDeck);
        userRepo.save(user);

        return savedDeck;
    }

    public User removeDeck(int userId, int deckId) throws Exception{
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isEmpty()) throw new Exception("Id not found");
        User user = optionalUser.get();

        user.getDecks().removeIf(deck -> deck.getId() == deckId);

        return userRepo.save(user);
    }
}
