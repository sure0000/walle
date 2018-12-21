package com.dashu.log.client.controller;

import com.dashu.log.dao.SelfAlterHistoryRepository;
import com.dashu.log.entity.SelfAlterHistory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/12/17 上午10:30
 **/
@RestController
public class AlterController {
    @Resource
    private SelfAlterHistoryRepository selfAlterHistoryRepository;

    /** 获取告警历史 **/
    @RequestMapping(value = "/alter/panel",method = RequestMethod.GET)
    public List<SelfAlterHistory> getAlterHistory(){
        return selfAlterHistoryRepository.getAllAlter();
    }

    /** 处理告警状态 **/
    @RequestMapping(value = "/alter/handle",method = RequestMethod.GET)
    public boolean updateAlterStatus(@RequestParam(value = "id")Integer id){
        try {
            selfAlterHistoryRepository.updateAlterStatusById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
