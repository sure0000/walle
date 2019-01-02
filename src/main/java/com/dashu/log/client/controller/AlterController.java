package com.dashu.log.client.controller;

import com.dashu.log.client.ClientUtil;
import com.dashu.log.dao.SelfAlterHistoryRepository;
import com.dashu.log.entity.SelfAlterHistory;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/12/17 上午10:30
 **/
@RestController
@CrossOrigin
public class AlterController {
    @Resource
    private SelfAlterHistoryRepository selfAlterHistoryRepository;
    @Resource
    private ClientUtil clientUtil;

    /** 获取告警历史 **/
    @RequestMapping(value = "/alter/panel",method = RequestMethod.GET)
    public String getAlterHistory(){
        try {
            List<SelfAlterHistory> list = selfAlterHistoryRepository.getAllAlter();
            return clientUtil.responseMessage(200,"",list);
        }catch (Exception e){
            return clientUtil.responseMessage(502,e.getMessage(),null);
        }
    }

    /** 处理告警状态 **/
    @RequestMapping(value = "/alter/handle",method = RequestMethod.GET)
    public String updateAlterStatus(@RequestParam(value = "id")Integer id){
        try {
            selfAlterHistoryRepository.updateAlterStatusById(id);
            return clientUtil.responseMessage(200,"",true);
        }catch (Exception e){
            return clientUtil.responseMessage(502,e.getMessage(),false);
        }
    }
}
