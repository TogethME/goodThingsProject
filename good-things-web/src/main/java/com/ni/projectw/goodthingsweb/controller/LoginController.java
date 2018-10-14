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

@Controller
public class LoginController {

    private final String IDY_SUBMIT_URL = "http://haoren.dc.10086.cn/hrhs/sendCode.do?";
    private final String LOGIN_SUBMIT_URL = "http://dc.10086.cn/SSOServer/user/login.do?";

    private static String token;

    @GetMapping("/getPhoneIdy")
    @ResponseBody
    public String getPhoneIdy(String phone,String idy,String cookie) throws HttpProcessException {
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
    public String toLogin(String phone,String idy,String phoneIdy) throws HttpProcessException {
        String url = LOGIN_SUBMIT_URL;

        url += "phone="+phone+"&dxCode="+phoneIdy+"&imgCode="+idy+"&channelId=3&redirectURL=100&type=0&timestamp="+System.currentTimeMillis();
        HttpConfig config = HttpConfig.custom();
        Header[] headers=HttpHeader.custom().userAgent("Mozilla/5.0")
                .other("Accept","application/json, text/plain, */*")
                .other("Accept-Encoding: ","gzip, deflate")
                .other("Accept-Language: "," zh-CN,zh;q=0.9")
           /*     .other("Host"," dc.10086.cn")
                .other("Origin","http://haoren.dc.10086.cn")*/
                .other("Proxy-Connection","keep-alive")
               // .other("Referer","http://haoren.dc.10086.cn")
                .other("X-Requested-With","XMLHttpRequest")
             //  .other("Content-Length","117")
                .build();
        config.headers(headers);
        config.url(url);
        String options = HttpClientUtil.get(config);
        saveCookie(options);
//        options.substring()
        return options;
    }

    private void saveCookie(String option){
        option = option.substring(4,option.length());
        JSONObject jsonObject = JSONObject.parseObject(option.substring(1,option.length()-1));
        token = jsonObject.getString("token");
    }

}
