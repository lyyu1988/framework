package cn.campus.platfrom.util.io;

import java.io.File;
import java.io.InputStream;

public interface FilePersistence {

    String[] uploadFile(String fileName, String extName, String[] meta_list) throws Exception;

    String[] uploadFile(byte[] fileBuff, String fileName, String extName, String[] meta_list) throws Exception;

    String[] uploadFile(File file, String fileName, String extName, String[] meta_list) throws Exception;

    InputStream downloadFile(String path, String filename) throws Exception;

    boolean deleteFile(String path, String filename) throws Exception;

    String[] modifyFile(String groupName, String remoteFileName, String fileName, String extName, String[] meta_list) throws Exception;

    String[] modifyFile(String groupName, String remoteFileName, byte[] fileBuff, String fileName, String extName, String[] meta_list) throws Exception;

    String[] modifyFile(String groupName, String remoteFileName, File file, String fileName, String extName, String[] meta_list) throws Exception;

    boolean checkFile(String path, String filename);
}
