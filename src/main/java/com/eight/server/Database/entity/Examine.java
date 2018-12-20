package com.eight.server.Database.entity;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 月排查表基本表
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-18
 */
@TableName("db_examine")
public class Examine extends Model<Examine> {

    private static final long serialVersionUID = 1L;

    /**
     * 月排查表编号
     */
    private String eno;
    /**
     * 来访者编号
     */
    private String sno;
    /**
     * 咨询师编号
     */
    private String cno;
    /**
     * 辅导员编号
     */
    private String ino;
    /**
     * 院系
     */
    private String edept;
    /**
     * 排查时间
     */
    private Date etime;
    /**
     * 排查结果
     */
    private String eresult;
    /**
     * 咨询师反馈时间
     */
    private Date feedbacktime;
    /**
     * 咨询师反馈结果
     */
    private String feedbackresult;


    public String getEno() {
        return eno;
    }

    public void setEno(String eno) {
        this.eno = eno;
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

    public String getIno() {
        return ino;
    }

    public void setIno(String ino) {
        this.ino = ino;
    }

    public String getEdept() {
        return edept;
    }

    public void setEdept(String edept) {
        this.edept = edept;
    }

    public Date getEtime() {
        return etime;
    }

    public void setEtime(Date etime) {
        this.etime = etime;
    }

    public String getEresult() {
        return eresult;
    }

    public void setEresult(String eresult) {
        this.eresult = eresult;
    }

    public Date getFeedbacktime() {
        return feedbacktime;
    }

    public void setFeedbacktime(Date feedbacktime) {
        this.feedbacktime = feedbacktime;
    }

    public String getFeedbackresult() {
        return feedbackresult;
    }

    public void setFeedbackresult(String feedbackresult) {
        this.feedbackresult = feedbackresult;
    }

    @Override
    protected Serializable pkVal() {
        return this.eno;
    }

    @Override
    public String toString() {
        return "Examine{" +
        "eno=" + eno +
        ", sno=" + sno +
        ", cno=" + cno +
        ", ino=" + ino +
        ", edept=" + edept +
        ", etime=" + etime +
        ", eresult=" + eresult +
        ", feedbacktime=" + feedbacktime +
        ", feedbackresult=" + feedbackresult +
        "}";
    }
}
