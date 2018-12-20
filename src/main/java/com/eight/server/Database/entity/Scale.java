package com.eight.server.Database.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 量表基本表
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-18
 */
@TableName("db_scale")
public class Scale extends Model<Scale> {

    private static final long serialVersionUID = 1L;

    /**
     * 量表编号
     */
    private String scno;
    /**
     * 名称
     */
    private String scname;
    /**
     * 描述
     */
    private String scdescribe;
    /**
     * 类别
     */
    private String sctype;


    public String getScno() {
        return scno;
    }

    public void setScno(String scno) {
        this.scno = scno;
    }

    public String getScname() {
        return scname;
    }

    public void setScname(String scname) {
        this.scname = scname;
    }

    public String getScdescribe() {
        return scdescribe;
    }

    public void setScdescribe(String scdescribe) {
        this.scdescribe = scdescribe;
    }

    public String getSctype() {
        return sctype;
    }

    public void setSctype(String sctype) {
        this.sctype = sctype;
    }

    @Override
    protected Serializable pkVal() {
        return this.scno;
    }

    @Override
    public String toString() {
        return "Scale{" +
        "scno=" + scno +
        ", scname=" + scname +
        ", scdescribe=" + scdescribe +
        ", sctype=" + sctype +
        "}";
    }
}
