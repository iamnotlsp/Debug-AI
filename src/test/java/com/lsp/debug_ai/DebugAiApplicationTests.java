package com.lsp.debug_ai;

import com.lsp.utils.MyUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class DebugAiApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    void testOKHttp() throws IOException {
        String run = new MyUtil().run("http://47.115.202.222:8899/member/collection/get");
        System.out.println(run);
    }



}
