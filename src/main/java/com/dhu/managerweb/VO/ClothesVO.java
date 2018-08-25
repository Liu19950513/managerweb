package com.dhu.managerweb.VO;

import lombok.Data;

import java.util.List;

/**
 * creater: LIUYING
 * date:2018/8/15 16:21
 */
@Data
/**
 * 按类别排列的服装信息对象
 */
public class ClothesVO {

    //private String categoryName;
    /**
     * 服装主类别
     * 四类：1（上衣）2（外套）3（裙装）4（裤子）
     */
    private Integer categoryType;

    List<ClothesInfoVO> clothesInfoVoList;
}
