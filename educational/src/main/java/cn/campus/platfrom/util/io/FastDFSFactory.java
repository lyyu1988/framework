package cn.campus.platfrom.util.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 客户端工厂类
 */
public class FastDFSFactory {

    private static Logger log= LogManager.getLogger(FastDFSFactory.class);

    /**
     * 创建存储客户端对象
     */
    private static FastDFSFactory fastDFSFactory=new FastDFSFactory();

    private String fdfsConfigFilename="fdfs_client.conf";
    private StorageClient storageClient = null;

    private FastDFSFactory(){
        ClassPathResource classPathResource = new ClassPathResource(fdfsConfigFilename);
        String path="";
        try {
            path = classPathResource.getFile().getPath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            ClientGlobal.init(path);
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = tracker.getStoreStorage(trackerServer);
            if (storageServer == null) {
                log.error("getStoreStorage fail, error code: " + tracker.getErrorCode());
            }
            storageClient = new StorageClient(trackerServer, storageServer);
        } catch (FileNotFoundException e) {
            log.error(e);
        } catch (IOException e) {
            log.error(e);
        } catch (MyException e) {
            log.error(e);
        }
    }

    public static FastDFSFactory getInstance() {
        return fastDFSFactory;
    }

    public StorageClient getStorageClient() {
        return storageClient;
    }

    public void setStorageClient(StorageClient storageClient) {
        this.storageClient = storageClient;
    }

}

