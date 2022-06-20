package org.xjh.movie.throwable.mapper;

import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import org.xjh.movie.throwable.exception.BaseErrorException;
import org.xjh.movie.throwable.model.BaseError;

/**
 * @author xjh
 * @date 6/20/2022 4:17 PM
 */
public class ExceptionMappers {

    @ServerExceptionMapper
    public RestResponse<BaseError> BaseErrorToRestResponse(BaseErrorException e) {
        return RestResponse.status(e.error.status, e.error);
    }
}
