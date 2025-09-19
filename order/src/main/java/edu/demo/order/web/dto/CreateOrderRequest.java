package edu.demo.order.web.dto;

import java.util.List;

public record CreateOrderRequest(String token, List<OrderItemRequest> items) {}
