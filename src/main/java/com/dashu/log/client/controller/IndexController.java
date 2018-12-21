package com.dashu.log.client.controller;


import com.dashu.log.client.ClientUtil;
import com.dashu.log.client.dao.ErrorTypeIdRepository;
import com.dashu.log.client.dao.IndexConfRepository;
import com.dashu.log.entity.IndexConf;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description index client
 * @Author: xuyouchang
 * @Date 2018/11/27 下午3:18
 **/
@RestController
public class IndexController {

    @Resource
    private IndexConfRepository indexConfRepository;
    @Resource
    private ClientUtil clientUtil;
    @Resource
    private ErrorTypeIdRepository errorTypeIdRepository;


    /** 删除单条index conf **/
    @RequestMapping(value = "/index/deleteSingleIndexConf",method = RequestMethod.GET)
    public boolean deleteSingleIndexConf(@RequestParam(value = "id")Integer id){
        if (id == null){
            return false;
        }
        try {
            indexConfRepository.deleteSingleIndexConfById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }


    /** get All Index Conf **/
    @RequestMapping(value = "/index/getAllIndexConf",method = RequestMethod.GET)
    public List<IndexConf> getAllIndexConf(){
        try {
            List<IndexConf> indexConfList = indexConfRepository.getAllIndexConf();
            return indexConfList;
        }catch (Exception e){
            return null;
        }

    }

    /** add Index Conf **/
    @RequestMapping(value = "/index/addIndexConf",method = RequestMethod.GET)
    public boolean addIndexConf(@RequestParam(value = "index")String index,
                               @RequestParam(value = "filed")String filed,
                               @RequestParam(value = "keywords")String keywords){
        if (index == "" || filed == "" || keywords == ""){
            return false;
        }
        try{
            indexConfRepository.addIndexConf(index,filed,keywords);
            return true;
        }catch (Exception e){
            return false;
        }


    }

    /**
     * add Forbid Id
     * @param errorTypeId
     * @return
     */
    @RequestMapping(value = "/index/addForbidId",method = RequestMethod.GET)
    public String addForbidId(@RequestParam(value = "errorTypeId")Integer errorTypeId){
        if (errorTypeId == null){
            return clientUtil.nullMessage();
        }
        try{
            errorTypeIdRepository.addFilterErrorType(errorTypeId);
            return clientUtil.successMessage(errorTypeId.toString());
        }catch (Exception e){
            return clientUtil.failMessage(e.toString());
        }

    }

    /**
     * get All Forbid Id
     * @return
     */
    @RequestMapping(value = "/index/getAllForbidId",method = RequestMethod.GET)
    public String getAllForbidId(){
        try{
            List<Integer> errorLogTypeList = errorTypeIdRepository.getAllFilterErrorId();
            return clientUtil.successMessage(errorLogTypeList.toString());
        }catch (Exception e){
            return clientUtil.failMessage(e.toString());
        }
    }

}
