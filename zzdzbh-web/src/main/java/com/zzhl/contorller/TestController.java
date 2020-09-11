package com.zzhl.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 【描述：】
 *
 * @author: tanggw
 * @Date: 2020/09/11/ 14:38
 */
@RestController
public class TestController {

    private final RedisTemplate<String,String> redisTemplate;

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
        String a_key = redisTemplate.opsForValue().get("a_key");
        return a_key;
    }

}
