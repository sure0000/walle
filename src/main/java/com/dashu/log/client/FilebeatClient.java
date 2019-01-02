package com.dashu.log.client;

import com.dashu.log.client.dao.FilebeatConfRepository;
import com.dashu.log.entity.FilebeatConf;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description Filebeat Client
 * @Author: xuyouchang
 * @Date 2018/11/28 下午5:04
 **/
@RestController
public class FilebeatClient {
    @Resource
    private FilebeatConfRepository filebeatConfRepository;
    @Resource
    private ClientUtil clientUtil;

    /**
     * add Filebeat Conf
     * @param hostname
     * @return
     */
    @RequestMapping(value = "/filebeat/addFilebeatConf",method = RequestMethod.GET)
    public String addFilebeatConf(@RequestParam(value = "hostname")String hostname){
        if (hostname == null){
            return clientUtil.responseMessage(201,"filebeat hostname is null",null);
        }
        try {
            filebeatConfRepository.addHostname(hostname);
            return clientUtil.responseMessage(200,"",true);
        }catch (Exception e){
            return clientUtil.responseMessage(502,e.getMessage(),false);
        }

    }

    /**
     * get Filebeat Conf
     * @return
     */
    @RequestMapping(value = "/filebeat/getFilebeatConf",method = RequestMethod.GET)
    public String getFilebeatConf(){
        try {
            List<String> filebeatConfList = filebeatConfRepository.getAllHostname();
            return clientUtil.responseMessage(200,"",filebeatConfList);
        }catch (Exception e){
            return clientUtil.responseMessage(502,e.getMessage(),false);
        }

    }}
