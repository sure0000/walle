package com.dashu.log.client.controller;


import com.dashu.log.client.ClientUtil;
import com.dashu.log.client.dao.ErrorTypeIdRepository;
import com.dashu.log.client.dao.IndexConfRepository;
import com.dashu.log.entity.IndexConf;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description index client
 * @Author: xuyouchang
 * @Date 2018/11/27 下午3:18
 **/
@RestController
@CrossOrigin
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
    public JSONObject addForbidId(@RequestParam(value = "errorTypeId")Integer errorTypeId){
        if (errorTypeId == null){
            return clientUtil.responseMessage(0,"errorTypeId is null",null);
        }
        try{
            errorTypeIdRepository.addFilterErrorType(errorTypeId);
            return clientUtil.responseMessage(1,"",errorTypeId.toString());
        }catch (Exception e){
            return clientUtil.responseMessage(-1,e.getMessage(),null);
        }

    }

    /**
     * get All Forbid Id
     * @return
     */
    @RequestMapping(value = "/index/getAllForbidId",method = RequestMethod.GET)
    public JSONObject getAllForbidId(){
        try{
            List<Integer> errorLogTypeList = errorTypeIdRepository.getAllFilterErrorId();
            return clientUtil.responseMessage(1,"",errorLogTypeList.toString());
        }catch (Exception e){
            return clientUtil.responseMessage(-1,e.getMessage(),null);
        }
    }

}
