package Multiple.Choice.multiplechoice.repositories;

import Multiple.Choice.multiplechoice.models.Question;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer>, JpaSpecificationExecutor<Question> {
//    List<Question> findAll(Specification<Question> questionSpec);
}
