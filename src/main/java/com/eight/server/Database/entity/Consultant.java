package com.eight.server.Database.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 咨询师基本表
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-20
 */
@TableName("db_consultant")
public class Consultant extends Model<Consultant> {

    private static final long serialVersionUID = 1L;

    /**
     * 咨询师编号
     */
    private String cno;
    /**
     * 姓名
     */
    private String cname;
    /**
     * 联系方式
     */
    private String cphone;
    /**
     * 等级
     */
    private String cclass;
    /**
     * 密码
     */
    private String cpwd;


    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

    public String getCclass() {
        return cclass;
    }

    public void setCclass(String cclass) {
        this.cclass = cclass;
    }

    public String getCpwd() {
        return cpwd;
    }

    public void setCpwd(String cpwd) {
        this.cpwd = cpwd;
    }

    @Override
    protected Serializable pkVal() {
        return this.cno;
    }

    @Override
    public String toString() {
        return "Consultant{" +
        "cno=" + cno +
        ", cname=" + cname +
        ", cphone=" + cphone +
        ", cclass=" + cclass +
        ", cpwd=" + cpwd +
        "}";
    }
}
