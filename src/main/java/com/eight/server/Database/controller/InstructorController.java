package com.eight.server.Database.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import com.eight.server.Database.entity.Instructor;
import com.eight.server.Database.service.IInstructorService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-12-20 14:56:20
 */
@Controller
@RequestMapping("/instructor")
public class InstructorController extends BaseController {

    private String PREFIX = "/system/instructor/";

    @Autowired
    private IInstructorService instructorService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "instructor.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/instructor_add")
    public String instructorAdd() {
        return PREFIX + "instructor_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/instructor_update/{instructorId}")
    public String instructorUpdate(@PathVariable Integer instructorId, Model model) {
        Instructor instructor = instructorService.selectById(instructorId);
        model.addAttribute("item",instructor);
        return PREFIX + "instructor_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return instructorService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Instructor instructor) {
        instructorService.insert(instructor);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer instructorId) {
        instructorService.deleteById(instructorId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Instructor instructor) {
        instructorService.updateById(instructor);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{instructorId}")
    @ResponseBody
    public Object detail(@PathVariable("instructorId") Integer instructorId) {
        return instructorService.selectById(instructorId);
    }
}
