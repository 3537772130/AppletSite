package com.applet.apply.service;

import com.applet.common.entity.ViewAppletPageContent;
import com.applet.common.entity.ViewAppletPageContentExample;
import com.applet.common.mapper.ViewAppletPageContentMapper;
import com.applet.common.util.NullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2019/12/10
 * Time: 18:16
 * To change this template use File | Settings | File Templates.
 * Description: 小程序页面服务类
 */
@SuppressWarnings("ALL")
@Service
public class AppletPageService {
    @Autowired
    private ViewAppletPageContentMapper viewAppletPageContentMapper;

    /**
     * 查询小程序页面配置信息
     * @param appletId
     * @param pageLogo
     * @return
     */
    public ViewAppletPageContent selectViewAppletPageContent(Integer appletId, String pageLogo) {
        ViewAppletPageContentExample example = new ViewAppletPageContentExample();
        example.createCriteria().andAppletIdEqualTo(appletId).andPageLogoEqualTo(pageLogo);
        List<ViewAppletPageContent> list = viewAppletPageContentMapper.selectByExampleWithBLOBs(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }
}
