package com.example.demo.services;

import com.example.demo.models.Post;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {

    @Value("${upload.path}")
    private String uploadPath;

    public Post uploadPostFiles(MultipartFile titleImage,MultipartFile p1Image,
                                MultipartFile p2Image,Post post) throws IOException {
        if (titleImage != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            if (!titleImage.isEmpty()) {
                File image = new File(uploadPath+"/"+ post.getTitleImage());
                image.delete();
                String resultTitleImageName = UUID.randomUUID().toString() + "." + titleImage.getOriginalFilename();
                titleImage.transferTo(new File(uploadPath + "/" + resultTitleImageName));
                post.setTitleImage(resultTitleImageName);
            }
            if (!p1Image.isEmpty()) {
                File image = new File(uploadPath+"/"+ post.getP1Image());
                image.delete();
                String resultp1ImageName = UUID.randomUUID().toString() + "." + p1Image.getOriginalFilename();
                p1Image.transferTo(new File(uploadPath + "/" + resultp1ImageName));
                post.setP1Image(resultp1ImageName);
            }
            if (!p2Image.isEmpty()) {
                File image = new File(uploadPath+"/"+ post.getP2Image());
                image.delete();
                String resultp2ImageName = UUID.randomUUID().toString() + "." + p2Image.getOriginalFilename();
                p2Image.transferTo(new File(uploadPath + "/" + resultp2ImageName));
                post.setP2Image(resultp2ImageName);
            }
        }
        return post;
    }
    public boolean deletePostFiles(Post post) throws IOException{
        File titleImage = new File(uploadPath+"/"+ post.getTitleImage());
        titleImage.delete();
        File p1Image = new File(uploadPath+"/"+ post.getP1Image());
        p1Image.delete();
        File p2Image = new File(uploadPath+"/"+ post.getP2Image());
        p2Image.delete();
        return true;
    }
}
