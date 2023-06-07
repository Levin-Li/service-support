package com.levin.commons.service.exception;

import com.levin.commons.service.domain.ServiceResp;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 服务异常
 * <p/>
 * <p/>
 * Created by echo on 2015/11/22.
 *
 * @author lilw
 */
@Schema(title = "服务异常")
public class ServiceException
        extends BaseException {

    private static final long serialVersionUID = -677894470754649710L;

    public ServiceException(String friendlyTips) {
        super(friendlyTips);
    }

    public ServiceException(int code, String friendlyTips) {
        super(code, friendlyTips);
    }

    public ServiceException(int code, String friendlyTips, String message) {
        super(code, friendlyTips, message);
    }

    public ServiceException(int code, String friendlyTips, String message, Throwable cause) {
        super(code, friendlyTips, message, cause);
    }

    @Override
    protected int getBaseCode() {
        return ServiceResp.ErrorType.BizError.code();
    }

}
