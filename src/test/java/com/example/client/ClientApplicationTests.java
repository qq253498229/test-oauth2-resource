package com.example.client;

import com.example.client.config.ResourceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(ResourceConfig.class)
public class ClientApplicationTests {

    @Test
    public void contextLoads() {
    }

}
