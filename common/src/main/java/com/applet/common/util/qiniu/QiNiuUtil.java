package com.applet.common.util.qiniu;

import com.applet.common.util.Constants;
import com.applet.common.util.file.GetImageUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.processing.OperationManager;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @program: demo
 * @description: 七牛云存储工具类
 * @author: zhouhuahu
 * @create: 2019-09-28 10:02
 **/
public class QiNiuUtil {

    private static final Logger logger = LoggerFactory.getLogger(QiNiuUtil.class);

    private static BucketManager bucketManager;

    private static Auth auth;

    private static OperationManager operationManager;

    private static UploadManager uploadManager;

    private static String zipSuffix = "YS.jpg";

    static {
        Configuration cfg = new Configuration();
        auth = Auth.create(QiNiuConfig.AK, QiNiuConfig.SK);
        bucketManager = new BucketManager(auth, cfg);
        operationManager = new OperationManager(auth, cfg);
        uploadManager = new UploadManager(cfg);
    }

    /**
     * 查询存储空间文件是否存在
     *
     * @param key
     * @return
     */
    public static boolean existsFile(String key) {
        if (key.indexOf("/public") >= 0) {
            return existsFile(QiNiuConfig.bucketAppletPublic, key);
        } else if (key.indexOf("/image") >= 0) {
            return existsFile(QiNiuConfig.bucketAppletImage, key);
        } else if (key.indexOf("/audio") >= 0) {
            return existsFile(QiNiuConfig.bucketAppletAudio, key);
        } else if (key.indexOf("/video") >= 0) {
            return existsFile(QiNiuConfig.bucketAppletVideo, key);
        } else if (key.indexOf("/zip") >= 0) {
            return existsFile(QiNiuConfig.bucketAppletZip, key);
        }
        return false;
    }

    public static boolean existsFile(String bucket, String key) {
        try {
            FileInfo info = bucketManager.stat(bucket, key);
            return null != info;
        } catch (QiniuException e) {
            return false;
        }
    }

//    public static boolean existsZipFile(String bucket, String key) {
//        try {
//            FileInfo info = bucketManager.stat(bucket, key + zipSuffix);
//            return null != info;
//        } catch (QiniuException e) {
//            return false;
//        }
//    }


    /*****************************************获取上传凭证 ***********************************************/

    /**
     * 公共空间
     *
     * @return
     */
    public static String getUploadTokenPublic() {
        return auth.uploadToken(QiNiuConfig.bucketAppletPublic);
    }

    /**
     * 图片空间
     *
     * @param key
     * @return
     */
    public static String getUploadTokenImage(String key) {
        return auth.uploadToken(QiNiuConfig.bucketAppletImage, key, 120L, null);
    }

    /**
     * 音频空间
     *
     * @param key
     * @return
     */
    public static String getUploadTokenAudio(String key) {
        return auth.uploadToken(QiNiuConfig.bucketAppletAudio, key);
    }

    /**
     * 视频空间
     *
     * @param key
     * @return
     */
    public static String getUploadTokenVideo(String key) {
        return auth.uploadToken(QiNiuConfig.bucketAppletVideo, key);
    }

    /**
     * 模板空间
     *
     * @param key
     * @return
     */
    public static String getUploadTokenZip(String key) {
        return auth.uploadToken(QiNiuConfig.bucketAppletZip, key);
    }


    /*****************************************上传文件 ***********************************************/

    /**
     * 根据文件 bucket 上传文件至对应空间
     *
     * @param multipartFile
     * @param key
     * @throws Exception
     */
    public static void uploadFile(MultipartFile multipartFile, String key) throws Exception {
        if (key.indexOf("/public") >= 0) {
            String type = multipartFile.getContentType();
            if (Constants.UPLOAD_FILE_TYPE_IMAGE.indexOf(type) >= 0 && multipartFile.getSize() > (1 * 1048576)) {
                // 大于1MB的图片进行压缩处理
                zipAndUpload(key, multipartFile, 0.3f, QiNiuConfig.bucketAppletPublic);
            } else {
                uploadFile(multipartFile.getBytes(), key, QiNiuConfig.bucketAppletPublic);
            }
        } else if (key.indexOf("/image") >= 0) {
            if (multipartFile.getSize() > (1 * 1048576)) {
                // 大于1MB的图片进行压缩处理
                zipAndUpload(key, multipartFile, 0.3f, QiNiuConfig.bucketAppletImage);
            } else {
                uploadFile(multipartFile.getBytes(), key, QiNiuConfig.bucketAppletImage);
            }
        } else if (key.indexOf("/audio") >= 0) {
            uploadFile(multipartFile.getBytes(), key, QiNiuConfig.bucketAppletAudio);
        } else if (key.indexOf("/video") >= 0) {
            uploadFile(multipartFile.getBytes(), key, QiNiuConfig.bucketAppletVideo);
        } else if (key.indexOf("/zip") >= 0) {
            uploadFile(multipartFile.getBytes(), key, QiNiuConfig.bucketAppletZip);
        }
    }


    /**
     * java 内上传文件
     *
     * @param file
     * @param fileName
     */
    public static void uploadFile(byte[] file, String fileName, String secfileNametyType) {
        try {
            Response response = uploadManager.put(file, fileName, auth.uploadToken(secfileNametyType, fileName, 600, null));
            logger.info("java sdk 上传文件：" + response.bodyString());
        } catch (Exception e) {
            logger.error("上传Audit文件出错", e);
        }
    }

    /**
     * 压缩图片并上传
     *
     * @param fileName
     * @param mFile
     * @param quality
     * @param bucket
     * @return
     */
    public static String zipAndUpload(String fileName, MultipartFile mFile, float quality, String bucket) throws Exception {
        //从MultipartFile获取File式的inputStream
//        CommonsMultipartFile cFile = (CommonsMultipartFile) mFile;
//        DiskFileItem fileItem = (DiskFileItem) cFile.getFileItem();
        InputStream inputStream = mFile.getInputStream();
        //压缩并上传
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Thumbnails.of(inputStream).scale(0.6f).outputQuality(quality).toOutputStream(out);
        uploadFile(out.toByteArray(), fileName, bucket);
        return fileName;
    }

    /**
     * 压缩并上传
     * @param fileName
     * @param obj
     * @param quality
     * @param bucket
     * @return
     * @throws Exception
     */
    public static String zipAndUpload(String fileName, Object obj, float quality, String bucket) throws Exception {
        InputStream inputStream = null;
        try {
            inputStream = (InputStream)obj;
        } catch (Exception e) {
            logger.error("【生成小程序二维码出错】", e);
            throw new Exception();
        }
        //压缩并上传
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Thumbnails.of(inputStream).scale(0.6f).outputQuality(quality).toOutputStream(out);
        uploadFile(out.toByteArray(), fileName, bucket);
        return fileName;
    }


    /**
     * 七牛 key
     *
     * @param picKey
     * @return
     */
    public static String generateQiniuPrivateDownURLByQuality(String picKey) {
        String qualityKey = picKey.replaceAll(".jpg", zipSuffix);
        if (existsFile(QiNiuConfig.bucketAppletImage, qualityKey)) {
            return getImageDownURL(qualityKey, 300l);
        } else {
            return getImageDownURL(zipAndUpload(picKey, 0.3f));
        }
    }

    /**
     * 私有空间
     * 压缩图片新的图片上传至七牛云
     *
     * @return 压缩后的图片HTTP URL
     */
    public static String zipAndUpload(String picKey, float quality) {
        String uploadKey = picKey.replaceAll(".jpg", zipSuffix);
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            String url = getImageDownURL("/" + picKey, 300l);
            Thumbnails.of(GetImageUtil.getImageIO(url)).scale(0.6f).outputQuality(quality).toOutputStream(out);
            uploadFile(out.toByteArray(), uploadKey, QiNiuConfig.bucketAppletImage);
            return uploadKey;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /*****************************************获取访问凭证 ***********************************************/

    /**
     * 获取访问凭证
     *
     * @param key
     * @return
     */
    public static String getDownURL(String key) {
        if (key.indexOf("/public") >= 0) {
            return getPublicDownURL(key);
        } else if (key.indexOf("/image") >= 0) {
            return getImageDownURL(key);
        } else if (key.indexOf("/audio") >= 0) {
            return getAudioDownURL(key);
        } else if (key.indexOf("/video") >= 0) {
            return getVideoDownURL(key);
        } else if (key.indexOf("/zip") >= 0) {
            return getZipDownURL(key);
        }
        return null;
    }

    /**
     * 公有空间文件访问凭证
     *
     * @param requestfileName
     * @return
     */
    public static String getPublicDownURL(String requestfileName) {
        String downloadRUL = QiNiuConfig.downURLAppletPublicUrL + requestfileName;
        return downloadRUL;
    }

    /**
     * 图片访问凭证
     *
     * @param requestfileName
     * @return
     */
    public static String getImageDownURL(String requestfileName) {
        return getImageDownURL(requestfileName, null);
    }

    public static String getImageDownURL(String requestfileName, Long expires) {
        Auth auth = Auth.create(QiNiuConfig.AK, QiNiuConfig.SK);
        String downloadRUL = auth.privateDownloadUrl(QiNiuConfig.downURLAppletImageUrl + requestfileName, (null == expires) ? 60*30 : expires);
        return downloadRUL;
    }

    /**
     * 语音频文件
     *
     * @param requestfileName
     * @return
     */
    public static String getAudioDownURL(String requestfileName) {
        return getAudioDownURL(requestfileName, null);
    }

    public static String getAudioDownURL(String requestfileName, Long expires) {
        Auth auth = Auth.create(QiNiuConfig.AK, QiNiuConfig.SK);
        String downloadRUL = auth.privateDownloadUrl(QiNiuConfig.downURLAppletAudioUrl + requestfileName, (null == expires) ? 60 : expires);
        return downloadRUL;
    }

    /**
     * 视频文件
     *
     * @param requestfileName
     * @return
     */
    public static String getVideoDownURL(String requestfileName) {
        return getVideoDownURL(requestfileName, null);
    }

    public static String getVideoDownURL(String requestfileName, Long expires) {
        Auth auth = Auth.create(QiNiuConfig.AK, QiNiuConfig.SK);
        String downloadRUL = auth.privateDownloadUrl(QiNiuConfig.downURLAppletVideoUrl + requestfileName, (null == expires) ? 60 : expires);
        return downloadRUL;
    }

    /**
     * 压缩文件
     *
     * @param requestfileName
     * @return
     */
    public static String getZipDownURL(String requestfileName) {
        return getZipDownURL(requestfileName, null);
    }

    public static String getZipDownURL(String requestfileName, Long expires) {
        Auth auth = Auth.create(QiNiuConfig.AK, QiNiuConfig.SK);
        String downloadRUL = auth.privateDownloadUrl(QiNiuConfig.downURLAppletZipUrl + requestfileName, (null == expires) ? 60 : expires);
        return downloadRUL;
    }


    /*****************************************重命名文件 ***********************************************/

    /**
     * 重命名七牛文件 是否删除原文件
     *
     * @param bucket
     * @param oldKey
     * @param newKey
     * @param delete
     */
    public static void updateFileName(String bucket, String oldKey, String newKey, boolean delete) {
        try {
            if (delete) {
                deleteFile(bucket, newKey);
            }
            bucketManager.rename(bucket, oldKey, newKey);
            logger.info("七牛云重命名文件成功 old:{},new:{}", oldKey, newKey);
        } catch (QiniuException e) {
            logger.info("七牛云重命名文件出错{}", e.getMessage());
        }
    }

    /**
     * 重命名七牛文件 不删除原文件
     *
     * @param bucket
     * @param oldKey
     * @param newKey
     */
    public static void updateFileName(String bucket, String oldKey, String newKey) {
        updateFileName(bucket, oldKey, newKey, false);
    }


    /*****************************************删除文件 ***********************************************/

    /**
     * 删除文件
     *
     * @param key
     */
    public static void deleteFile(String key) {
        if (key.indexOf("/public") >= 0) {
            deleteFile(QiNiuConfig.bucketAppletPublic, key);
        } else if (key.indexOf("/image") >= 0) {
            deleteFile(QiNiuConfig.bucketAppletImage, key);
        } else if (key.indexOf("/audio") >= 0) {
            deleteFile(QiNiuConfig.bucketAppletAudio, key);
        } else if (key.indexOf("/video") >= 0) {
            deleteFile(QiNiuConfig.bucketAppletVideo, key);
        } else if (key.indexOf("/zip") >= 0) {
            deleteFile(QiNiuConfig.bucketAppletZip, key);
        }
    }

    /**
     * 删除七牛文件
     *
     * @param bucket
     * @param key
     */
    public static void deleteFile(String bucket, String key) {
        try {
            if (existsFile(bucket, key)) {
                bucketManager.delete(bucket, key);
                logger.info("七牛云删除文件成功{}", key);
            } else {
                logger.info("七牛云删除文件时发现文件不存在{}", key);
            }
        } catch (QiniuException e) {
            logger.info("七牛云删除文件出错{}", e.getMessage());
        }
    }


    /**
     * 移动文件到其他存储空间
     *
     * @param oldKey
     * @param newKey
     * @throws Exception
     */
    public static void removeFile(String oldKey, String newKey) throws Exception {
        if (existsFile(oldKey)) {
            String fromBucket = "";
            String toBucket = "";
            for (int i = 0; i < 2; i++) {
                String bucket = "";
                String key = "";
                switch (i) {
                    case 0:
                        key = oldKey;
                        break;
                    case 1:
                        key = newKey;
                        break;
                }
                if (key.indexOf("/public") >= 0) {
                    bucket = QiNiuConfig.bucketAppletPublic;
                } else if (key.indexOf("/image") >= 0) {
                    bucket = QiNiuConfig.bucketAppletImage;
                } else if (key.indexOf("/audio") >= 0) {
                    bucket = QiNiuConfig.bucketAppletAudio;
                } else if (key.indexOf("/video") >= 0) {
                    bucket = QiNiuConfig.bucketAppletVideo;
                } else if (key.indexOf("/zip") >= 0) {
                    bucket = QiNiuConfig.bucketAppletZip;
                }
                switch (i) {
                    case 0:
                        fromBucket = bucket;
                        break;
                    case 1:
                        toBucket = bucket;
                        break;
                }

            }
            deleteFile(newKey);
            bucketManager.copy(fromBucket, oldKey, toBucket, newKey);
            deleteFile(oldKey);
        }
    }

    private static FileItem createFileItem(File file, String fieldName) {
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        FileItem item = factory.createItem(fieldName, "text/plain", true, file.getName());
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        try {
            FileInputStream fis = new FileInputStream(file);
            OutputStream os = item.getOutputStream();
            while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return item;
    }

    public static void main(String[] args) {
        try {
            String key = "/api/image/M1-A20191003000711304300";
//            if (existsFile(QiNiuConfig.bucketAppletPrivate, key)){
//                System.out.println("七牛云图片路径：" + QiniuUtil.generateQiniuPrivateDownURLByQuality(key));
//            } else {
//                System.out.println("未找到文件");
//            }
            System.out.println("七牛云图片路径：" + QiNiuUtil.getImageDownURL(key));
//            deleteFile(QiNiuConfig.bucketAppletPrivate, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
