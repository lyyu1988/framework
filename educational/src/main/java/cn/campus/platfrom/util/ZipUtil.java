package cn.campus.platfrom.util;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import java.io.*;

/**
 * Created by Administrator on 2017/7/11 0011.
 */
public class ZipUtil {

    private static String ZIP_ENCODEING = "GBK";

    public static void createDirectory(String path){
        path=path.replace("\\\\", File.separator);
        path=path.replace("/", File.separator);
        File dir = new File(path);
        if (dir.isDirectory()) {
            if (!dir.exists()) {
                dir.mkdirs();
            }
        } else {
            path = path.substring(0, path.lastIndexOf(File.separator));
            dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        }
    }

    public static File unzip(File zipFile, String saveFileDir) throws IOException {
        File dir = new File(saveFileDir);
        ZipArchiveInputStream zipArchiveInputStream = new ZipArchiveInputStream(new FileInputStream(zipFile), ZIP_ENCODEING);
        ArchiveEntry archiveEntry;
        while ((archiveEntry = zipArchiveInputStream.getNextEntry()) != null) {
            // 获取文件名
            String entryFileName = archiveEntry.getName();
            // 构造解压出来的文件存放路径
            String entryFilePath = saveFileDir + entryFileName;
            createDirectory(entryFilePath);
            OutputStream os = null;
            try {
                // 把解压出来的文件写到指定路径
                File entryFile = new File(entryFilePath);
                if(entryFile.isDirectory()){
                    entryFile.mkdirs();
                } else {
                    os = new BufferedOutputStream(new FileOutputStream(
                            entryFile));
                    byte[] buffer = new byte[1024];
                    int len = -1;
                    while ((len = zipArchiveInputStream.read(buffer)) != -1) {
                        os.write(buffer, 0, len);
                    }
                }
            } catch (IOException e) {
                throw new IOException(e);
            } finally {
                if (os != null) {
                    os.flush();
                    os.close();
                }
            }
        }
        return dir;
    }

    /**
     * 把文件压缩成zip格式
     *
     * @param
     * @param zipFilePath 压缩后的zip文件路径   ,如"D:/test/aa.zip";
     */
    public static File compressFilesZip(File file, String zipFilePath) throws IOException {
        ZipArchiveOutputStream zaos = null;
        File zipFile = new File(zipFilePath);
        zaos = new ZipArchiveOutputStream(zipFile);
        zaos.setUseZip64(Zip64Mode.AsNeeded);

        File[] files = file.listFiles();
        for (int i = 0, length = files.length; i < length; i++) {
            File f = files[i];
            ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(f, f.getName());
            zaos.putArchiveEntry(zipArchiveEntry);
            if (f.isDirectory()) {
                System.out.println(f.getName());
                File[] imgFiles = f.listFiles();
                for (File imgFile:imgFiles){
                    ZipArchiveEntry zipImgEntry = new ZipArchiveEntry(imgFile, f.getName()+ File.separator+imgFile.getName());
                    zaos.putArchiveEntry(zipImgEntry);
                    InputStream is = new BufferedInputStream(new FileInputStream(imgFile));
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = is.read(buffer)) != -1) {
                        //把缓冲区的字节写入到ZipArchiveEntry
                        zaos.write(buffer, 0, len);
                    }
                    zaos.closeArchiveEntry();
                    if (is != null)
                        is.close();
                }
                continue;
            }
            InputStream is = new BufferedInputStream(new FileInputStream(f));
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                //把缓冲区的字节写入到ZipArchiveEntry
                zaos.write(buffer, 0, len);
            }
            zaos.closeArchiveEntry();
            if (is != null)
                is.close();
        }

        zaos.finish();

        return zipFile;
    }

}
