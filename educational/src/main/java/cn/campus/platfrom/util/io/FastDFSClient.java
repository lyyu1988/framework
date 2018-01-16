package cn.campus.platfrom.util.io;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient;
import org.springframework.stereotype.Component;

import java.io.*;

/*
 * FastDFS客户端类
 */
@Component
public class FastDFSClient implements FilePersistence {

    /**
     * 根据本地文件名上传
     * @Create_by:xiedan
     * @param fileName 本地文件名，包含全路径和扩展名
     * @param extName  文件扩展名
     * @param meta_list元数据,可为空
     * @return string[0] groupName 分组名, string[1] remoteFileName 服务器存储文件名
     * @throws Exception
     */
    @Override
    public String[] uploadFile(String fileName, String extName,
                               String[] meta_list) throws Exception {
        StorageClient storageClient = FastDFSFactory.getInstance().getStorageClient();
        NameValuePair[] nameValuePairs = getNameValuePair(meta_list);
        return storageClient.upload_file(fileName, extName, nameValuePairs);
    }


    /**
     * 根据字节流上传
     * @Create_by:xiedan
     * @param fileBuff 上传文件字节流
     * @param extName  文件扩展名
     * @param meta_list元数据,可为空
     * @return
     * @throws Exception
     */
    @Override
    public String[] uploadFile(byte[] fileBuff,String fileName, String extName,
                               String[] meta_list) throws Exception {
        StorageClient storageClient = FastDFSFactory.getInstance().getStorageClient();
        NameValuePair[] nameValuePairs = getNameValuePair(meta_list);
        return storageClient.upload_file(fileBuff, extName, nameValuePairs);
    }

    /**
     * 根据File对象上传
     * @Create_by:xiedan
     * @param file File对象
     * @param extName  文件扩展名
     * @param meta_list元数据,可为空
     * @return
     * @throws Exception
     */
    @Override
    public String[] uploadFile(File file,String fileName, String extName, String[] meta_list) throws Exception {
        StorageClient storageClient = FastDFSFactory.getInstance().getStorageClient();
        InputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] fileBuff = new byte[(int) file.length()];
        fis.read(fileBuff);
        bos.write(fileBuff);
        fis.close();
        bos.close();
        NameValuePair[] nameValuePairs = getNameValuePair(meta_list);
        return storageClient.upload_file(fileBuff, extName, nameValuePairs);
    }

    /**
     * 下载文件
     * @Create_by:xiedan
     * @param groupName 组名，上传时服务器返回
     * @param remoteFileName 远程文件名，上传时服务器返回
     * @return byte[] 下载文件字节流
     * @throws MyException
     * @throws Exception
     */
    @Override
    public InputStream downloadFile(String groupName,String remoteFileName) throws Exception {
        StorageClient storageClient = FastDFSFactory.getInstance().getStorageClient();
        byte[] data = storageClient.download_file(groupName, remoteFileName);
        return new ByteArrayInputStream(data);
    }

    /**
     * 删除文件
     * @Create_by:xiedan
     * @param groupName
     * @param remoteFileName
     * @return 返回0表示成功
     * @throws Exception
     */
    @Override
    public boolean deleteFile(String groupName, String remoteFileName) throws Exception {
        StorageClient storageClient = FastDFSFactory.getInstance().getStorageClient();
        return storageClient.delete_file(groupName, remoteFileName)==0?true:false;
    }

    /**
     * 修改文件
     * @Create_by:xiedan
     * @param groupName
     * @param remoteFileName
     * @param fileName
     * @param extName
     * @param meta_list
     * @return string[0] groupName 分组名, string[1] remoteFileName 服务器存储文件名
     * @throws Exception
     */
    @Override
    public String[] modifyFile(String groupName, String remoteFileName,
                               String fileName,String extName,String[] meta_list) throws Exception {
        StorageClient storageClient = FastDFSFactory.getInstance().getStorageClient();
        storageClient.delete_file(groupName, remoteFileName);
        NameValuePair[] nameValuePairs = getNameValuePair(meta_list);
        return storageClient.upload_file(fileName, extName, nameValuePairs);
    }

    /**
     * 修改文件
     * @Create_by:xiedan
     * @param groupName
     * @param remoteFileName
     * @param fileName
     * @param extName
     * @param meta_list
     * @return string[0] groupName 分组名, string[1] remoteFileName 服务器存储文件名
     * @throws Exception
     */
    @Override
    public String[] modifyFile(String groupName, String remoteFileName,
                               byte[] fileBuff,String fileName, String extName, String[] meta_list) throws Exception {
        StorageClient storageClient = FastDFSFactory.getInstance().getStorageClient();
        storageClient.delete_file(groupName, remoteFileName);
        return this.uploadFile(fileBuff,fileName, extName, meta_list);
    }

    /**
     * 修改文件
     * @Create_by:xiedan
     * @param groupName
     * @param remoteFileName
     * @param fileName
     * @param extName
     * @param meta_list
     * @return string[0] groupName 分组名, string[1] remoteFileName 服务器存储文件名
     * @throws Exception
     */
    @Override
    public String[] modifyFile(String groupName, String remoteFileName,
                               File file,String fileName, String extName, String[] meta_list) throws Exception {
        StorageClient storageClient = FastDFSFactory.getInstance().getStorageClient();
        storageClient.delete_file(groupName, remoteFileName);
        return this.uploadFile(file,fileName, extName, meta_list);
    }

    private NameValuePair[] getNameValuePair(String[] meta_list){
        NameValuePair[] nameValuePairs = null;
        if(null!=meta_list && meta_list.length>0){
            nameValuePairs=new NameValuePair[meta_list.length];
            for(int i=0,length=meta_list.length;i<length;i++){
                nameValuePairs[i]=new NameValuePair(meta_list[i]);
            }
        }
        return nameValuePairs;
    }

    @Override
    public boolean checkFile(String path, String filename) {
        StorageClient storageClient = FastDFSFactory.getInstance().getStorageClient();
        boolean result=false;
        try {
            storageClient.download_file(path, filename);
            result=true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
}

