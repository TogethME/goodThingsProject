package com.ni.projectw.goodthingsweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.ni.projectw.goodthingsweb.dao.UserMapper;
import com.ni.projectw.goodthingsweb.model.User;
import com.ni.projectw.goodthingsweb.pojo.GoodThings;
import org.apache.http.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @Autowired
    private UserMapper userMapper;

    private final String IDY_SUBMIT_URL = "http://haoren.dc.10086.cn/hrhs/sendCode.do?";
    private final String LOGIN_SUBMIT_URL = "http://dc.10086.cn/SSOServer/user/login.do?";
    private final String RECOMMEND_GOODPEOPLE_URL = "http://haoren.dc.10086.cn/hrhs/recommend_goodpeople.do?";

    private static String COOKIE_TOKEN;

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
        String returnCode = saveCookie(phone, options);
        if("100".equals(returnCode)){
            return "toContent";
        }
//        options.substring()
        return options;
    }

    //推荐好人好事
    public String recommend(GoodThings goodThings) throws HttpProcessException {
        String url = RECOMMEND_GOODPEOPLE_URL;

        url +=  "srcMobile="+goodThings.getSrcMobile()
                //电话
                +"&srcName="+goodThings.getSrcName()
                //推荐人
                +"&srcProvince="+goodThings.getSrcProvince()
                //推荐人省份
                +"&srcCity="+goodThings.getSrcCity()
                //推荐人城市
                +"&categoryType="+goodThings.getCategoryType()
                //被推荐人名字
                +"&recName="+goodThings.getRecName()
                //被推荐人省份
                +"&recProvince="+goodThings.getRecProvince()
                //被推荐人城市
                +"&recCity="+goodThings.getRecCity()
                //推荐内容
                +"&content="+goodThings.getContent()
                +"&timestamp="+System.currentTimeMillis();
        HttpConfig config = HttpConfig.custom();
        Header[] headers=HttpHeader.custom().userAgent("Mozilla/5.0")
                .other("Accept","application/json, text/plain, */*")
                .other("Accept-Encoding: ","gzip, deflate")
                .other("Accept-Language: "," zh-CN,zh;q=0.9")
                .other("Cookie: ",COOKIE_TOKEN)
                /*     .other("Host"," dc.10086.cn")
                     .other("Origin","http://haoren.dc.10086.cn")*/
                .other("Proxy-Connection","keep-alive")
                // .other("Referer","http://haoren.dc.10086.cn")
                .other("X-Requested-With","XMLHttpRequest")
                //  .other("Content-Length","117")
                .build();
        config.headers(headers);
        config.url(url);
        String get = HttpClientUtil.get(config);
        String returnCode = JSONObject.parseObject(get).getString("returnCode");

        return returnCode;
    }

    private String saveCookie(String phone,String option){
        option = option.substring(4,option.length());
        JSONObject jsonObject = JSONObject.parseObject(option.substring(1,option.length()-1));
        COOKIE_TOKEN = "haoren_mobile="+phone+"; fangshuaiSheji="+jsonObject.getString("token");
        return jsonObject.getString("returnCode");
    }

    @GetMapping("/insertUser")
    @ResponseBody
    public String insertUser() throws HttpProcessException {
        User user = new User();
        user.setName("wangbin");
        userMapper.insert(user);
        return "插入成功,user=" +user;
    }

    @GetMapping("/getAll")
    @ResponseBody
    public String getAll() throws HttpProcessException {
        return userMapper.selectAll().toString();
    }

}
