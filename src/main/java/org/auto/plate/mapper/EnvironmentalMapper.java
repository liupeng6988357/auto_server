package org.auto.plate.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.auto.plate.entity.Environmental;

import java.util.List;

@Mapper
public interface EnvironmentalMapper {


    List<Environmental> findParamsList(@Param("query") String query, @Param("firstNum") int firstNum, @Param("pageSize") Integer pageSize);

    List<Environmental> findEnvironmentList(String paramtype);
}
