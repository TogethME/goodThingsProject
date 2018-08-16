package com.ni.projectw.goodthingsweb.controller;

import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import org.apache.http.Header;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @GetMapping("/create")
    @ResponseBody
    public String create(String phone,String idy) {

        System.out.println("hello");
        return "";
    }

    public static void main(String[] args) {
        String url = "http://dc.10086.cn/SSOServer/user/login.do";
        HttpConfig config = HttpConfig.custom();
        Header[] headers=HttpHeader.custom().userAgent("Mozilla/5.0").build();
        config.headers(headers);
//        HttpClientUtil.options()
    }

}
