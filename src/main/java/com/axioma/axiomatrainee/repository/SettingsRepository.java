package com.axioma.axiomatrainee.repository;

import com.axioma.axiomatrainee.model.exercises.ExerciseSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingsRepository extends JpaRepository<ExerciseSettings, Long> {
}
