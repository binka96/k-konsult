package com.k_konsult.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.k_konsult.Service.FileService;

import java.util.List;
import java.util.Map;

@RestController
@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path = "/K-Konsult/file")
public class FileUploadController {


    @Autowired
    private FileService fileService;

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/upload/{title}")
    public ResponseEntity<Map<String , Boolean>> handleFileUpload(@PathVariable String title , @RequestParam("demo[]") MultipartFile[] files) {
       return fileService.handleFileUpload(title, files);
    }


    @GetMapping("/Get/images/{folder}/{imageName}")
    public ResponseEntity<FileSystemResource> getImage(@PathVariable String folder , @PathVariable String imageName) {
        return fileService.getImage(folder, imageName);
    }

    @GetMapping("/Get/files/{title}")
    public  ResponseEntity<List<String>> getFiles(@PathVariable String title) {
        return ResponseEntity.ok(fileService.getFiles(title));
    }
    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/images/{folder}/{imageName}")
    public ResponseEntity<Map<String , String>> deleteImage(@PathVariable String folder  , @PathVariable String imageName) {
        return ResponseEntity.ok(fileService.deleteImage(folder, imageName));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/delete-directory/{folder}")
    public ResponseEntity<Map<String , String>> deleteDirectory(@PathVariable String folder) {
        return ResponseEntity.ok(fileService.deleteDirectory(folder));
    }

}