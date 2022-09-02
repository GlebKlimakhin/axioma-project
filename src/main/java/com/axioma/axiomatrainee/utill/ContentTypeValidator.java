package com.axioma.axiomatrainee.utill;

import com.axioma.axiomatrainee.model.files.AvailibleFileTypes;

public class ContentTypeValidator {

    public static boolean validate(String contentType) {
        return (contentType.equals(AvailibleFileTypes.JPEG.getContentType()) ||
                contentType.equals(AvailibleFileTypes.JPG.getContentType()) ||
                contentType.equals(AvailibleFileTypes.PNG.getContentType());
    }

}
