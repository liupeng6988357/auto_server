package org.auto.plate.service;

import org.auto.plate.entity.AutoExceptionPages;

import java.util.List;

public interface AutoExceptionPageService {

    int insertPage(AutoExceptionPages autoExceptionPages);

    List<AutoExceptionPages> selectByCaseId(int caseId);

    int deleteByCaseId(int caseId);
}
