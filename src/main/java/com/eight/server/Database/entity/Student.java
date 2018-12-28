package com.eight.server.Database.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 来访者基本表
 * </p>
 *
 * @author Aiden_Z
 * @since 2018-12-28
 */
@TableName("db_student")
public class Student extends Model<Student> {

    private static final long serialVersionUID = 1L;

    /**
     * 来访者编号
     */
    @TableId
    private String sno;

    /**
     * 姓名
     */
    private String sname;

    /**
     * 院系
     */
    private String sdept;

    /**
     * 联系方式
     */
    private String sphone;

    /**
     * 等级
     */
    private String sclass;

    /**
     * 紧急联系人
     */
    private String scon;

    /**
     * 紧急联系人联系方式
     */
    private String sconph;

    /**
     * 密码
     */
    private String spwd;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }
    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }
    public String getSdept() {
        return sdept;
    }

    public void setSdept(String sdept) {
        this.sdept = sdept;
    }
    public String getSphone() {
        return sphone;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone;
    }
    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }
    public String getScon() {
        return scon;
    }

    public void setScon(String scon) {
        this.scon = scon;
    }
    public String getSconph() {
        return sconph;
    }

    public void setSconph(String sconph) {
        this.sconph = sconph;
    }
    public String getSpwd() {
        return spwd;
    }

    public void setSpwd(String spwd) {
        this.spwd = spwd;
    }

    @Override
    protected Serializable pkVal() {
        return this.sno;
    }

    @Override
    public String toString() {
        return "Student{" +
        "sno=" + sno +
        ", sname=" + sname +
        ", sdept=" + sdept +
        ", sphone=" + sphone +
        ", sclass=" + sclass +
        ", scon=" + scon +
        ", sconph=" + sconph +
        ", spwd=" + spwd +
        "}";
    }
}
