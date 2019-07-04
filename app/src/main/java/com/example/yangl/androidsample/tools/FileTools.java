
package com.example.yangl.androidsample.tools;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2019/6/29
 * version:
 * update:
 */
public class FileTools {

    public static int ALREADY_EXIST = 0;
    public static int CREATE_FAILED = -1;
    public static int CREATE_SUCCESS = 1;

    public static int createFile(String filePath,String fileName){
        File file = new File(filePath+File.separator+fileName);

        if(file.exists()){
            return ALREADY_EXIST;
        }

        File parentDir = file.getParentFile();
        while (!parentDir.exists()){
            createFileDir(parentDir);
        }

        try {
            boolean newFile = file.createNewFile();
            if(newFile){
                return CREATE_SUCCESS;
            }else {
                return ALREADY_EXIST;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ALREADY_EXIST;
        }
    }


    private static int createFileDir(File file){
        if(file.exists()){
            return ALREADY_EXIST;
        }

        File parentDir = file.getParentFile();
        while (!parentDir.exists()){
            createFileDir(file);
        }

        if(file.mkdirs()){
            return CREATE_SUCCESS;
        }else {
            return CREATE_FAILED;
        }
    }
}
