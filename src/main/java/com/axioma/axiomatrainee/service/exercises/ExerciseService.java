package com.axioma.axiomatrainee.service.exercises;

import com.axioma.axiomatrainee.model.exercises.*;
import com.axioma.axiomatrainee.repository.IExerciseRepository;
import com.axioma.axiomatrainee.repository.QuestionsRepository;
import com.axioma.axiomatrainee.repository.SettingsRepository;
import com.axioma.axiomatrainee.requestdto.SaveExerciseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final IExerciseRepository exerciseRepository;
    private final QuestionsRepository questionsRepository;
    private final SettingsRepository settingsRepository;

    public Optional<Exercise> findById(Long id, ExerciseType type) {
        return exerciseRepository.findByIdAndExerciseTypeEquals(id, type);
    }

    public void deleteById(Long id) {
        exerciseRepository.deleteById(id);
    }

    @Transactional
    public Exercise save(SaveExerciseRequest request) {
        Set<Question> questions = new HashSet<>();
        if(request.getQuestions() != null) {
            questions = request.getQuestions().stream()
                    .map(this::fromDto)
                    .collect(Collectors.toSet());
            questionsRepository.saveAll(questions);
        }
        ExerciseSettings settings = new ExerciseSettings();
        if(request.getSettings() != null) {
            settings = settingsRepository.save(request.getSettings());
        }
        Exercise exercise = Exercise.builder()
                        .exerciseType(request.getType())
                        .name(request.getName())
                        .difficulty(request.getDifficulty())
                        .data(request.getData())
                        .questions(questions)
                        .settings(settings)
                        .createdAt(Date.from(Instant.now()))
                        .build();
        questions.forEach(q -> q.setExercise(exercise));
        return exerciseRepository.save(exercise);
    }

    public Set<Exercise> findAllByType(ExerciseType exerciseType) {
        return exerciseRepository.findAllByExerciseTypeEquals(exerciseType);
    }

    private Question fromDto(QuestionDto dto) {
        Question question = new Question();
        question.setQuestion(dto.getQuestion());
        question.setAnswers(dto.getAnswers());
        question.setRightAnswer(dto.getRightAnswer());
        return question;
    }
 }
