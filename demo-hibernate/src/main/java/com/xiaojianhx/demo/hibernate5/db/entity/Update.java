package com.xiaojianhx.demo.hibernate5.db.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Update {

    @Column(name = "reg_id", nullable = false, columnDefinition = "int(11) DEFAULT 0 COMMENT '创建人ID'")
    private int regId;

    @Column(name = "reg_time", nullable = false, columnDefinition = "DATETIME COMMENT '创建时间'")
    private Timestamp regTime;

    @Column(name = "upp_id", nullable = false, columnDefinition = "int(11) DEFAULT 0 COMMENT '创建人ID'")
    private int uppId;

    @Column(name = "upp_time", nullable = false, columnDefinition = "DATETIME COMMENT '修改时间'")
    private Timestamp uppTime;

    public int getRegId() {
        return regId;
    }

    public void setRegId(int regId) {
        this.regId = regId;
    }

    public Timestamp getRegTime() {
        return regTime;
    }

    public void setRegTime(Timestamp regTime) {
        this.regTime = regTime;
    }

    public int getUppId() {
        return uppId;
    }

    public void setUppId(int uppId) {
        this.uppId = uppId;
    }

    public Timestamp getUppTime() {
        return uppTime;
    }

    public void setUppTime(Timestamp uppTime) {
        this.uppTime = uppTime;
    }
}