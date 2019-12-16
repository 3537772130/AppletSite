package com.applet.apply.service;

import com.applet.apply.entity.FigureCode;
import com.applet.apply.entity.FigureCodeExample;
import com.applet.apply.mapper.FigureCodeMapper;
import com.applet.apply.util.NullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhouhuahu
 * @date 2018/9/17
 */
@Service
public class FigureCodeService {
    @Autowired
    private FigureCodeMapper figureCodeMapper;

    /**
     * 删除过期图形码
     * @param wxId
     * @param type
     */
    public void deleteFigureCode(Integer wxId, String type){
        FigureCodeExample example = new FigureCodeExample();
        example.createCriteria().andWxIdEqualTo(wxId).andTypeEqualTo(type).andChannelEqualTo("APPLET");
        figureCodeMapper.deleteByExample(example);
    }

    /**
     * 添加小程序图形码
     * @param wxId
     * @param code
     * @param type
     */
    public void saveFigureCode(Integer wxId, String code, String type){
        FigureCode figureCode = new FigureCode();
        figureCode.setWxId(wxId);
        figureCode.setCode(code);
        figureCode.setType(type);
        figureCode.setChannel("APPLET");
        figureCodeMapper.insertSelective(figureCode);
    }

    /**
     * 查询微信最新图形码
     * @param wxId
     * @param type
     * @return
     */
    public FigureCode selectFigureCode(Integer wxId, String type){
        FigureCodeExample example = new FigureCodeExample();
        example.setOrderByClause("id desc limit 1");
        example.createCriteria().andWxIdEqualTo(wxId).andTypeEqualTo(type).andChannelEqualTo("APPLET");
        List<FigureCode> list = figureCodeMapper.selectByExample(example);
        if (NullUtil.isNotNullOrEmpty(list)){
            return list.get(0);
        }
        return null;
    }
}
