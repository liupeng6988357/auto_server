package org.auto.plate.entity;

import java.io.InputStream;

public class AutoExceptionPages {

    private Integer id;

    private Integer caseId;

    private InputStream page;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public InputStream getPage() {
        return page;
    }

    public void setPage(InputStream page) {
        this.page = page;
    }
}
