package com.donbala.model;

import java.util.Date;

public class CmsCodedef extends CmsCodedefKey {
    private String codename;

    private String codealias;

    private String othersign;

    private String source;

    private Date makedate;

    private String makeuser;

    private Date modifydate;

    private String modifyuser;

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    public String getCodealias() {
        return codealias;
    }

    public void setCodealias(String codealias) {
        this.codealias = codealias;
    }

    public String getOthersign() {
        return othersign;
    }

    public void setOthersign(String othersign) {
        this.othersign = othersign;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getMakedate() {
        return makedate;
    }

    public void setMakedate(Date makedate) {
        this.makedate = makedate;
    }

    public String getMakeuser() {
        return makeuser;
    }

    public void setMakeuser(String makeuser) {
        this.makeuser = makeuser;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public String getModifyuser() {
        return modifyuser;
    }

    public void setModifyuser(String modifyuser) {
        this.modifyuser = modifyuser;
    }
}