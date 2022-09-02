package com.axioma.axiomatrainee.repository;

import com.axioma.axiomatrainee.model.files.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageFile, Long> {
    Optional<ImageFile> findByUserId(Long userId);
}
