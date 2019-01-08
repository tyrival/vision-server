package com.tyrival.utils;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/25
 * @Version: V1.0
 */
public class FileUtil {

    /**
     * 保存文件
     * @param file
     * @param filePath
     * @param fileName
     * @throws Exception
     */
    public static void save(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    /**
     * 删除文件
     * @param filePath
     * @return
     */
    public static boolean delete(String filePath) {
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
