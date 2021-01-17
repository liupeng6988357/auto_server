package org.auto.plate.service.impl;


import org.auto.plate.entity.AutoExceptionPages;
import org.auto.plate.mapper.AutoExceptionPagesMapper;
import org.auto.plate.service.AutoExceptionPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoExceptionPageServiceImpl implements AutoExceptionPageService {


    @Autowired
    AutoExceptionPagesMapper autoExceptionPagesMapper;

    @Override
    public int insertPage(AutoExceptionPages autoExceptionPages) {
        return autoExceptionPagesMapper.insert(autoExceptionPages);
    }

    @Override
    public List<AutoExceptionPages> selectByCaseId(int caseId) {
        return autoExceptionPagesMapper.selectByCaseId(caseId);
    }

    @Override
    public int deleteByCaseId(int caseId) {
        return autoExceptionPagesMapper.deleteByCaseId(caseId);
    }
}
