package kr.co.wave.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class ImageHandler {

    @Value("${file.upload.path}")
    private String uploadPath;

    public Resource getProductImage(String imageName) {
        return new FileSystemResource(uploadPath + File.separator + imageName);
    }


    public Map<String, String> saveFiles(List<MultipartFile> files)throws RuntimeException{

        if(files == null || files.size() == 0){
            return null;
        }
        Map<String, String> uploadNames = new HashMap<>();
        for (MultipartFile multipartFile : files) {
            String fieldName = multipartFile.getName();
            String originalFilename = multipartFile.getOriginalFilename();
            String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
            String savedName = UUID.randomUUID().toString() + ext;

            Path savePath = Paths.get(uploadPath, savedName);

            try {
                Files.copy(multipartFile.getInputStream(), savePath);
                uploadNames.put(fieldName, savedName);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }

        }//end for
        return uploadNames;
    }
}
