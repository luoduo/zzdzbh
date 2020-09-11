package com.zzhl.contorller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzhl.common.entity.TBAreaDict;
import com.zzhl.common.service.TBAreaDictService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 中华人民共和国国家标准 GB/T 2260-2007 行政区划代码 字典表 前端控制器
 * </p>
 *
 * @author tanggewei
 * @since 2020-09-11
 */
@RestController
public class TBAreaDictController {
    private final TBAreaDictService areaDictService;

    public TBAreaDictController(TBAreaDictService areaDictService) {
        this.areaDictService = areaDictService;
    }


    @GetMapping("/area")
    public Object area(){

        System.out.println("1111111");
        TBAreaDict one = areaDictService.getOne(
                new LambdaQueryWrapper<TBAreaDict>()
                        .last(" limit 1 ")
        );
        System.out.println(JSON.toJSONString(one));
        return JSON.toJSONString(one);
    }
}
