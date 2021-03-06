package com.eight.server.Database.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 测试基本表
 * </p>
 *
 * @author Aiden_Z
 * @since 2018-12-28
 */
@TableName("db_test")
public class Test extends Model<Test> {

    private static final long serialVersionUID = 1L;

    /**
     * 测试编号
     */
    @TableId
    private String tno;

    /**
     * 来访者编号
     */
    private String sno;

    /**
     * 咨询师编号
     */
    private String cno;

    /**
     * 量表编号
     */
    private String scno;

    /**
     * 测试分数
     */
    private Integer tscore;

    /**
     * 测试结果
     */
    private String tresult;

    /**
     * 测试时间
     */
    private LocalDateTime ttime;

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
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
    public String getScno() {
        return scno;
    }

    public void setScno(String scno) {
        this.scno = scno;
    }
    public Integer getTscore() {
        return tscore;
    }

    public void setTscore(Integer tscore) {
        this.tscore = tscore;
    }
    public String getTresult() {
        return tresult;
    }

    public void setTresult(String tresult) {
        this.tresult = tresult;
    }
    public LocalDateTime getTtime() {
        return ttime;
    }

    public void setTtime(LocalDateTime ttime) {
        this.ttime = ttime;
    }

    @Override
    protected Serializable pkVal() {
        return this.tno;
    }

    @Override
    public String toString() {
        return "Test{" +
        "tno=" + tno +
        ", sno=" + sno +
        ", cno=" + cno +
        ", scno=" + scno +
        ", tscore=" + tscore +
        ", tresult=" + tresult +
        ", ttime=" + ttime +
        "}";
    }
}
