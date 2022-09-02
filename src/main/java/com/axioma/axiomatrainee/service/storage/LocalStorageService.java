package com.axioma.axiomatrainee.service.storage;

import com.axioma.axiomatrainee.exceptions.StorageException;
import com.axioma.axiomatrainee.model.files.ImageFile;
import com.axioma.axiomatrainee.repository.ImageRepository;
import com.axioma.axiomatrainee.utill.ContentTypeValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class LocalStorageService implements StorageService{

    public final String path;

    private final ImageRepository imageRepository;

    public LocalStorageService(ImageRepository imageRepository, @Value("${file.storage.workdir}") String path) {
        this.imageRepository = imageRepository;
        this.path = path;
    }

    @Override
    @Transactional
    public void saveOrUpdateImageInUserDir(MultipartFile img, Long userId) {
        if(!ContentTypeValidator.validate(img.getContentType())) {
            throw new IllegalArgumentException("Invalid file format");
        }

        ImageFile imageFile = ImageFile.builder()
                .userId(userId)
                .size(img.getSize())
                .link(path + userId + "/" + img.getName())
                .name(img.getName())
                .build();
        imageRepository.save(imageFile);


    }

    private boolean saveToFileSystem(MultipartFile img) {

    }

    private void makeDirectory(Path path) {
        if(Files.notExists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                throw new StorageException("Not able to create directory");
            }
        }
    }

    @Override
    public ImageFile getImageByPath(String path) {
        return null;
    }

}
