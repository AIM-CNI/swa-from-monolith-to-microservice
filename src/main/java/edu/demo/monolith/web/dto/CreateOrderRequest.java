package edu.demo.monolith.web.dto;

import java.util.List;

public record CreateOrderRequest(Long userId, List<OrderItemRequest> items) {}
