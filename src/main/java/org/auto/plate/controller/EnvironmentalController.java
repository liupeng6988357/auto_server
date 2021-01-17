package org.auto.plate.controller;

import org.auto.plate.entity.RespBean;
import org.auto.plate.service.EnvironmentalService;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * (Environmental)表控制层
 *
 * @author makejava
 * @since 2020-04-18 23:34:05
 */
@RestController
@RequestMapping("api")
public class EnvironmentalController {


    @Autowired
    EnvironmentalService environmentalService;

    /**
     * 分页获取全部参数列表
     * @param query
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/findparamslist", method = RequestMethod.GET)
    public RespBean findParamsList(@RequestParam String query, Integer pageNum, Integer pageSize) {
        return environmentalService.findParamsList(query,pageNum,pageSize);
    }

    @GetMapping("/findEnvironment")
    public RespBean findEnvironment(@RequestParam String paramtype) {
        return environmentalService.findEnvironment(paramtype);
    }
}
