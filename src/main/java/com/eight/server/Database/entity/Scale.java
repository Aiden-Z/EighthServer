package com.eight.server.Database.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 量表基本表
 * </p>
 *
 * @author Aiden_Z
 * @since 2018-12-28
 */
@TableName("db_scale")
public class Scale extends Model<Scale> {

    private static final long serialVersionUID = 1L;

    /**
     * 量表编号
     */
    @TableId
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
