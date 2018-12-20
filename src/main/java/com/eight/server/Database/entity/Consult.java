package com.eight.server.Database.entity;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 咨询基本表
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-18
 */
@TableName("db_consult")
public class Consult extends Model<Consult> {

    private static final long serialVersionUID = 1L;

    /**
     * 咨询记录编号
     */
    private String recordno;
    /**
     * 来访者编号
     */
    private String sno;
    /**
     * 咨询师编号
     */
    private String cno;
    /**
     * 咨询时间
     */
    private Date consulttime;
    /**
     * 咨询结果
     */
    private String consultresult;


    public String getRecordno() {
        return recordno;
    }

    public void setRecordno(String recordno) {
        this.recordno = recordno;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public Date getConsulttime() {
        return consulttime;
    }

    public void setConsulttime(Date consulttime) {
        this.consulttime = consulttime;
    }

    public String getConsultresult() {
        return consultresult;
    }

    public void setConsultresult(String consultresult) {
        this.consultresult = consultresult;
    }

    @Override
    protected Serializable pkVal() {
        return this.recordno;
    }

    @Override
    public String toString() {
        return "Consult{" +
        "recordno=" + recordno +
        ", sno=" + sno +
        ", cno=" + cno +
        ", consulttime=" + consulttime +
        ", consultresult=" + consultresult +
        "}";
    }
}
