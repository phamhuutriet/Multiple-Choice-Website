package Multiple.Choice.multiplechoice.models;

import java.util.Comparator;

public class PriorityComparator implements Comparator<Question> {
    @Override
    public int compare(Question q1, Question q2) {
        return q2.getPriorityScore() - q1.getPriorityScore();
    }
}
