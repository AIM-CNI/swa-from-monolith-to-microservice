package edu.demo.order.web;

import edu.demo.order.domain.Order;
import edu.demo.order.repo.OrderRepository;
import edu.demo.order.service.OrderService;
import edu.demo.order.web.dto.CreateOrderRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderRepository orders;

    public OrderController(OrderService orderService, OrderRepository orders) {
        this.orderService = orderService;
        this.orders = orders;
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody CreateOrderRequest req) {
        var productIds = req.items().stream().map(i -> i.productId()).collect(Collectors.toList());
        var quantities = req.items().stream().map(i -> i.quantity()).collect(Collectors.toList());
        var order = orderService.createOrder(req.token(), productIds, quantities);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> get(@PathVariable Long id) {
        return orders.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
