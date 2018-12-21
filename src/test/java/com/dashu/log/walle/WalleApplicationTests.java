package com.dashu.log.walle;

import com.dashu.log.Walle;
import com.dashu.log.alter.WalleNotify;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WalleApplicationTests {


    @Test
    public void contextLoads() {
        WalleNotify notify = new WalleNotify();
        notify.sendMessage("walle","walle is start","walle");
    }

}
