package com.qun.order;

import com.qun.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * @author qun
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {


    @PostMapping()
    public OrderInfo CreateOrder(@RequestBody OrderInfo info, @AuthenticationPrincipal User user){
        log.info("=========="+user);
        return info;
    }

    @GetMapping("/{id}")
    public OrderInfo getOrder(@PathVariable Long id){
        log.info("=========="+id);
        return new OrderInfo();
    }
}
