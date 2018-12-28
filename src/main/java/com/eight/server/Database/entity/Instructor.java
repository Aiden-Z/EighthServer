package com.eight.server.Database.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 辅导员基本表
 * </p>
 *
 * @author Aiden_Z
 * @since 2018-12-28
 */
@TableName("db_instructor")
public class Instructor extends Model<Instructor> {

    private static final long serialVersionUID = 1L;

    /**
     * 辅导员编号
     */
    @TableId
    private String ino;

    /**
     * 姓名
     */
    private String iname;

    /**
     * 联系方式
     */
    private String iphone;

    /**
     * 院系
     */
    private String idept;

    /**
     * 密码
     */
    private String ipwd;

    public String getIno() {
        return ino;
    }

    public void setIno(String ino) {
        this.ino = ino;
    }
    public String getIname() {
        return iname;
    }

    public void setIname(String iname) {
        this.iname = iname;
    }
    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }
    public String getIdept() {
        return idept;
    }

    public void setIdept(String idept) {
        this.idept = idept;
    }
    public String getIpwd() {
        return ipwd;
    }

    public void setIpwd(String ipwd) {
        this.ipwd = ipwd;
    }

    @Override
    protected Serializable pkVal() {
        return this.ino;
    }

    @Override
    public String toString() {
        return "Instructor{" +
        "ino=" + ino +
        ", iname=" + iname +
        ", iphone=" + iphone +
        ", idept=" + idept +
        ", ipwd=" + ipwd +
        "}";
    }
}
