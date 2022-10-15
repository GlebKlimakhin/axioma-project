package com.axioma.axiomatrainee.model.exercises;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import java.util.List;

@Embeddable
public class QuestionsAnswers {

    private String question;

    @ElementCollection
    @CollectionTable(name = "QA_answers", joinColumns = @JoinColumn(name = "qa_id"))
    private List<String> answers;
}
