package com.qun.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qun
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {


    @PostMapping()
    public OrderInfo CreateOrder(@RequestBody OrderInfo info, @AuthenticationPrincipal String username){
        log.info("=========="+username);
        return info;
    }
}
