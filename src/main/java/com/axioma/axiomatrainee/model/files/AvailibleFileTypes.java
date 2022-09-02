package com.axioma.axiomatrainee.model.files;

public enum AvailibleFileTypes {
    JPEG("image/jpeg"),
    JPG("image/jpg"),
    PNG("image/png");

    private final String contentType;

    AvailibleFileTypes(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return contentType;
    }

    public String getContentType() {
        return this.contentType;
    }
}
