package com.github.zhoujiale.commons.util.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author zhou
 * @className FileUtils
 * @descrption 文件工具类
 * @date 2022/9/2 14:15
 */
@Slf4j
public class FileUtils {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private static final String ZIP = "zip";
    /**
     * @param file
     * @return java.lang.String
     * @description 获取文件后缀
     * @author zhou
     * @create 2021/8/10 15:45
     **/
    public static String getSuffix(File file) {
        String name = file.getName();
        return name.substring(name.lastIndexOf(".") + 1);
    }

    /**
     * @param file 文件
     * @return java.lang.String
     * @description 读取文件到字符串
     * @date 2022/9/2 14:21
     * @author zhou
     */
    public static String getStrByFile(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String content;
            while ((content = bufferedReader.readLine()) != null) {
                stringBuilder.append(content).append(LINE_SEPARATOR);
            }
        } catch (Exception e) {
            log.error("read file to string error");
            log.error(e.getMessage(), e);
            throw new RuntimeException("read file error!");
        }
        return stringBuilder.toString();
    }

    /**
     * @description 获取压缩文件
     * @date 2022/8/15 13:35
     * @author zhou
     * @param filePathList 文件路径列表
     * @param zipFilePath 压缩文件路径
     * @param zipFileParentPath 压缩文件父路径
     * @return java.io.File
     */
    public static File getZipFile(List<String> filePathList, String zipFilePath, String zipFileParentPath){
        File zipFile = new File(zipFilePath);
        if (!zipFile.exists()) {
            List<File> fileList = new ArrayList<>();
            for (String s : filePathList) {
                File subFile = new File(s);
                if (!subFile.exists()) {
                    continue;
                }
                fileList.add(subFile);
            }
            if (!CollectionUtils.isEmpty(fileList) && fileList.size() == 1 &&
                    getSuffix(fileList.get(0)).equals(ZIP)) {
                //只有一个文件并且是zip
                zipFile = fileList.get(0);
            } else {
                //压缩文件
                zipFile(fileList, zipFile, zipFileParentPath);
            }
        }
        return zipFile;
    }

    /**
     * @description
     * @date 2022/9/2 15:30
     * @author zhou
     * @param srcFileList 文件路径列表
     * @param zipFile 压缩文件
     * @param zipFileParentPath 压缩文件父路径
     * @return java.io.File
     */
    private static File zipFile(List<File> srcFileList, File zipFile, String zipFileParentPath) {
        FileInputStream fileInputStream = null;
        int length = zipFileParentPath.length();
        try(ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile.toPath()))) {
            if (!zipFile.exists()) {
                //不存在则创建
                zipFile.createNewFile();
            } else {
                //已存在则删除
                zipFile.deleteOnExit();
            }
            ZipEntry zipEntry;
            for (File file : srcFileList) {
                //读取源文件
                fileInputStream = new FileInputStream(file);
                zipEntry = new ZipEntry(file.getAbsolutePath().substring(length));
                zipOutputStream.putNextEntry(zipEntry);
                int len;
                byte[] buffer = new byte[1024];
                while ((len = fileInputStream.read(buffer)) > 0) {
                    zipOutputStream.write(buffer, 0, len);
                }
            }
        } catch (Exception e) {
            log.error("zip file error");
            log.error(e.getMessage(), e);
            throw new RuntimeException("zip file error!");
        } finally {
            if (null != fileInputStream){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    log.error("fileInputStream close error");
                    log.error(e.getMessage(),e);
                    throw new RuntimeException("fileInputStream close error");
                }
            }
        }
        return zipFile;
    }

}
