package com.ensah.gestionExamens.web.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileController {

    @GetMapping("/downloadFile/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) throws IOException {
        // Récupérer le chemin complet du fichier PDF
        Path filePath = Paths.get("src/main/resources/static/examenFile/" + fileName);
       
        // C:\Users\po\OneDrive\Bureau\JEE-PROJECT\GestionExamens\src\main\java\com\ensah\gestionExamens\web\controller\FileController.java
       // C:\Users\po\OneDrive\Bureau\JEE-PROJECT\GestionExamens\src\main\resources\static\examenFile\pv14
        
        // Charger le fichier en tant que ressource ByteArrayResource
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(filePath));
        
        // Construire l'en-tête de réponse pour le téléchargement
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        
        // Renvoyer la réponse avec le fichier PDF et l'en-tête approprié
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(Files.size(filePath))
                .body(resource);
    }
}

