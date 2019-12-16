package com.applet.apply.util.qiniu;


import com.applet.apply.util.PropertiesLoadUtils;

import java.nio.charset.Charset;

/**
 * @program: demo
 * @description: 七牛配置文件
 * @author: zhouhuahu
 * @create: 2019-09-28 09:59
 **/
public class QiNiuConfig {
    public static final String AK = "rInhqBhQ9wYTqim0k_Xf-8YFIILEvl5DVu_kk5Yp";
    public static final String SK = "yBglSgyUW_Qfys-VF7-JtTXx7P10zyn6PbR27ZLc";

    /**
     * 版本号
     */
    public static final String VERSION = "7.2.7";
    /**
     * 块大小，不能改变
     */
    public static final int BLOCK_SIZE = 4 * 1024 * 1024;
    /**
     * 所有都是UTF-8编码
     */
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    /**
     * 连接超时时间 单位秒(默认10s)
     */
    public static final int CONNECT_TIMEOUT = 10;
    /**
     * 写超时时间 单位秒(默认 0 , 不超时)
     */
    public static final int WRITE_TIMEOUT = 0;
    /**
     * 回复超时时间 单位秒(默认30s)
     */
    public static final int READ_TIMEOUT = 30;
    /**
     * 底层HTTP库所有的并发执行的请求数量
     */
    public static final int DISPATCHER_MAX_REQUESTS = 64;
    /**
     * 底层HTTP库对每个独立的Host进行并发请求的数量
     */
    public static final int DISPATCHER_MAX_REQUESTS_PER_HOST = 16;
    /**
     * 底层HTTP库中复用连接对象的最大空闲数量
     */
    public static final int CONNECTION_POOL_MAX_IDLE_COUNT = 32;
    /**
     * 底层HTTP库中复用连接对象的回收周期（单位分钟）
     */
    public static final int CONNECTION_POOL_MAX_IDLE_MINUTES = 5;

    public static final String bucketAppletPublic = "appletpublic";//七牛云公共空间
    public static final String bucketAppletImage = "appletimage";//七牛云私有图片空间
    public static final String bucketAppletAudio = "appletaudio";//七牛云私有音频文件空间
    public static final String bucketAppletVideo = "appletvideo";//七牛云私有视频文件空间
    public static final String bucketAppletZip = "appletzip";//七牛云私有模板文件空间

    public static final String downURLAppletPublicUrL;//公共空间地址
    public static final String downURLAppletImageUrl;//图片私有空间地址
    public static final String downURLAppletAudioUrl;//音频文件私有空间地址
    public static final String downURLAppletVideoUrl;//视频文件私有空间地址
    public static final String downURLAppletZipUrl;//模板文件私有空间地址

    static {
        if (PropertiesLoadUtils.isRun()) {
            downURLAppletPublicUrL = "http://public.wscxy.xin/";
            downURLAppletImageUrl = "http://image.wscxy.xin/";
            downURLAppletAudioUrl = "http://audio.wscxy.xin/";
            downURLAppletVideoUrl = "http://video.wscxy.xin/";
            downURLAppletZipUrl = "http://zip.wscxy.xin/";
        } else {
            downURLAppletPublicUrL = "http://pymkwekvt.bkt.clouddn.com/";
            downURLAppletImageUrl = "http://pymksenk7.bkt.clouddn.com/";
            downURLAppletAudioUrl = "http://pymknhbeu.bkt.clouddn.com/";
            downURLAppletVideoUrl = "http://pymkf6d00.bkt.clouddn.com/";
            downURLAppletZipUrl = "http://pymkskun5.bkt.clouddn.com/";
        }
    }
}
