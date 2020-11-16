package com.levin.commons.enums;


public enum VerificationMode {

    //@Desc("无二次验证")
    NONE,
    //@Desc("图片验证")
    IMAGE,
    //@Desc("短信验证")
    SM,
    //@Desc("微信扫码")
    WX_SCAN,
    //@Desc("两步验证")
    TOTP;
}
