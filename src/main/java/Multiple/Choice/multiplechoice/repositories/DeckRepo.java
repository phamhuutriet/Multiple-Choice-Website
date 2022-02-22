package Multiple.Choice.multiplechoice.repositories;

import Multiple.Choice.multiplechoice.models.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeckRepo extends JpaRepository<Deck, Integer> {
}
