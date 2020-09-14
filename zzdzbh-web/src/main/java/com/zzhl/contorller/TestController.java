package com.zzhl.contorller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

/**
 * 【描述：】
 *
 * @author: tanggw
 * @Date: 2020/09/11/ 14:38
 */
@RestController
public class TestController {

    private final RedisTemplate<String,Object> redisTemplate;

    public TestController(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/index")
    public String index(){

        System.out.println("1111111");
        return "success";
    }

    @GetMapping("/redis")
    public String redis(){
        redisTemplate.opsForValue().set("a_key","a_value");
        String a_key = (String)redisTemplate.opsForValue().get("a_key");
        return a_key;
    }

    @GetMapping("/redisObj")
    public String redisObj(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("d", 1);
        map.put("e", new BigDecimal(100));
        map.put("f", LocalDateTime.now());
        map.put("g", new Date());
        map.forEach((key,value) -> {
            redisTemplate.opsForHash().put("a_key_obj_hash",key, value);
        });
//        Object a_key = (Object)redisTemplate.opsForHash().get("a_key_obj");
        return JSON.toJSONString(map);
    }

}
