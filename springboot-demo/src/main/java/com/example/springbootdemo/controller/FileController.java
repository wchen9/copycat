package com.example.springbootdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile() throws IOException {
        Path path = Paths.get("C:\\git\\copycat\\springboot-demo\\src\\main\\resources\\files\\a.xlsx");

        Resource resource = new InputStreamResource(Files.newInputStream(path));

        return ResponseEntity.ok()
                .contentType(org.springframework.http.MediaType.parseMediaType("application/octet-stream"))
                .header("Content-Disposition", "attachment; filename=" + path.getFileName().toString())
                .body(resource);
    }

}
