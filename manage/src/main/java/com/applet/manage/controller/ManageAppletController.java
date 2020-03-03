package com.applet.manage.controller;

import com.applet.common.util.*;
import com.applet.common.util.encryption.EncryptionUtil;
import com.applet.common.util.qiniu.QiNiuUtil;
import com.applet.manage.config.annotation.SessionScope;
import com.applet.common.entity.*;
import com.applet.manage.service.AppletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: SpringBootDemo
 * @description: 管理小程序控制类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-08-19 13:26
 **/
@RestController
@RequestMapping(value = "/api/manage/applet/")
public class ManageAppletController {
    private static final Logger log = LoggerFactory.getLogger(ManageAppletController.class);
    @Autowired
    private AppletService appletService;

    /**
     * 查询小程序服务类型列表
     *
     * @param type
     * @return
     */
    @RequestMapping(value = "queryAppletTypePage")
    public Object queryAppletTypePage(AppletType type, HttpServletRequest request) {
        Page page = PageUtil.initPage(request);
        page = appletService.selectAppletTypePage(type, page);
        return AjaxResponse.success(page);
    }

    /**
     * 加载小程序服务类型信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "loadAppletType")
    public Object loadAppletType(Integer id) {
        if (NullUtil.isNotNullOrEmpty(id) && id.intValue() != 0) {
            return AjaxResponse.success(appletService.selectAppletTypeById(id));
        }
        return AjaxResponse.error("未找到相关信息");
    }

    /**
     * 更新小程序服务类型信息
     *
     * @param record
     * @return
     */
    @RequestMapping(value = "updateAppletType")
    public Object updateAppletType(AppletType record) {
        try {
            if (null == record) {
                return AjaxResponse.error("参数错误");
            }
            if (NullUtil.isNullOrEmpty(record.getTypeName())) {
                return AjaxResponse.error("类型名称不能为空");
            }
            if (record.getTypeName().trim().length() > 50) {
                return AjaxResponse.error("类型名称长度过长");
            } else {
                record.setTypeName(record.getTypeName().trim());
            }
            appletService.updateAppletType(record);
            return AjaxResponse.success("提交成功");
        } catch (Exception e) {
            log.error("更新小程序服务类型出错{}", e);
            return AjaxResponse.error("提交失败");
        }
    }

    /**
     * 分页查询小程序列表
     *
     * @param info
     * @param request
     * @return
     */
    @RequestMapping(value = "queryAppletToPage")
    public Object queryAppletToPage(ViewAppletInfo info, HttpServletRequest request) {
        Page page = PageUtil.initPage(request);
        page = appletService.selectAppletInfoToPage(info, page);
        return AjaxResponse.success(page);
    }

    /**
     * 查询小程序管理列表
     *
     * @param info
     * @param request
     * @return
     */
    @RequestMapping(value = "queryAppletManageToPage")
    public Object queryAppletManageToPage(ViewAppletInfo info, HttpServletRequest request) {
        Page page = PageUtil.initPage(request);
        page = appletService.selectAppletInfoToPage(info, page);
        return AjaxResponse.success(page);
    }

    /**
     * 变更小程序管理状态
     *
     * @param id
     * @param status
     * @return
     */
    @RequestMapping(value = "updateAppletStatus")
    public Object updateAppletStatus(Integer id, Integer status) {
        try {
            if (NullUtil.isNullOrEmpty(id) || NullUtil.isNullOrEmpty(status)) {
                return AjaxResponse.error("参数错误");
            }
            if (status.intValue() != 1) {
                status = -1;
            }
            AppletInfo info = new AppletInfo();
            info.setId(id);
            info.setStatus(status);
            int result = appletService.updateAppletInfo(info);
            if (result > 0) {
                return AjaxResponse.success("操作成功");
            }
        } catch (Exception e) {
            log.error("修改小程序状态出错{}", e);
        }
        return AjaxResponse.error("操作失败");
    }

    /**
     * 加载小程序信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "loadAppletDetails")
    public Object loadAppletDetails(Integer id) {
        if (NullUtil.isNullOrEmpty(id)) {
            return AjaxResponse.error("参数错误");
        }
        ViewAppletInfo info = appletService.selectAppletInfo(id);
        if (null == info) {
            return AjaxResponse.error("未找到相关信息");
        }
        return AjaxResponse.success();
    }

    /**
     * 分页查询小程序审核列表(初审)
     *
     * @param record
     * @param request
     * @return
     */
    @RequestMapping(value = "queryAppletAuditToFirstTrial")
    public Object queryAppletAuditToFirstTrial(ViewAppletAuditList record, HttpServletRequest request) {
//        record.setAuditResult(0);
        Page page = PageUtil.initPage(request);
        page = appletService.selectAppletAuditToPage(record, page);
        return AjaxResponse.success(page);
    }

    /**
     * 分页查询小程序审核列表(终审)
     *
     * @param record
     * @param request
     * @return
     */
    @RequestMapping(value = "queryAppletAuditToLastTrial")
    public Object queryAppletAuditToLastTrial(ViewAppletAuditList record, HttpServletRequest request) {
        Page page = PageUtil.initPage(request);
        page = appletService.selectAppletAuditToPage(record, page);
        return AjaxResponse.success(page);
    }

    /**
     * 查询小程序审核记录
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "queryAppletAuditToList")
    public Object queryAppletAuditToList(Integer id) {
        List<ViewAppletAudit> list = appletService.selectAppletAuditList(id);
        return NullUtil.isNotNullOrEmpty(list) ? AjaxResponse.success(list) : AjaxResponse.error("未找到相关记录");
    }

    /**
     * 加载小程序审核信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "loadAppletAuditDetails")
    public Object loadAppletAuditDetails(Integer id) throws Exception {
        if (NullUtil.isNullOrEmpty(id)) {
            return AjaxResponse.error("参数错误");
        }
        ViewAppletAuditList record = appletService.selectAppletAuditDetails(id);
        if (null == record) {
            return AjaxResponse.error("未找到相关信息");
        }
        record.setManagerAccount(EncryptionUtil.decryptAppletRSA(record.getManagerAccount()));
        record.setManagerPassword(EncryptionUtil.decryptAppletRSA(record.getManagerPassword()));
        record.setAppId(EncryptionUtil.decryptAppletRSA(record.getAppId()));
        record.setAppSecret(EncryptionUtil.decryptAppletRSA(record.getAppSecret()));
        return AjaxResponse.success(record);
    }

    /**
     * 添加小程序审核信息
     *
     * @param manager
     * @param appletAudit
     * @return
     */
    @RequestMapping(value = "saveAppletAuditInfo")
    public Object saveAppletAuditInfo(@SessionScope(Constants.WEB_MANAGER_INFO) ManagerInfo manager, AppletAudit appletAudit) {
        try {
            if (null == appletAudit) {
                return AjaxResponse.error("获取参数出错");
            }
            if (NullUtil.isNullOrEmpty(appletAudit.getAppletId())) {
                return AjaxResponse.error("参数错误");
            }
            if (NullUtil.isNullOrEmpty(appletAudit.getResult())) {
                return AjaxResponse.error("请选择审核结果");
            }
            if (NullUtil.isNullOrEmpty(appletAudit.getRemark())) {
                return AjaxResponse.error("请填写备注说明");
            }
            ViewAppletAudit audit = appletService.selectAppletNewAudit(appletAudit.getAppletId());
            if (null == audit) {
                return AjaxResponse.error("参数错误，未获取到相关信息");
            }
            if (audit.getAuditResult().intValue() == -1 || audit.getAuditResult().intValue() == 2) {
                return AjaxResponse.error("小程序状态不符");
            }
            appletAudit.setAppletCode(audit.getAppletCode());
            appletAudit.setAuditorId(manager.getId());
            if ((manager.getRoleId().intValue() == 1 || manager.getRoleId() == 6)
                    && appletAudit.getResult().intValue() == 1 && audit.getAuditResult().intValue() == 1) {
                appletAudit.setResult(2);
            }
            appletService.addAppletAudit(appletAudit);
            return AjaxResponse.success("提交成功");
        } catch (Exception e) {
            log.error("提交小程序审核信息出错{}", e);
            return AjaxResponse.error("提交失败");
        }
    }

    /**
     * 加载小程序版本文件列表
     *
     * @return
     */
    @RequestMapping(value = "loadAppletFilePage")
    public Object loadAppletFilePage() {
        List<AppletType> list = appletService.selectAppletTypeList(true);
        return AjaxResponse.success(list);
    }

    /**
     * 分页查询小程序版本文件列表
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "queryAppletFilePage")
    public Object queryAppletFilePage(AppletFile file, HttpServletRequest request) {
        Page page = PageUtil.initPage(request);
        page = appletService.selectAppletFilePage(file, page);
        return AjaxResponse.success(page);
    }

    /**
     * 加载小程序版本文件信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "loadAppletFile")
    public Object loadAppletFile(Integer id) {
        Map map = new HashMap();
        List<AppletType> list = appletService.selectAppletTypeList(true);
        map.put("typeList", list);
        if (NullUtil.isNotNullOrEmpty(id) && id.intValue() != 0) {
            map.put("info", appletService.selectAppletFileById(id));
            return AjaxResponse.success(map);
        }
        return AjaxResponse.msg("-1", map);
    }

    /**
     * 更新小程序版本文件信息
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "updateAppletFile")
    public Object updateAppletFile(AppletFile file) {
        try {
            if (null == file) {
                return AjaxResponse.error("参数错误");
            }
            if (NullUtil.isNullOrEmpty(file.getTypeId())) {
                return AjaxResponse.error("请选择服务类型");
            }
            if (NullUtil.isNullOrEmpty(file.getVersionNumber())) {
                return AjaxResponse.error("版本号不能为空");
            }
            if (file.getVersionNumber().trim().length() > 30) {
                return AjaxResponse.error("版本号长度过长");
            } else {
                file.setVersionNumber(file.getVersionNumber().trim().toUpperCase());
            }
            appletService.updateAppletFile(file);
            return AjaxResponse.success("提交成功");
        } catch (Exception e) {
            log.error("提交小程序版本文件信息出错{}", e);
            return AjaxResponse.error("提交失败");
        }
    }

    /**
     * 上传小程序版本压缩文件
     *
     * @param multipartFile
     * @param id
     * @param typeId
     * @return
     */
    @RequestMapping(value = "uploadAppletFile")
    public Object uploadAppletFile(@RequestParam("typeZip") MultipartFile multipartFile, Integer id, Integer typeId) {
        try {
            //校验文件信息
            com.applet.common.entity.CheckResult result = CheckFileUtil.checkZipFile(multipartFile);
            if (!result.getBool()) {
                return AjaxResponse.error(result.getMsg());
            }
            AppletFile file = appletService.selectAppletFile(id, typeId);
            if (null == file) {
                return AjaxResponse.error("信息不符");
            }
            String fileKey = NullUtil.isNotNullOrEmpty(file.getFilePath()) ?
                    file.getFilePath() : "/api/zip/AT" + typeId + "/" + file.getVersionNumber() + ".zip";
            QiNiuUtil.uploadFile(multipartFile, fileKey);
            if (NullUtil.isNullOrEmpty(file.getFilePath())) {
                file.setFilePath(fileKey);
                file.setFileStatus(true);
                appletService.updateAppletFile(file);
            }
            return AjaxResponse.success("上传成功");
        } catch (Exception e) {
            log.error("上传头像出错{}", e);
            return AjaxResponse.error("上传失败");
        }
    }

    /**
     * 分页查询小程序版本信息
     *
     * @param version
     * @param request
     * @return
     */
    @RequestMapping(value = "queryAppletVersionPage")
    public Object queryAppletVersionPage(ViewAppletVersion version, HttpServletRequest request) {
        Page page = PageUtil.initPage(request);
        page = appletService.selectAppletVersionPage(version, page);
        return AjaxResponse.success(page);
    }

    /**
     * 加载小程序版本信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "loadAppletVersion")
    public Object loadAppletVersion(Integer id) {
        try {
            if (NullUtil.isNotNullOrEmpty(id) && id.intValue() != 0) {
                ViewAppletVersion version = appletService.selectAppletVersion(id);
                List<AppletFile> list = appletService.selectAppletFile(version.getTypeId());
                Map map = new HashMap();
                map.put("info", version);
                map.put("list", list);
                return AjaxResponse.success(map);
            }
        } catch (Exception e) {
            log.error("加载小程序版本信息出错{]", e);
        }
        return AjaxResponse.error("未找到相关记录");
    }

    /**
     * 更新小程序版本信息
     *
     * @param version
     * @return
     */
    @RequestMapping(value = "updateAppletVersion")
    public Object updateAppletVersion(AppletVersion version) {
        try {
            if (null == version) {
                return AjaxResponse.error("参数错误");
            }
            if (NullUtil.isNullOrEmpty(version.getId())) {
                return AjaxResponse.error("参数丢失");
            }
            if (NullUtil.isNullOrEmpty(version.getFileId())) {
                return AjaxResponse.error("请选择版本文件");
            }
            appletService.updateAppletVersion(version);
            return AjaxResponse.success("提交成功");
        } catch (Exception e) {
            log.error("更新小程序版本出错{}", e);
            return AjaxResponse.error("提交失败");
        }
    }

    /**
     * 下载小程序版本文件
     *
     * @param id
     * @param response
     */
    @RequestMapping(value = "downloadAppletVersionFile")
    public void downloadAppletVersionFile(Integer id, HttpServletResponse response) {
        try {
            if (NullUtil.isNullOrEmpty(id)) {
                return;
            }
            ViewAppletVersion version = appletService.selectAppletVersion(id);
            if (null == version) {
                return;
            }
            if (NullUtil.isNullOrEmpty(version.getFileId())) {
                return;
            }
            response.sendRedirect(QiNiuUtil.getZipDownURL(version.getFilePath()));
        } catch (Exception e) {
            log.error("下载小程序文件出错");
        }
    }
}
