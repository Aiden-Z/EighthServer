package com.eight.server.Database.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import com.eight.server.Database.entity.Consultant;
import com.eight.server.Database.service.IConsultantService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-12-20 14:56:10
 */
@Controller
@RequestMapping("/consultant")
public class ConsultantController extends BaseController {

    private String PREFIX = "/system/consultant/";

    @Autowired
    private IConsultantService consultantService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "consultant.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/consultant_add")
    public String consultantAdd() {
        return PREFIX + "consultant_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/consultant_update/{consultantId}")
    public String consultantUpdate(@PathVariable Integer consultantId, Model model) {
        Consultant consultant = consultantService.selectById(consultantId);
        model.addAttribute("item",consultant);
        return PREFIX + "consultant_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return consultantService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Consultant consultant) {
        consultantService.insert(consultant);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer consultantId) {
        consultantService.deleteById(consultantId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Consultant consultant) {
        consultantService.updateById(consultant);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{consultantId}")
    @ResponseBody
    public Object detail(@PathVariable("consultantId") Integer consultantId) {
        return consultantService.selectById(consultantId);
    }
}
