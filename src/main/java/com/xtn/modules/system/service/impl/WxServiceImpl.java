package com.xtn.modules.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xtn.common.config.WxConfig;
import com.xtn.common.utils.HttpUtil;
import com.xtn.common.utils.RetryTemplate;
import com.xtn.modules.system.service.WxService;
import com.xtn.modules.system.vo.wx.Quota;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xCoder
 */
@Slf4j
@Service
public class WxServiceImpl implements WxService {

    private Map<String, Object> requestParam = null;

    @Override
    public String getAccessToken() {
        initRequestParam();
        requestParam.put("grant_type", WxConfig.GRANT_TYPE_CLIENT_CREDENTIAL);
        Object result = sendRequest(WxConfig.WX_URL + WxConfig.WxApiEnum.TOKEN_API.getValue(), requestParam, HttpMethod.GET);
        JSONObject jsonObject = JSONObject.parseObject(result.toString());
        return jsonObject.getString("access_token");
    }

    @Override
    public String wxLogin(String code) {
        initRequestParam();
        requestParam.put("js_code", code);
        requestParam.put("grant_type", WxConfig.GRANT_TYPE_AUTHORIZATION_CODE);
        Object result = sendRequest(WxConfig.WX_URL + WxConfig.WxApiEnum.LOGIN_API.getValue(), requestParam, HttpMethod.GET);
        JSONObject jsonObject = JSONObject.parseObject(result.toString());
        return jsonObject.getString("openid");
    }

    @Override
    public String getPhoneNumber(String code) {
        String accessToken = getAccessToken();
        requestParam = new HashMap<>();
        requestParam.put("code", code);
        Object result = sendRequest(WxConfig.WX_URL + WxConfig.WxApiEnum.GET_PHONE_API.getValue() + accessToken, requestParam, HttpMethod.POST);
        JSONObject jsonObject = JSONObject.parseObject(result.toString());
        JSONObject phoneInfo = jsonObject.getJSONObject("phone_info");
        return phoneInfo.getString("purePhoneNumber");
    }

    @Override
    public Quota getApiQuota(String accessToken, String path) {
        requestParam = new HashMap<>();
        requestParam.put("cgi_path", path);
        Object result = sendRequest(WxConfig.WX_URL + WxConfig.WxApiEnum.GET_QUOTA_API.getValue() + accessToken, requestParam, HttpMethod.POST);
        JSONObject jsonObject = JSONObject.parseObject(result.toString());
        Quota quota = null;
        if (jsonObject.getInteger("errcode") == 0) {
            quota = jsonObject.getObject("quota", Quota.class);
        }
        return quota;
    }

    public void initRequestParam() {
        requestParam = new HashMap<>();
        requestParam.put("appid", WxConfig.APP_ID);
        requestParam.put("secret", WxConfig.SECRET);
    }

    public Object sendRequest(String url, Map<String, Object> requestParam, HttpMethod method) {
        Object result = null;
        try {
            result = new RetryTemplate() {
                @Override
                protected Object retry() {
                    switch (method) {
                        case GET:
                            return HttpUtil.get(url, requestParam);
                        case POST:
                            return HttpUtil.post(url, requestParam);
                        default:
                            return null;
                    }
                }
            }.setRetryNum(3).execute();
        } catch (Throwable throwable) {
            log.error("调用微信接口请求失败，错误原因：{}", throwable.getMessage());
        }
        return result;
    }
}
