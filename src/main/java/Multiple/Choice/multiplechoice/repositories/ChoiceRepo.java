package Multiple.Choice.multiplechoice.repositories;

import Multiple.Choice.multiplechoice.models.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoiceRepo extends JpaRepository<Choice, Integer> {
}
