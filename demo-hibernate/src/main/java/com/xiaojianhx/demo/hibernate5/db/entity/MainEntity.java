package com.xiaojianhx.demo.hibernate5.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class MainEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "del_flg", nullable = false, columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '逻辑状态：0-正常；1-删除；'")
    private short defFlg;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public short getDefFlg() {
        return defFlg;
    }

    public void setDefFlg(short defFlg) {
        this.defFlg = defFlg;
    }
}