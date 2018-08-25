package com.dhu.managerweb.client;

import com.dhu.managerweb.VO.ClothesVO;
import com.dhu.managerweb.VO.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * creater: LIUYING
 * date:2018/8/24 19:30
 */

@FeignClient(name = "clothes",url = "http://localhost:9010/clothes")
public interface ClothesClient {

    @GetMapping("/clothesInfoForRecommend/{style}/{skin}")
    ResultVO<ClothesVO> list(@PathVariable("style") String style, @PathVariable("skin") String skin);
}
