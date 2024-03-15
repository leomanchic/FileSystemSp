package ru.leoman.filesystemsp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.leoman.filesystemsp.service.FileService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class InfoController {
    private final FileService fileService;
    @Autowired
    public InfoController(FileService fileService){
        this.fileService = fileService;
    }
    @GetMapping("/info")
    public ResponseEntity<Map<String, String>> info() throws IOException {
        Map<String,String>  info = new HashMap<>();
        info.put("name", "FileSystem");
        info.put("version", "1.0.0");
        info.put("author", "lemanchic");
        info.put("year","2024");
        info.put("filesInSystem", String.valueOf(fileService.readAll().size()));
        return ResponseEntity.ok(info);
    }
}
