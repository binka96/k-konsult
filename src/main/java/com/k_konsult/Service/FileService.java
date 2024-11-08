package com.k_konsult.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class FileService {
    private static final String UPLOAD_DIR = "/home/arrow/Desktop/k-konsult/k-konsult/picture/"; // Заменете с вашия път

    public ResponseEntity<Map<String , Boolean>> handleFileUpload(String title , MultipartFile[] files ){
        Map<String, Boolean> response = new HashMap<>();
        Path directoryPath = Paths.get(UPLOAD_DIR + title);
        if(!Files.exists(directoryPath)){
            try {
            Files.createDirectories(directoryPath);
            for (MultipartFile file : files) {
                try {
                    // Записване на файла в определена директория
                    
                    Path path = Paths.get(UPLOAD_DIR + title+"/" + file.getOriginalFilename());
                    Files.copy(file.getInputStream(), path);
                } catch (IOException e) {
                    e.printStackTrace();
                
                    response.put("success", false);
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
                }
            } 
            response.put("success", true);
            return ResponseEntity.ok(response);
            }
            catch(IOException e){
                response.put("success", false);
                return ResponseEntity.ok(response);
            }
        }else{
            for (MultipartFile file : files) {
                try {
                    // Записване на файла в определена директория
                    Path path = Paths.get(UPLOAD_DIR + title+ "/" + file.getOriginalFilename());
                    Files.copy(file.getInputStream(), path);
                } catch (IOException e) {
                    e.printStackTrace();
                
                    response.put("success", false);
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
                }
            } 
            response.put("success", true);
            return ResponseEntity.ok(response);
        }
    }

    public ResponseEntity<FileSystemResource>  getImage(String folder , String imageName ){
        File file = new File(UPLOAD_DIR+folder+"/", imageName);
        
        if (!file.exists()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        FileSystemResource resource = new FileSystemResource(file);
        
        // Задайте заглавките за отговор
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "image/jpg"); // Можете да промените в зависимост от формата на изображението
        //headers.add("Content-Disposition", "inline; filename=" + imageName); // Служи за опции при сваляне на файла

        return new ResponseEntity<>(resource,headers,  HttpStatus.OK);
    }

    public List<String> getFiles(String title){
        File directory = new File(UPLOAD_DIR+title);
        String[] fileNames = directory.list(); // Получаване на имената на файловете в директорията

        if (fileNames != null) {
            return Arrays.asList(fileNames); // Превръщане в списък
        } else {
            return List.of(); // Връщане на празен списък, ако директорията е празна или не съществува
        }
    }

    public Map<String , String> deleteImage(String folder , String imageName){
        File file = new File(UPLOAD_DIR+folder+"/", imageName);
        Map<String, String> response = new HashMap<>();
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                response.put("message", "Image deleted successfully");
                return response;
            } else {
                response.put("message", "Failed to delete the image");
                return response;
            }
        } else {
            response.put("message", "Image not found");
            return response;
        }
    }

    public Map<String , String> deleteDirectory(String folder ){
        File directory = new File(UPLOAD_DIR+folder);
        Map<String, String> response = new HashMap<>();
        if (!directory.exists()) {
            response.put("message", "Directory does not exist!");
            return response;
        }

        if (!directory.isDirectory()) {
            response.put("message", "Provided path is not a directory");
            return response;
        }

        try {
            deleteDirectoryRecursively(directory);
            response.put("message", "Directory deleted successfully");
            return response;
        } catch (Exception e) {
            response.put("message", "Error while deleting directory: " + e.getMessage());

            return response;
        }
    }

    private void deleteDirectoryRecursively(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectoryRecursively(file);
                } else {
                    file.delete();
                }
            }
        }
        directory.delete();
    }
}
