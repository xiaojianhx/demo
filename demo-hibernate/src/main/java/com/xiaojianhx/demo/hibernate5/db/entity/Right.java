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
@Table(name = "rights")
public class Right extends MainEntity {

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(20) DEFAULT '' COMMENT '名称'")
    private String name;

    @Embedded
    private Update update;

    @ManyToMany
    @JoinTable(name = "role_rights", joinColumns = { @JoinColumn(name = "right_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private Set<Role> roleSet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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