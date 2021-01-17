package org.auto.plate.service.impl;

import org.auto.plate.entity.*;
import org.auto.plate.mapper.AutoCaseMapper;
import org.auto.plate.mapper.AutoElementMapper;
import org.auto.plate.service.AutoCaseService;
import org.auto.plate.service.AutoExceptionPageService;
import org.auto.plate.service.AutoProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.*;

/**
 * (AutoCase)表服务实现类
 *
 * @author makejava
 * @since 2020-04-18 23:34:05
 */
@Service("autoCaseService")
public class AutoCaseServiceImpl implements AutoCaseService {
    @Resource
    private AutoCaseMapper autoCaseMapper;

    @Resource
    private AutoProjectService autoProjectService;

    @Resource
    AutoCaseListObj autoCaseListObj;

    @Resource
    AutoElementMapper autoElementMapper;

    @Resource
    AutoExceptionPageService autoExceptionPageService;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AutoCase queryById(Integer id) {
        AutoCase autoCase = this.autoCaseMapper.queryById(id);
        List<AutoExceptionPages> list = autoExceptionPageService.selectByCaseId(autoCase.getId());
        if (list.size()>0) {
            List<InputStream> inputStreamList = new ArrayList<InputStream>();
            for (int i = 0; i < list.size(); i++) {
                inputStreamList.add(list.get(i).getPage());
            }
            autoCase.setPages(inputStreamList);
        }
        return autoCase;
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<AutoCase> queryAllByLimit(int offset, int limit) {
        return this.autoCaseMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param autoCase 实例对象
     * @return 实例对象
     */
    @Override
    public AutoCase insert(AutoCase autoCase) {
        this.autoCaseMapper.insert(autoCase);
        return autoCase;
    }

    /**
     * 修改数据
     *
     * @param autoCase 实例对象
     * @return 实例对象
     */
    @Override
    public AutoCase update(AutoCase autoCase) {
        this.autoCaseMapper.update(autoCase);
        return this.queryById(autoCase.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.autoCaseMapper.deleteById(id) > 0;
    }

    /**
     * 添加自动化测试用例case
     * @param autoCase
     * @return
     */
    @Override
    public RespBean addAutoTestCase(AutoCase autoCase) {
        AutoProject autoProject = autoProjectService.queryById(autoCase.getProid());
        autoCase.setProname(autoProject.getProjectname());
        Date date = new Date();
        autoCase.setCreatetime(date);
        autoCase.setStatus(1);
        Integer count = autoCaseMapper.insert(autoCase);
        AutoCase autoCase1 = autoCaseMapper.selectAutoCaseByCreateDate(date);
        if (count != 1) {
            return RespBean.error("添加失败！！！");
        }
        return RespBean.ok("添加成功！！！",autoCase1);
    }

    /**
     * 获取caselist 或者 templates
     * @param query
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public RespBean getAutoCaseOrTemplateListByUserId(String query, Integer userId, Integer pageNum, Integer pageSize, Integer type) {
        int firstNum = (pageNum-1) * pageSize;
        List<AutoCase> list = autoCaseMapper.selectAllAutoCaseByUser(query,userId,firstNum,pageSize, type);
        autoCaseListObj.setAutoCaseList(list);
        autoCaseListObj.setTotal(autoCaseMapper.queryAllCase(query, userId, type).size());
        if (autoCaseListObj.getTotal() == 0) {
            return RespBean.error("暂无用例！！！");
        }
        return RespBean.ok("获取成功！！！",autoCaseListObj);
    }

    /**
     * 获取项目下的所有template
     * @param projectId
     * @return
     */
    @Override
    public RespBean getAutoCaseOrTemplateProId(Integer projectId, Integer type) {
        List<AutoCase> list = autoCaseMapper.selectAllByProId(projectId, type);
        return RespBean.ok("获取成功！！！",list);
    }

    @Override
    public RespBean getAutoCaseCountByProId(Integer projectId) {
        int count = autoCaseMapper.selectAllAutoCaseByProId(projectId);
        return RespBean.ok("获取成功！！！",count);
    }

    @Override
    public RespBean getAutoTestCaseCountList(Integer userId, Integer type) {
        List<Integer> autoCaseCountList = new ArrayList<Integer>();
        List<AutoProject> list = autoProjectService.findUserProList(userId);
        for (int i = 0; i < list.size(); i++) {
            autoCaseCountList.add(autoCaseMapper.selectCaseCount(list.get(i).getId(),type));
        }
        return RespBean.ok("获取成功！！！",autoCaseCountList);
    }

    /**
     * 待完善
     * @param autoCaseList
     * @return
     */
    @Override
    public boolean delete(List<AutoCase> autoCaseList) {
        List<Integer> autoCaseIdList = new ArrayList<Integer>();
        for (int i = 0; i < autoCaseList.size(); i++) {
            autoCaseIdList.add(autoCaseList.get(i).getId());
        }
        for (int i = 0; i < autoCaseIdList.size(); i++) {
            autoCaseMapper.deleteById(autoCaseIdList.get(i));
        }
        return true;
    }

    @Override
    public RespBean getAllAutoTestCaseByProjectId(String query, Integer projectId, Integer pageNum, Integer pageSize, Integer type) {
        int firstNum = (pageNum-1) * pageSize;
        autoCaseListObj.setAutoCaseList(autoCaseMapper.selectAllAutoCaseByProject(query,projectId,firstNum,pageSize, type));
        autoCaseListObj.setTotal(autoCaseMapper.queryAllCaseCountByProject(projectId,type));
        if (autoCaseListObj.getTotal() == 0) {
            return RespBean.error("暂无用例！！！");
        }
        return RespBean.ok("获取成功！！！",autoCaseListObj);
    }

    @Override
    public RespBean findTemplateAndCaseList(String elelist, String pretemplate, String posttemplate, String paraname) {
        List<AutoElement> elementList = new ArrayList<AutoElement>();
        if (!elelist.equals("") && elelist != null) {
           String[] elements = elelist.split(",");
           String[] params = paraname.split(",");
           if (elements.length != params.length) {
               return RespBean.error("未知错误！！！");
           }
            for (int i = 0; i < elements.length; i++) {
                AutoElement autoElement = autoElementMapper.queryById(Integer.parseInt(elements[i]));
                autoElement.setParaname(params[i]);
                elementList.add(autoElement);
            }
        }
        // 前置和后置使用localId 来标示：1标示前置，2标示后置
        List<AutoCase> preList = getCaseList(pretemplate,1);
        List<AutoCase> postList = getCaseList(posttemplate,2);
        for (int i = 0; i < postList.size(); i++) {
            preList.add(postList.get(i));
        }
        Map<String,List> map = new HashMap<String,List>();
        map.put("elelist",elementList);
        map.put("templateList",preList);
        return RespBean.ok("获取成功！！！",map);
    }

    private List<AutoCase> getCaseList(String pretemplate, Integer localId) {
        List<AutoCase> preList = new ArrayList<AutoCase>();
        if (!pretemplate.equals("") && pretemplate != null) {
           String[] pretemplates = pretemplate.split(",");
            for (int i = 0; i < pretemplates.length; i++) {
                AutoCase autoCase = autoCaseMapper.queryById(Integer.parseInt(pretemplates[i]));
                autoCase.setLocalId(localId);
                preList.add(autoCase);
            }
        }
        return preList;
    }
}