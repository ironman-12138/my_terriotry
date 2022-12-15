package com.xtn.modules.system.service;


import com.xtn.modules.system.vo.wx.Quota;

public interface WxService {

    /**
     * 获取微信凭证
     * @return 微信凭证 accessToken
     */
    String getAccessToken();

    /**
     * 微信小程序登录
     * @param code 登录时获取的code
     * @return 用户信息
     */
    String wxLogin(String code);

    /**
     * 获取手机号
     * @param code 动态令牌code
     * @return 手机号
     */
    String getPhoneNumber(String code);

    /**
     * 查询API调用额度
     * @param accessToken 微信凭证
     * @param path api的请求地址
     * @return API调用额度
     */
    Quota getApiQuota(String accessToken, String path);
}
