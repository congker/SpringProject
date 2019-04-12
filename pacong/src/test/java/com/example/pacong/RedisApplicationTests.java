package com.example.pacong;

import com.example.pacong.Bean.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by xuwencong on 2019/4/11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisApplicationTests {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<String, User> redisTemplate;


    @Test
    public void test() {
        stringRedisTemplate.opsForValue().set("redis3", "this is redis3 value");
//        Assert.assertEquals("this is redis value", stringRedisTemplate.opsForValue().get("redis1"));
    }

    @Test
    public void test2() {
        // 保存对象
        User user = new User("超人", 20);
        redisTemplate.opsForValue().set(user.getUsername(), user);
        user = new User("蝙蝠侠", 30);
        redisTemplate.opsForValue().set(user.getUsername(), user);

        user = new User("蜘蛛侠", 40);
        redisTemplate.opsForValue().set(user.getUsername(), user);

        Assert.assertEquals(20, redisTemplate.opsForValue().get("超人").getAge().longValue());
        Assert.assertEquals(30, redisTemplate.opsForValue().get("蝙蝠侠").getAge().longValue());
        Assert.assertEquals(40, redisTemplate.opsForValue().get("蜘蛛侠").getAge().longValue());

    }


}
