package com.lsp.pojo.home.response.subclass;

import com.lsp.pojo.home.response.subclass.Scheme;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: LinShanPeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchemeVideo extends Scheme {
    private Integer type = 4;
    private String route = "视频";
    private String videoUrl;

    public SchemeVideo(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
