package com.tyrival.coder.util;


import java.io.*;

public class FileUtil {
    /**
     * 读取文件内容
     *
     * @param filePath
     * @return
     */
    public static String read(String filePath) {
        BufferedReader br = null;
        String line = null;
        StringBuffer buf = new StringBuffer();

        try {
            // 根据文件路径创建缓冲输入流
            br = new BufferedReader(new FileReader(filePath));

            // 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中
            while ((line = br.readLine()) != null) {
                buf.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    br = null;
                }
            }
        }

        return buf.toString();
    }

    /**
     * 将内容回写到文件中
     *
     * @param content
     */
    public static void write(String path, String fileName, String content) throws Exception {
        mkdir(path);
        String filePath = path + fileName;
        File file = new File(filePath);
        if (file.exists()) {
            throw new Exception("文件已存在，无法重复生成: " + filePath);
        }
        BufferedWriter bw = null;
        try {
            // 根据文件路径创建缓冲输出流
            bw = new BufferedWriter(new FileWriter(filePath));
            // 将内容写入文件中
            bw.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    bw = null;
                }
            }
        }
    }
    public void fileAppender(String fileName,String content) throws IOException{

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = null;
        // 一行一行的读
        StringBuilder sb = new StringBuilder();
        sb.append(content);
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            sb.append("\r\n");
        }
        reader.close();

        //写回去
        RandomAccessFile mm = new RandomAccessFile(fileName, "rw");
        mm.writeBytes(sb.toString());
        mm.close();
    }


    public static File inputStreamToFile(InputStream inputStream, String fileName) throws Exception {

        File file = new File(fileName);
        OutputStream outputStream = new FileOutputStream(file);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        outputStream.close();
        inputStream.close();
        return file;
    }

    public static void mkdir(String filePath) throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
