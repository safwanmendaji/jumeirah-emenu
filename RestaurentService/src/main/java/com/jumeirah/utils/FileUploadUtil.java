package com.jumeirah.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;

@Component
public class FileUploadUtil {

    private final String uploadDir = "uploads/images/";

    // List of allowed file extensions
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png");

    public String saveFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) return null;

        // Get file extension and check if it's allowed
        String fileName = file.getOriginalFilename();
        String fileExtension = getFileExtension(fileName);

        if (!ALLOWED_EXTENSIONS.contains(fileExtension.toLowerCase())) {
            throw new IllegalArgumentException("Unsupported file type: " + fileExtension);
        }

        String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(uniqueFileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return "/" + uploadDir + uniqueFileName;  // Return file URL
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) return ""; // No file extension
        return fileName.substring(dotIndex + 1);
    }
}
