package org.auto.plate.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.auto.plate.entity.AutoExceptionPages;

import java.util.List;

@Mapper
public interface AutoExceptionPagesMapper {

    Integer insert(AutoExceptionPages autoExceptionPages);

    List<AutoExceptionPages> selectByCaseId(Integer caseId);

    Integer deleteByCaseId(int caseId);
}
