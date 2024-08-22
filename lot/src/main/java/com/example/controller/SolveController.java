package com.example.controller;

import com.example.utils.Address;
import com.example.domain.Image;
import com.example.mapper.UserMapper;
import com.example.socket.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@RestController
@RequestMapping("/solve")
public class SolveController {

    @Autowired
    private UserMapper userMapper;

    public static String oldPath = "";
    public static String oldPersonPath = "";


    @GetMapping("/submit/{x}/{y}/{z}/{smoke}/{tem}/{ang}")
    public void solveIt(@PathVariable Integer x,@PathVariable Integer y ,@PathVariable Integer z, @PathVariable float smoke, @PathVariable Integer tem, @PathVariable Integer ang) {


        smoke = (int)smoke;
        System.out.println(x + "," + y + "," + z + "," + smoke + "," + tem  + "," + ang);
        WebSocket.sendToOneClient("2","1", x + "," + y + "," + z + "," + smoke + "," + tem  + "," + ang);
    }


    @PostMapping("/images/{isperson}")
    public void getImages(@RequestBody Image image,@PathVariable Integer isperson) {

        String base64Image = image.getFile();
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        String fileName = UUID.randomUUID().toString() + ".png";
        String filePath = Address.getAdress() + fileName;
        File file = new File(filePath);

        try {
            // 创建文件输出流
            FileOutputStream fos = new FileOutputStream(file);

            // 将解码后的字节数组写入文件
            fos.write(imageBytes);

            // 关闭流
            fos.close();
            String message = "";
            if(isperson == 1){

               message = "/file/"+fileName+","+"/file/"+fileName + "," + "5";
            }else{

               message = "/file/"+fileName;
            }
            WebSocket.sendToOneClient("2","1",message);

            if(oldPath.length() > 0 && !oldPath.equals(oldPersonPath)) {

                    File file1 = new File(oldPath);
                    try {
                        file1.delete();
                    } catch (Exception e) {
                        System.out.println("error");
                    }



            }


            if(oldPersonPath.length() > 0 && isperson == 1){
                File file1 = new File(oldPersonPath);
                try {
                    file1.delete();

                    oldPersonPath = "";
                } catch (Exception e) {
                    System.out.println("error");
                }
            }

            oldPath = filePath;

            if(isperson == 1){
                oldPersonPath = filePath;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}







































































































































































