package com.eight.server.Database.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 辅导员基本表
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-20
 */
@TableName("db_instructor")
public class Instructor extends Model<Instructor> {

    private static final long serialVersionUID = 1L;

    /**
     * 辅导员编号
     */
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
