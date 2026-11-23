package com.api_test.book.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageStorageService {

    private final Path uploadDir;

    public ImageStorageService(@Value("${app.upload.dir:uploads}") String uploadDirectory) {
        this.uploadDir = Paths.get(uploadDirectory).toAbsolutePath().normalize();
        try {
            Files.createDirectories(uploadDir);
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory", e);
        }
    }

    public Path getUploadDir() {
        return uploadDir;
    }

    public String store(MultipartFile file) {
        String originalName = file.getOriginalFilename();
        String extension = "";
        if (originalName != null && originalName.contains(".")) {
            extension = originalName.substring(originalName.lastIndexOf("."));
        }
        String filename = UUID.randomUUID().toString() + extension;
        Path target = uploadDir.resolve(filename);

        try {
            Files.copy(file.getInputStream(), target);
            return "/uploads/" + filename;
        } catch (IOException e) {
            throw new RuntimeException("Could not store file: " + filename, e);
        }
    }
}