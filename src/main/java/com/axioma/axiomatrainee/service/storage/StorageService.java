package com.axioma.axiomatrainee.service.storage;

import com.axioma.axiomatrainee.model.files.ImageFile;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void saveOrUpdateImageInUserDir(MultipartFile img, Long userId);

    ImageFile getImageByPath(String path);

}
