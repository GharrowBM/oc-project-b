package fr.gharrowbm.occhatopbackend.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.annotation.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/pictures")
@Hidden
public class ImageController {

    private final Path storageLocation = Paths.get("src/main/resources/pictures");

    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<UrlResource> serveImage(@PathVariable String filename) {
        try {
            Path file = storageLocation.resolve(filename);
            URI uri = file.toUri();
            UrlResource resource = new UrlResource(uri);

            if (resource.exists() || resource.isReadable()) {
                String contentType = Files.probeContentType(file);
                if (contentType == null) {
                    contentType = "application/octet-stream";
                }

                return ResponseEntity
                        .ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                throw new RuntimeException("Could not read file: " + filename);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error: " + ex.getMessage());
        }
    }
}