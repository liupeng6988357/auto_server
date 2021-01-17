package org.auto.plate.service;

import org.auto.plate.entity.RespBean;
import org.springframework.stereotype.Service;

@Service
public interface EnvironmentalService {
    RespBean findParamsList(String query, Integer pageNum, Integer pageSize);

    RespBean findEnvironment(String paramtype);
}
