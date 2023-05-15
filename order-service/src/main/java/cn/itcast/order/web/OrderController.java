package cn.itcast.order.web;

import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;
import cn.itcast.order.service.OrderService;
import cn.itcast.order.service.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("order")
public class OrderController {

   @Autowired
   private OrderService orderService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserClient userClient;
    @GetMapping("{orderId}")
    public Order queryOrderByUserId(@PathVariable("orderId") Long orderId) {
        Order order = orderService.queryOrderById(orderId);
        // 根据id查询订单并返回
        String url = "http://userservice/user/"+order.getUserId();
        User forObject = userClient.findById(orderId);
        order.setUser(forObject);
        return order;
    }
}
