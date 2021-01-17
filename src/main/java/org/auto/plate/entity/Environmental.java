package org.auto.plate.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Environmental {
    private Integer id;
    private String paramsname;
    private String paramstype;
    private String paramsvalue;
    private String paramsdescription;
    private Date createtime;
    private Integer createby;
    private Date updatetime;
    private Integer updateby;
    private String createUser;
    private String updateUser;

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParamsname() {
        return paramsname;
    }

    public void setParamsname(String paramsname) {
        this.paramsname = paramsname;
    }

    public String getParamstype() {
        return paramstype;
    }

    public void setParamstype(String paramstype) {
        this.paramstype = paramstype;
    }

    public String getParamsvalue() {
        return paramsvalue;
    }

    public void setParamsvalue(String paramsvalue) {
        this.paramsvalue = paramsvalue;
    }

    public String getParamsdescription() {
        return paramsdescription;
    }

    public void setParamsdescription(String paramsdescription) {
        this.paramsdescription = paramsdescription;
    }
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd hh:mm:ss")
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getCreateby() {
        return createby;
    }

    public void setCreateby(Integer createby) {
        this.createby = createby;
    }
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd hh:mm:ss")
    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getUpdateby() {
        return updateby;
    }

    public void setUpdateby(Integer updateby) {
        this.updateby = updateby;
    }
}
