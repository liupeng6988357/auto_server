package org.auto.plate.service.impl;


import org.auto.plate.entity.Environmental;
import org.auto.plate.entity.RespBean;
import org.auto.plate.mapper.EnvironmentalMapper;
import org.auto.plate.mapper.UserMapper;
import org.auto.plate.service.EnvironmentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvironmentalServiceImpl implements EnvironmentalService {

    @Autowired
    EnvironmentalMapper environmentalMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public RespBean findParamsList(String query, Integer pageNum, Integer pageSize) {
        int firstNum = (pageNum - 1) * pageSize;
        List<Environmental> paramsList = environmentalMapper.findParamsList(query,firstNum,pageSize);
        if(paramsList.size() ==0) {
            return RespBean.error("暂无数据",paramsList);
        }
        for (int i = 0; i < paramsList.size(); i++) {
            paramsList.get(i).setCreateUser(userMapper.queryById(paramsList.get(i).getCreateby()).getUsername());
            paramsList.get(i).setUpdateUser(userMapper.queryById(paramsList.get(i).getUpdateby()).getUsername());
        }
        return RespBean.ok("获取成功",paramsList);
    }

    @Override
    public RespBean findEnvironment(String paramtype) {
        List<Environmental> environmentalList = environmentalMapper.findEnvironmentList(paramtype);
        if(environmentalList.size() ==0) {
            return RespBean.error("暂无数据",environmentalList);
        }
        return RespBean.ok("获取成功",environmentalList);
    }
}
