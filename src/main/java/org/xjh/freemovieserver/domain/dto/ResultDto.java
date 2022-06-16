package org.xjh.freemovieserver.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author xjh
 * @date 6/16/2022 3:02 PM
 */
@Data
@AllArgsConstructor
public class ResultDto<T> {
    private String code;
    private String message;
    private T data;
}
