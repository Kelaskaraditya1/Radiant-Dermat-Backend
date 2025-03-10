package com.StarkIndustries.RadientDermat.cloudinary.controller;

import com.StarkIndustries.RadientDermat.cloudinary.service.CloudinaryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/upload")
public class CloudinaryController {

    @Autowired
    public CloudinaryService cloudinaryService;

    @PostMapping("/file")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile multipartFile){
        if(multipartFile.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please upload proper file!!");
        Map fileData = this.cloudinaryService.uploadDataToCloud(multipartFile);
        String fileUrl = fileData.get("secure_url").toString();
        return ResponseEntity.status(HttpStatus.OK).body(fileUrl);
    }
}
