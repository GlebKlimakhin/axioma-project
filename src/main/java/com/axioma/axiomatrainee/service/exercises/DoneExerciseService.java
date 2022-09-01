package com.axioma.axiomatrainee.service.exercises;

import com.axioma.axiomatrainee.model.dto.DoneExerciseDto;
import com.axioma.axiomatrainee.model.exercises.DoneExercise;
import com.axioma.axiomatrainee.model.exercises.ExerciseType;
import com.axioma.axiomatrainee.repository.IDoneExercisesRepository;
import com.axioma.axiomatrainee.requestdto.SaveDoneExerciseRequestDto;
import com.axioma.axiomatrainee.utill.DateParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoneExerciseService {

    private IDoneExercisesRepository doneExercisesRepository;
    private static final String READING_SPEED = "READING_SPEED";

    @Autowired
    public void setDoneExercisesRepository(IDoneExercisesRepository doneExercisesRepository) {
        this.doneExercisesRepository = doneExercisesRepository;
    }

    public List<DoneExerciseDto> findAllByUserIdAndType(Long userId, ExerciseType type) {
        return doneExercisesRepository.findAllByDoneExerciseId_UseridAndExerciseType(userId, type)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<DoneExerciseDto> findAllByUserId(Long userId) {

        return doneExercisesRepository.findAllByDoneExerciseId_Userid(userId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public DoneExercise save(SaveDoneExerciseRequestDto requestDto) {
        DoneExercise doneExercise = new DoneExercise();
        doneExercise.setDoneExerciseId(requestDto.getDoneExerciseId());
        doneExercise.setExerciseType(ExerciseType.READING_SPEED);
        return doneExercisesRepository.save(doneExercise);
    }

    private DoneExercise mapToEntity(DoneExerciseDto dto) {
        return DoneExercise.builder()
                .doneExerciseId(dto.getDoneExerciseId())
                .exerciseType(dto.getExerciseType())
                .score(dto.getScore())
                .doneAt(DateParser.parseFromUnix(dto.getUnixDate()))
                .build();
    }

    private DoneExerciseDto mapToDto(DoneExercise exercise) {
        return DoneExerciseDto.builder()
                .doneExerciseId(exercise.getDoneExerciseId())
                .exerciseType(exercise.getExerciseType())
                .score(exercise.getScore())
                .unixDate(DateParser.parseFromDate(exercise.getDoneAt()))
                .build();
    }
}
