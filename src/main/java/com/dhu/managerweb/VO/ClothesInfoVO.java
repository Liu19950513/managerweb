package com.dhu.managerweb.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * creater: LIUYING
 * date: 2018-8-13 20:34
 */
@Data
/**
 * 返回给前台页面所需要的服装信息对象
 */
public class ClothesInfoVO {
    //@JsonProperty("id")
    private Long clothesId;

    private String title;

    private String description;

    private String url;

    private Integer category;


}
