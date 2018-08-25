package com.dhu.managerweb.client;

import com.dhu.managerweb.VO.ClothesInfoVO;
import com.dhu.managerweb.VO.ClothesVO;
import com.dhu.managerweb.VO.ResultVO;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * creater: LIUYING
 * date:2018/8/24 19:30
 */

@FeignClient(name = "clothes",url = "http://localhost:9010/clothes")
public interface ClothesClient {

    @RequestMapping(value = "/clothesInfoForRecommend/{style}/{skin}",method = RequestMethod.GET)
    ResultVO<ClothesVO> list(@PathVariable("style") String style, @PathVariable("skin") String skin);

    @RequestMapping(value = "/recommendClothes/{style}/{skin}",method = RequestMethod.GET)
    List<ClothesVO> listForRecommend(@PathVariable("style") String style, @PathVariable("skin") String skin);
}
