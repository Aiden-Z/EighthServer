package com.eight.server.Database.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import com.eight.server.Database.entity.Scale;
import com.eight.server.Database.service.IScaleService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-12-18 22:53:58
 */
@Controller
@RequestMapping("/scale")
public class ScaleController extends BaseController {

    private String PREFIX = "/system/scale/";

    @Autowired
    private IScaleService scaleService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "scale.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/scale_add")
    public String scaleAdd() {
        return PREFIX + "scale_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/scale_update/{scaleId}")
    public String scaleUpdate(@PathVariable Integer scaleId, Model model) {
        Scale scale = scaleService.selectById(scaleId);
        model.addAttribute("item",scale);
        return PREFIX + "scale_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return scaleService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Scale scale) {
        scaleService.insert(scale);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer scaleId) {
        scaleService.deleteById(scaleId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Scale scale) {
        scaleService.updateById(scale);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{scaleId}")
    @ResponseBody
    public Object detail(@PathVariable("scaleId") Integer scaleId) {
        return scaleService.selectById(scaleId);
    }
}
