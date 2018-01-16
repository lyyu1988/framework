package cn.campus.platfrom.util.io;

import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Component
public class FtpFileClient implements FilePersistence {

    @Override
    public String[] uploadFile(String fileName, String extName,
                               String[] meta_list) throws Exception {
        File file=new File(fileName);
        return this.uploadFile(file, fileName, extName, meta_list);
    }

    @Override
    public String[] uploadFile(byte[] fileBuff, String fileName,
                               String extName, String[] meta_list) throws Exception {
        ByteArrayInputStream is=new ByteArrayInputStream(fileBuff);
        FTPUtils.getInstance().storeFile(fileName, is);
        is.close();
        String[] result=new String[2];
        result[0]=fileName;
        result[1]="";
        return result;
    }

    @Override
    public String[] uploadFile(File file, String fileName, String extName,
                               String[] meta_list) throws Exception {
        if (file.getName().length() > 0) {
            FileInputStream fis=new FileInputStream(file);
            FTPUtils.getInstance().storeFile(fileName, fis);
            fis.close();
        }
        String[] result=new String[2];
        result[0]=fileName;
        result[1]="";
        return result;
    }

    @Override
    public InputStream downloadFile(String path, String filename) throws Exception {
        return FTPUtils.getInstance().retrieveFile(path, filename);
    }

    @Override
    public boolean deleteFile(String path, String filename) throws Exception {
        return FTPUtils.getInstance().deleteFile(path+"/"+filename);
    }

    @Override
    public String[] modifyFile(String groupName, String remoteFileName,
                               String fileName, String extName, String[] meta_list)
            throws Exception {
        this.uploadFile(fileName, extName, meta_list);
        this.deleteFile(groupName, remoteFileName);
        String[] result=new String[2];
        result[0]=fileName;
        result[1]="";
        return result;
    }

    @Override
    public String[] modifyFile(String groupName, String remoteFileName,
                               byte[] fileBuff, String fileName, String extName, String[] meta_list)
            throws Exception {
        this.uploadFile(fileBuff, fileName, extName, meta_list);
        this.deleteFile(groupName, remoteFileName);
        String[] result=new String[2];
        result[0]=fileName;
        result[1]="";
        return result;
    }

    @Override
    public String[] modifyFile(String groupName, String remoteFileName,
                               File file, String fileName, String extName, String[] meta_list)
            throws Exception {
        this.uploadFile(file, fileName, extName, meta_list);
        this.deleteFile(groupName, remoteFileName);
        String[] result=new String[2];
        result[0]=fileName;
        result[1]="";
        return result;
    }

    @Override
    public boolean checkFile(String path, String filename) {
        return FTPUtils.getInstance().checkFile(path, filename);
    }

}

