package com.ni.projectw.goodthingsweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import org.apache.http.Header;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    private final String IDY_SUBMIT_URL = "http://haoren.dc.10086.cn/hrhs/sendCode.do?";
    private final String LOGIN_SUBMIT_URL = "http://dc.10086.cn/SSOServer/user/login.do";

    @GetMapping("/getPhoneIdy")
    @ResponseBody
    public String create(String phone,String idy,String cookie) throws HttpProcessException {
        String url = IDY_SUBMIT_URL;
        url += "code="+idy + "&mobile=" +phone +"&channelId=3&timestamp="+System.currentTimeMillis();
        HttpConfig config = HttpConfig.custom();
        Header[] headers=HttpHeader.custom().userAgent("Mozilla/5.0").other("SSO-cookie","sso_yzm_uuid3="+cookie).build();
        config.headers(headers);
        config.url(url);
        String s = HttpClientUtil.get(config);
        return s;
    }

    @GetMapping("/login")
    @ResponseBody
    public String toLogin(String phone,String dxCode,String imaCode) throws HttpProcessException {


        String url ="http://haoren.dc.10086.cn/hrhs/sendImageCode.do?channelId=3&timestamp="+System.currentTimeMillis();
        HttpConfig config = HttpConfig.custom();
        Header[] headers=HttpHeader.custom().userAgent("Mozilla/5.0").build();
        config.headers(headers);
        config.url(url);
        Map<String, Object> map = new HashMap<>();
        map.put("phone",phone);
        map.put("dxCode",dxCode);
        map.put("imaCode",imaCode);
        map.put("channelId",3);
        map.put("redirectURL",100);
        map.put("type",0);
        map.put("timestamp",System.currentTimeMillis());
        config.map(map);
        String options = HttpClientUtil.options(config);
        return options;
    }

    public static void main(String[] args) throws HttpProcessException {

        String url ="http://haoren.dc.10086.cn/hrhs/sendImageCode.do?channelId=3&timestamp="+System.currentTimeMillis();
        HttpConfig config = HttpConfig.custom();
        Header[] headers=HttpHeader.custom().userAgent("Mozilla/5.0").build();
        config.headers(headers);
        config.url(url);
        String s = HttpClientUtil.get(config);
        JSONObject json = JSONObject.parseObject(s);
        JSONObject resultJson = JSONObject.parseObject(json.getString("result"));
        String cookie = resultJson.getString("cookie").split(";")[0];
        System.out.println(System.currentTimeMillis());
//        HttpClientUtil.options()
    }

}
