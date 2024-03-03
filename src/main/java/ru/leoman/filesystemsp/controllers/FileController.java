package ru.leoman.filesystemsp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leoman.filesystemsp.File;
import ru.leoman.filesystemsp.service.FileServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class FileController {
    private final FileServiceImpl service;
    @Autowired
    public FileController(FileServiceImpl service) {
        this.service = service;
    }
    @GetMapping("/files")
    public List<File> getFiles() throws IOException {
        return service.readAll();
    }
    @GetMapping("files/{uuid}")
    public File  getFile(@PathVariable UUID uuid) throws IOException {
        return service.read(uuid);
    }
    @PostMapping("/files")
    public ResponseEntity<File> createDevice(@RequestBody File file) throws IOException {
        service.create(file);
        return new ResponseEntity<>(file, HttpStatus.CREATED);
    }
    @DeleteMapping("/files/{uuid}")
    public ResponseEntity<Void> deleteFile(@PathVariable UUID uuid) throws IOException {
        service.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}
