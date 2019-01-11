package com.dashu.log.client.controller;

import com.dashu.log.client.ClientUtil;
import com.dashu.log.dao.SelfAlterHistoryRepository;
import com.dashu.log.entity.SelfAlterHistory;
import com.dashu.log.entity.User;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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

    /** 获取未处理告警 **/
    @RequestMapping(value = "/alter/panel",method = RequestMethod.GET)
    public String getUnHandledAlterHistory(HttpServletRequest request,
                                           HttpSession session){
        String cookieName = clientUtil.getCookie(request,"token");
        User user = (User)session.getAttribute(cookieName);
        try {
            List<SelfAlterHistory> list = selfAlterHistoryRepository.getUnhandledAlter(user.getUsername());
            return clientUtil.responseMessage(200,"",list);
        }catch (Exception e){
            return clientUtil.responseMessage(502,e.getMessage(),null);
        }
    }

    /**获取已处理告警**/
    @RequestMapping(value = "/alter/panel/handled",method = RequestMethod.GET)
    public String getHandledAlterHistory(@RequestParam(value = "page")Integer page,
                                         HttpServletRequest request,
                                         HttpSession session){
        String cookieName = clientUtil.getCookie(request,"token");
        User user = (User)session.getAttribute(cookieName);
        Integer pageSize = 10;
        try {
            List<SelfAlterHistory> list = selfAlterHistoryRepository.getHandledAlter(user.getUsername());
            int total = list.size();
            if (list.size() >= page*pageSize){
                list = list.subList((page-1)*pageSize,page*pageSize);
            }else if (list.size() != 0){
                list = list.subList((page-1)*pageSize,list.size());
            }
            JSONObject result = new JSONObject();
            result.put("total",total);
            result.put("list",list);
            result.put("currentPage",page);
            return clientUtil.responseMessage(200,"",result);
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
