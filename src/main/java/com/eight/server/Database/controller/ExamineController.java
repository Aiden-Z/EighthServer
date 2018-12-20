package com.eight.server.Database.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import com.eight.server.Database.entity.Examine;
import com.eight.server.Database.service.IExamineService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-12-18 22:53:49
 */
@Controller
@RequestMapping("/examine")
public class ExamineController extends BaseController {

    private String PREFIX = "/system/examine/";

    @Autowired
    private IExamineService examineService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "examine.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/examine_add")
    public String examineAdd() {
        return PREFIX + "examine_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/examine_update/{examineId}")
    public String examineUpdate(@PathVariable Integer examineId, Model model) {
        Examine examine = examineService.selectById(examineId);
        model.addAttribute("item",examine);
        return PREFIX + "examine_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return examineService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Examine examine) {
        examineService.insert(examine);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer examineId) {
        examineService.deleteById(examineId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Examine examine) {
        examineService.updateById(examine);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{examineId}")
    @ResponseBody
    public Object detail(@PathVariable("examineId") Integer examineId) {
        return examineService.selectById(examineId);
    }
}
