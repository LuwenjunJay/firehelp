package com.example.utils;

import com.example.controller.SolveController;

import java.io.File;

public class DeleteImages {

    public static void delete(String id){
        if(id.equals("1")){
            if(SolveController.oldPath.length() > 0 && SolveController.oldPath.equals(SolveController.oldPersonPath)){
                File file = new File(SolveController.oldPath);
                try{
                    file.delete();
                }catch (Exception e){
                    System.out.println("error");
                }

            }

            if(SolveController.oldPath.length() > 0){
                File file = new File(SolveController.oldPath);
                try{
                    file.delete();
                }catch (Exception e){
                    System.out.println("error");
                }

            }

            if(SolveController.oldPersonPath.length() > 0){
                File file = new File(SolveController.oldPersonPath);
                try{
                    file.delete();
                }catch (Exception e){
                    System.out.println("error");
                }
            }
        }
    }
}
