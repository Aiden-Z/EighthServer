package com.eight.server.Database.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 咨询基本表
 * </p>
 *
 * @author Aiden_Z
 * @since 2018-12-27
 */
@TableName("db_consult")
public class Consult extends Model<Consult> {

    private static final long serialVersionUID = 1L;

    /**
     * 咨询记录编号
     */
    @TableId
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
    private LocalDateTime consulttime;

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
    public LocalDateTime getConsulttime() {
        return consulttime;
    }

    public void setConsulttime(LocalDateTime consulttime) {
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
