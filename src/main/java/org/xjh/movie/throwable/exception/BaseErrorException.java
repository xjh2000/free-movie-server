package org.xjh.movie.throwable.exception;

import org.xjh.movie.throwable.model.BaseError;

/**
 * @author xjh
 * @date 6/20/2022 4:07 PM
 */
public class BaseErrorException extends RuntimeException {
    public BaseError error;

    public BaseErrorException(BaseError error) {
        this.error = error;
    }

}
