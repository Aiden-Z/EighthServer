package com.eight.server.Database.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import com.eight.server.Database.entity.Consult;
import com.eight.server.Database.service.IConsultService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-12-18 22:53:38
 */
@Controller
@RequestMapping("/consult")
public class ConsultController extends BaseController {

    private String PREFIX = "/system/consult/";

    @Autowired
    private IConsultService consultService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "consult.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/consult_add")
    public String consultAdd() {
        return PREFIX + "consult_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/consult_update/{consultId}")
    public String consultUpdate(@PathVariable Integer consultId, Model model) {
        Consult consult = consultService.selectById(consultId);
        model.addAttribute("item",consult);
        return PREFIX + "consult_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return consultService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Consult consult) {
        consultService.insert(consult);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer consultId) {
        consultService.deleteById(consultId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Consult consult) {
        consultService.updateById(consult);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{consultId}")
    @ResponseBody
    public Object detail(@PathVariable("consultId") Integer consultId) {
        return consultService.selectById(consultId);
    }
}
