package com.levin.commons.service.exception;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * 业务异常，通常用于展示给用户看
 * <p/>
 * <p/>
 * Created by echo on 2015/11/22.
 *
 * @author lilw
 */
@Schema(title = "业务异常")
public class BizException
        extends BaseException {

    private static final long serialVersionUID = -677894470754649710L;

    public BizException(int code, String friendlyTips) {
        super(code, friendlyTips);
    }

    public BizException(int code, String message, String friendlyTips) {
        super(code, message, friendlyTips);
    }

    public BizException(int code, String friendlyTips, String message, Throwable cause) {
        super(code, friendlyTips, message, cause);
    }
}
