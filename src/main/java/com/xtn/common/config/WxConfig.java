package com.xtn.common.config;

/**
 * 微信配置
 * @author xCoder
 */
public interface WxConfig {

    String APP_ID = "111";
    String SECRET = "222";
    String WX_URL = "https://api.weixin.qq.com";
    //授权类型-获取授权凭证
    String GRANT_TYPE_CLIENT_CREDENTIAL = "client_credential";
    //授权类型-小程序登录
    String GRANT_TYPE_AUTHORIZATION_CODE = "authorization_code";

    enum WxApiEnum {

        TOKEN_API("/cgi-bin/token", "获取接口调用凭据"),
        LOGIN_API("/sns/jscode2session", "小程序登录"),
        GET_PHONE_API("/wxa/business/getuserphonenumber?access_token=", "获取手机号"),
        GET_QUOTA_API("/cgi-bin/openapi/quota/get?access_token=", "查询API调用额度");

        private String value;

        private String content;

        WxApiEnum(String value, String content) {
            this.value = value;
            this.content = content;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

}
