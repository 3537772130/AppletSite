package com.applet.user.util;

import com.applet.user.entity.CheckResult;
import com.applet.user.util.file.FileUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: SpringBootDemo
 * @description: 检查文件工具类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-08-05 09:24
 **/
public class CheckFileUtil {

    /**
     * 校验图片文件
     *
     * @param file
     * @return
     */
    public static CheckResult checkImageFile(MultipartFile file) {
        if (file == null) {
            return new CheckResult("未解析到文件");
        }
        String fileName = file.getOriginalFilename();
        if (NullUtil.isNullOrEmpty(fileName) && file.getSize() == 0) {
            return new CheckResult("图片内容为空");
        }
        if (file.getSize() > 5 * 1048576) {
            return new CheckResult("文件大于5MB");
        }
        String type = file.getContentType();
        if (Constants.UPLOAD_FILE_TYPE_IMAGE.indexOf(type) < 0) {
            return new CheckResult("不支持的文件类型");
        }
        return new CheckResult();
    }

    /**
     * 校验音频文件
     *
     * @param file
     * @return
     */
    public static CheckResult checkAudioFile(MultipartFile file) {
        if (file == null) {
            return new CheckResult("未解析到文件");
        }
        String fileName = file.getOriginalFilename();
        if (NullUtil.isNullOrEmpty(fileName) && file.getSize() == 0) {
            return new CheckResult("音频内容为空");
        }
        if (file.getSize() > 10 * 1048576) {
            return new CheckResult("文件大于10MB");
        }
        if (!FileUtil.isVedioFile(fileName)) {
            return new CheckResult("不支持的文件类型");
        }
        String type = file.getContentType();
        if (Constants.UPLOAD_FILE_TYPE_AUDIO.indexOf(type) < 0) {
            return new CheckResult("不支持的文件类型");
        }
        return new CheckResult();
    }

    /**
     * 校验视频文件
     *
     * @param file
     * @return
     */
    public static CheckResult checkVideoFile(MultipartFile file) {
        if (file == null) {
            return new CheckResult("未解析到文件");
        }
        String fileName = file.getOriginalFilename();
        if (NullUtil.isNullOrEmpty(fileName) && file.getSize() == 0) {
            return new CheckResult("视频内容为空");
        }
        if (file.getSize() > 10 * 1048576) {
            return new CheckResult("文件大于10MB");
        }
        if (!FileUtil.isVedioFile(fileName)) {
            return new CheckResult("不支持的文件类型");
        }
        String type = file.getContentType();
        if (Constants.UPLOAD_FILE_TYPE_VIDEO.indexOf(type) < 0) {
            return new CheckResult("不支持的文件类型");
        }
        return new CheckResult();
    }

    /**
     * 校验压缩文件
     *
     * @param file
     * @return
     */
    public static CheckResult checkZipFile(MultipartFile file) {
        if (file == null) {
            return new CheckResult("未解析到文件");
        }
        String fileName = file.getOriginalFilename();
        if (NullUtil.isNullOrEmpty(fileName) && file.getSize() == 0) {
            return new CheckResult("压缩包内容为空");
        }
        if (file.getSize() > 50 * 1048576) {
            return new CheckResult("文件大于50MB");
        }
        String type = file.getContentType();
        if (Constants.UPLOAD_FILE_TYPE_ZIP.indexOf(type) < 0) {
            return new CheckResult("不支持的文件类型");
        }
        return new CheckResult();
    }


    /**
     * 检查系统文件
     *
     * @param file
     * @return
     */
    public static CheckResult checkFile(MultipartFile file) {
        if (file == null) {
            return new CheckResult("未解析到文件");
        }
        String fileName = file.getOriginalFilename();
        if (NullUtil.isNullOrEmpty(fileName) && file.getSize() == 0) {
            return new CheckResult("文件内容为空");
        }
        String type = file.getContentType();
        if (Constants.UPLOAD_FILE_TYPE_IMAGE.indexOf(type) >= 0) {
            if (file.getSize() > 5 * 1048576) {
                return new CheckResult("图片大于5MB");
            }
        } else if (Constants.UPLOAD_FILE_TYPE_ZIP.indexOf(type) >= 0) {
            if (file.getSize() > 10 * 1048576) {
                return new CheckResult("压缩包大于10MB");
            }
        } else if (Constants.UPLOAD_FILE_TYPE_VIDEO.indexOf(type) >= 0) {
            if (file.getSize() > 10 * 1048576) {
                return new CheckResult("视频大于10MB");
            }
        } else if (Constants.UPLOAD_FILE_TYPE_AUDIO.indexOf(type) >= 0) {
            if (file.getSize() > 5 * 1048576) {
                return new CheckResult("音频大于5MB");
            }
        } else {
            return new CheckResult("不支持的文件类型");
        }
        return new CheckResult();
    }


}
