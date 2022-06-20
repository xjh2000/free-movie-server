package org.xjh.movie.throwable.model;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.jboss.resteasy.reactive.RestResponse;

/**
 * @author xjh
 * @date 6/20/2022 4:05 PM
 */
public class BaseError {
    public RestResponse.Status status;
    public String message;

    @Schema(nullable = true)
    public String detail;
}
