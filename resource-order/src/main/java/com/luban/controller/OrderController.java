package com.luban.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequestMapping("/order")
public class OrderController {

    @RequestMapping("/find/{orderNo}")
    public Object selectOrderInfoById(@PathVariable("orderNo") String orderNo) {
        log.info("orderNo:{}", orderNo);
        //OrderInfo orderInfo = orderInfoMapper.selectOrderInfoByIdAndUserName(orderNo,userName);
        /*if(null == orderInfo) {
            return "根据orderNo:"+orderNo+"查询没有该订单";
        }*/
        Map map = new HashMap<String, String>();
        map.put("user_name", "bole");
        map.put("order_id", orderNo);
        map.put("amount", "100");
        return map;
    }
}
