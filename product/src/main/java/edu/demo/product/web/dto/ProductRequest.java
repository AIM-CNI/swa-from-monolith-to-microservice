package edu.demo.product.web.dto;

public record ProductRequest(Long id, String name, String description, double price, int stock) {}
