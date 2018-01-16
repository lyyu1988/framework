package cn.campus.platfrom.util.io;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;


public class FTPUtils {

    private static FTPUtils ftpUtils = new FTPUtils();
    private FTPClient ftpClient;

    @Value("${ftp.port}")
    private String port; // 服务器端口
    @Value("${ftp.username}")
    private String username; // 用户登录名
    @Value("${ftp.password}")
    private String password; // 用户登录密码
    @Value("${ftp.picurl}")
    private String maindir;		//文件上传主目录
    @Value("${ftp.servername}")
    private String servername;	//服务器地址

    private InputStream is; // 文件下载输入流

    /**
     * 私有构造方法
     */
    private FTPUtils() {
        if (null == ftpClient) {
            ftpClient = new FTPClient();
        }
    }

    /**
     * 获取FTPUtils对象实例
     * @return
     *      FTPUtils对象实例
     */
    public static FTPUtils getInstance () {
        return ftpUtils;
    }

    /**
     * 连接（配置通用连接属性）至服务器
     *
     * @param serverName
     *      服务器名称
     * @param remotePath
     *      当前访问目录
     * @return
     *      <b>true</b>：连接成功
     *      <br/>
     *      <b>false</b>：连接失败
     */
    public boolean connectToTheServer (String serverName, String remotePath) {
        // 定义返回值
        boolean result = false;
        try {
            // 连接至服务器，端口默认为21时，可直接通过URL连接
            ftpClient.connect(serverName, Integer.parseInt(port));
            // 登录服务器
            ftpClient.login(username, password);
            // 判断返回码是否合法
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                // 不合法时断开连接
                ftpClient.disconnect();
                // 结束程序
                return result;
            }
            // 设置文件操作目录
            ftpClient.mkd(remotePath);
            result = ftpClient.changeWorkingDirectory(remotePath);
            // 设置文件类型，二进制
            result = ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            // 设置缓冲区大小
            ftpClient.setBufferSize(3072);
            // 设置字符编码
            ftpClient.setControlEncoding("UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 上传文件至FTP服务器
     *
     * @param serverName
     *      服务器名称
     * @param storePath
     *      上传文件存储路径
     * @param fileName
     *      上传文件存储名称
     * @param is
     *      上传文件输入流
     * @return
     *      <b>true</b>：上传成功
     *      <br/>
     *      <b>false</b>：上传失败
     */
    public boolean storeFile (String fileName, InputStream is) {
        boolean result = false;
        try {
            // 连接至服务器,并且设置上传根目录
            result = connectToTheServer(servername, maindir);
            // 判断服务器是否连接成功
            if (result) {
                int lastIndex = fileName.lastIndexOf("/");
                String dir=fileName.substring(0, lastIndex+1);
                String f_name=fileName.substring(lastIndex+1);
                String[] dirs=dir.split("/");
//            	 ftpClient.mkd(dir) ;
                boolean flag=true;
                for(String d:dirs){
                    ftpClient.mkd(d);
                    ftpClient.changeWorkingDirectory(d);
                }

                if(flag){
                    // 上传文件
                    result = ftpClient.storeFile(f_name, is);
                    if(result){
                        System.out.println("上传成功");
                    }else{
                        System.out.println("上传失败");
                    }
                }else{
                    System.out.println("上传创建文件夹失败");
                }
            }
            // 关闭输入流
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 判断输入流是否存在
            if (null != is) {
                try {
                    // 关闭输入流
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 登出服务器并断开连接
            ftpUtils.logout();
        }
        return result;
    }

    public boolean storeFileForMainInfo (String fileName, InputStream is) {
        boolean result = false;
        try {
            // 连接至服务器,并且设置上传根目录
            result = connectToTheServer(servername, maindir);
            // 判断服务器是否连接成功
            if (result) {
                String dir=fileName.substring(0, 10);
                String f_name=fileName.substring(11);
                String[] dirs=dir.split("/");
//            	 ftpClient.mkd(dir) ;
                boolean flag=true;
                ftpClient.mkd("data");
                ftpClient.changeWorkingDirectory("data");
                for(String d:dirs){
                    ftpClient.mkd(d);
                    ftpClient.changeWorkingDirectory(d);
                }

                if(flag){
                    // 上传文件
                    result = ftpClient.storeFile(f_name, is);
                }else{
                    System.out.println("上传创建文件夹失败");
                }
            }
            // 关闭输入流
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 判断输入流是否存在
            if (null != is) {
                try {
                    // 关闭输入流
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 登出服务器并断开连接
            ftpUtils.logout();
        }
        return result;
    }
    /**
     * 下载FTP服务器文件至本地<br/>
     * 操作完成后需调用logout方法与服务器断开连接
     *
     * @param serverName
     *      服务器名称
     * @param remotePath
     *      下载文件存储路径
     * @param fileName
     *      下载文件存储名称
     * @return
     *      <b>InputStream</b>：文件输入流
     */
    public InputStream retrieveFile (String serverName, String remotePath, String fileName) {
        try {
            boolean result = false;
            // 连接至服务器
            result = connectToTheServer(serverName, remotePath);
            // 判断服务器是否连接成功
            if (result) {
                // 获取文件输入流
                is = ftpClient.retrieveFileStream(fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }

    /**
     * 下载FTP服务器文件至本地<br/>
     * 操作完成后需调用logout方法与服务器断开连接
     *
     * @param serverName
     *      服务器名称
     * @param remotePath
     *      下载文件存储路径
     * @param fileName
     *      下载文件存储名称
     * @return
     *      <b>InputStream</b>：文件输入流
     */
    public InputStream retrieveFile (String remotePath, String fileName) {
        try {
            boolean result = false;
            // 连接至服务器
            result = connectToTheServer(servername, remotePath);
            // 判断服务器是否连接成功
            if (result) {
                // 获取文件输入流
                is = ftpClient.retrieveFileStream(fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }

    /**
     * 删除FTP服务器文件
     *
     * @param serverName
     *      服务器名称
     * @param remotePath
     *      当前访问目录
     * @param fileName
     *      文件存储名称
     * @return
     *      <b>true</b>：删除成功
     *      <br/>
     *      <b>false</b>：删除失败
     */
    public boolean deleteFile (String fileName) {
        boolean result = false;
        // 连接至服务器
        result = connectToTheServer(servername, maindir);
        // 判断服务器是否连接成功
        if (result) {
            try {
                // 删除文件
                result = ftpClient.deleteFile(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // 登出服务器并断开连接
                ftpUtils.logout();
            }
        }
        return result;
    }

    /**
     * 检测FTP服务器文件是否存在
     *
     * @param serverName
     *      服务器名称
     * @param remotePath
     *      检测文件存储路径
     * @param fileName
     *      检测文件存储名称
     * @return
     *      <b>true</b>：文件存在
     *      <br/>
     *      <b>false</b>：文件不存在
     */
    public boolean checkFile (String serverName, String remotePath, String fileName) {
        boolean result = false;
        try {
            // 连接至服务器
            result = connectToTheServer(serverName, remotePath);
            // 判断服务器是否连接成功
            if (result) {
                // 默认文件不存在
                result = false;
                // 获取文件操作目录下所有文件名称
                String[] remoteNames = ftpClient.listNames();
                // 循环比对文件名称，判断是否含有当前要下载的文件名
                for (String remoteName: remoteNames) {
                    if (fileName.equals(remoteName)) {
                        result = true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 登出服务器并断开连接
            ftpUtils.logout();
        }
        return result;
    }

    /**
     * 检测FTP服务器文件是否存在
     *
     * @param serverName
     *      服务器名称
     * @param remotePath
     *      检测文件存储路径
     * @param fileName
     *      检测文件存储名称
     * @return
     *      <b>true</b>：文件存在
     *      <br/>
     *      <b>false</b>：文件不存在
     */
    public boolean checkFile (String remotePath, String fileName) {
        boolean result = false;
        try {
            // 连接至服务器
            result = connectToTheServer(servername, remotePath);
            // 判断服务器是否连接成功
            if (result) {
                // 默认文件不存在
                result = false;
                // 获取文件操作目录下所有文件名称
                String[] remoteNames = ftpClient.listNames();
                // 循环比对文件名称，判断是否含有当前要下载的文件名
                for (String remoteName: remoteNames) {
                    if (fileName.equals(remoteName)) {
                        result = true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 登出服务器并断开连接
            ftpUtils.logout();
        }
        return result;
    }

    /**
     * 登出服务器并断开连接
     *
     * @param ftp
     *      FTPClient对象实例
     * @return
     *      <b>true</b>：操作成功
     *      <br/>
     *      <b>false</b>：操作失败
     */
    public boolean logout () {
        boolean result = false;
        if (null != is) {
            try {
                // 关闭输入流
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (null != ftpClient) {
            try {
                // 登出服务器
                result = ftpClient.logout();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // 判断连接是否存在
                if (ftpClient.isConnected()) {
                    try {
                        // 断开连接
                        ftpClient.disconnect();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }

    /**
     * 删除FTP服务器文件 夹
     *
     * @param serverName
     *      服务器名称
     * @param remotePath
     *      当前访问目录
     * @param fileName
     *      文件存储名称
     * @return
     *      <b>true</b>：删除成功
     *      <br/>
     *      <b>false</b>：删除失败
     */
    public boolean deleteDir(String ftpPath){
        boolean result = false;
        // 连接至服务器
        result = connectToTheServer(servername, maindir);
        // 判断服务器是否连接成功
        if (result) {
            try {
                // 删除文件
                FTPFile[] files = ftpClient.listFiles(ftpPath);
                for(FTPFile f:files){
                    String path = ftpPath+f.getName();
                    if(f.isFile()){
                        // 是文件就删除文件
                        ftpClient.deleteFile(path);
                        System.out.println("删除文件"+path);
                    }else if(f.isDirectory()){
                        // 登出服务器并断开连接
                        ftpUtils.logout();
                        deleteDir(path);
                    }
                }
                // 每次删除文件夹以后就去查看该文件夹下面是否还有文件，没有就删除该空文件夹
                FTPFile[] files2 = ftpClient.listFiles(ftpPath);
                if(files2.length==0){
                    result = ftpClient.removeDirectory(ftpPath);
                }else{
                    result = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // 登出服务器并断开连接
                ftpUtils.logout();
            }
        }
        return result;
    }
}

