package edu.demo.order.service;

import edu.demo.auth.AuthApi;
import edu.demo.order.domain.Order;
import edu.demo.order.domain.OrderItem;
import edu.demo.order.repo.OrderRepository;
import edu.demo.payment.PaymentApi;
import edu.demo.product.ProductApi;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class OrderService {
    private final ProductApi productApi;
    private final OrderRepository orders;
    private final PaymentApi paymentApi;
    private final AuthApi authApi;

    public OrderService(ProductApi productApi, OrderRepository orders, PaymentApi paymentApi, AuthApi authApi) {
        this.productApi = productApi;
        this.orders = orders;
        this.paymentApi = paymentApi;
        this.authApi = authApi;
    }

    @Transactional
    public Order createOrder(String token, java.util.List<Long> productIds, java.util.List<Integer> quantities) {
        if (productIds.size() != quantities.size()) throw new InvalidOrderException("Mismatched items");
        var items = new ArrayList<OrderItem>();
        double total = 0.0;
        for (int i=0; i<productIds.size(); i++) {
            var p = productApi.getProductById(productIds.get(i));
            int q = quantities.get(i);
            if (p.getStock() < q) throw new InsufficientStockException("Insufficient stock for product " + p.getId());
            p.setStock(p.getStock() - q);
            productApi.updateStockFor(p);
            var item = OrderItem.builder()
                    .productId(p.getId())
                    .quantity(q)
                    .priceAtOrder(p.getPrice())
                    .build();
            items.add(item);
            total += p.getPrice() * q;
        }
        var order = Order.builder()
                .userId(authApi.validateUser(token).id())
                .items(items)
                .totalAmount(total)
                .status("CREATED")
                .build();
        orders.save(order);
        boolean paid = paymentApi.pay(total, token);
        order.setStatus(paid ? "PAID" : "FAILED");
        return orders.save(order);
    }
}
