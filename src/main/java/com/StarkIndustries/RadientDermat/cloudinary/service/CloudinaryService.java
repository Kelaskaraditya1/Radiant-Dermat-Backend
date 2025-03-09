package com.StarkIndustries.RadientDermat.cloudinary.service;

import com.StarkIndustries.RadientDermat.cloudinary.configuration.CloudinaryConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryService {

    @Autowired
    public CloudinaryConfiguration cloudinaryConfiguration;

    public Map uploadDataToCloud(MultipartFile multipartFile){
        try{
            if(!multipartFile.isEmpty())
                return this.cloudinaryConfiguration.getCloudinaryConfigurations().uploader()
                    .upload(multipartFile.getBytes(),Map.of());
            else
                return null;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
