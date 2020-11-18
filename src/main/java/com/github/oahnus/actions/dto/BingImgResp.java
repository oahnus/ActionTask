package com.github.oahnus.actions.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oahnus on 2020-11-18
 */
@Data
public class BingImgResp {
    private List<BImage> images = new ArrayList<BImage>();

    public static @Data class BImage {
        private String url;
        private String copyright;
        private String title;
        private Boolean wp;
    }
}
