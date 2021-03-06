package com.xiaojianhx.demo.hibernate5.db.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends MainEntity {

    @Column(name = "account", nullable = false, columnDefinition = "VARCHAR(20) DEFAULT '' COMMENT '账号'")
    private String account;

    @Column(name = "mobile", nullable = false, columnDefinition = "VARCHAR(20) DEFAULT '' COMMENT '手机号码'")
    private String mobile;

    @Column(name = "email", nullable = false, columnDefinition = "VARCHAR(50) DEFAULT '' COMMENT '邮箱'")
    private String email;

    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(50) DEFAULT '' COMMENT '密码'")
    private String password;

    @Column(name = "salt", nullable = false, columnDefinition = "VARCHAR(50) DEFAULT '' COMMENT '密码盐'")
    private String salt;

    @Column(name = "state", nullable = false, columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '审核状态：0-未审核；1-已审核；2-审核不通过；'")
    private short state;

    @Embedded
    private Update update;

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private Set<Role> roleSet;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Update getUpdate() {
        return update;
    }

    public void setUpdate(Update update) {
        this.update = update;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }
}