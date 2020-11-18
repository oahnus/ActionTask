package com.github.oahnus.actions.dto;

import lombok.Data;

/**
 * Created by oahnus on 2020-11-18
 */
@Data
public class DingRet {
    private Integer errcode;
    private String errmsg;

    public boolean isSuccess() {
        return errcode != null && errcode == 0;
    }
}
