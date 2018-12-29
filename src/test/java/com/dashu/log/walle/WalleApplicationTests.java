package com.dashu.log.walle;

import com.dashu.log.Walle;
import com.dashu.log.alter.WalleNotify;
import com.dashu.log.client.ClientUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WalleApplicationTests {
    @Resource
    private ClientUtil clientUtil;



    @Test
    public void contextLoads() {
//        clientUtil.writeCookie(response,"xyc","123");
    }

}
