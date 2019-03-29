package com.example.miaosha.service.impl;

import com.example.miaosha.service.SeckillInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuwencong on 2019/3/28
 */
public class SecKillImpl implements SeckillInterface {

    public static Map<Long,Long> inventory;

    static {
        inventory = new HashMap<>();
        inventory.put(10000001L, 10000l);
        inventory.put(10000002L, 10000l);
    }

    @Override
    public void secKill(String arg1, Long arg2) {
        reduceInventory(arg2); //秒杀调用
    }

    public Long reduceInventory(Long commodityId){   //模拟秒杀操作，姑且认为一个秒杀就是将库存减一，实际情景要复杂的多
        inventory.put(commodityId,inventory.get(commodityId) - 1);
        return inventory.get(commodityId);
    }

}
