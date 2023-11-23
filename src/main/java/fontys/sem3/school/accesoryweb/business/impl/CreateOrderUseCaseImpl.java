package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.business.CreateOrderUseCase;
import fontys.sem3.school.accesoryweb.domain.CreateOrderDetailRequest;
import fontys.sem3.school.accesoryweb.domain.CreateOrderRequest;
import fontys.sem3.school.accesoryweb.domain.CreateOrderResponse;
import fontys.sem3.school.accesoryweb.persistence.OrderDetailRepository;
import fontys.sem3.school.accesoryweb.persistence.OrderRepository;

import fontys.sem3.school.accesoryweb.persistence.ProductRepository;
import fontys.sem3.school.accesoryweb.persistence.UserRepository;
import fontys.sem3.school.accesoryweb.persistence.entity.OrderDetailEntity;
import fontys.sem3.school.accesoryweb.persistence.entity.OrderEntity;
import fontys.sem3.school.accesoryweb.persistence.entity.ProductEntity;
import fontys.sem3.school.accesoryweb.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class CreateOrderUseCaseImpl implements CreateOrderUseCase {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;

   @Override
    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        UserEntity user = userRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        OrderEntity order = saveNewOrder(request,user);
        saveOrderDetails(request.getOrderDetailRequests(), order);

        return CreateOrderResponse.builder().orderId(order.getId()).build();
    }



    private OrderEntity saveNewOrder(CreateOrderRequest request, UserEntity user) {
        OrderEntity newOrder = OrderEntity.builder()
                .orderDate(LocalDateTime.now())
                .customer(user)
                .build();

        List<OrderDetailEntity> orderDetailEntities = new ArrayList<>();

        for (CreateOrderDetailRequest orderDetailRequest : request.getOrderDetailRequests()) {
            ProductEntity product = productRepository.findById(orderDetailRequest.getProductId()).orElse(null);

            if (product != null) {
                OrderDetailEntity orderDetailEntity = OrderDetailEntity.builder()
                        .order(newOrder)
                        .product(product)
                        .price(product.getPrice())
                        .amount(orderDetailRequest.getAmount())
                        .build();
                orderDetailEntities.add(orderDetailEntity);
            }
        }

        newOrder.setOrderDetails(orderDetailEntities);

        return orderRepository.save(newOrder);
    }





    private void saveOrderDetails(List<CreateOrderDetailRequest> orderDetails, OrderEntity order) {
        for (CreateOrderDetailRequest detailRequest : orderDetails) {
            ProductEntity product = productRepository.findById(detailRequest.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));

            OrderDetailEntity orderDetail = saveNewOrderDetail(detailRequest, order, product);
            order.getOrderDetails().add(orderDetail);
        }
    }

    private OrderDetailEntity saveNewOrderDetail(CreateOrderDetailRequest request, OrderEntity order, ProductEntity product) {
        OrderDetailEntity newOrderDetail = OrderDetailEntity.builder()
                .product(product)
                .order(order)
                .price(product.getPrice())
                .amount(request.getAmount())
                .build();
        return orderDetailRepository.save(newOrderDetail);
    }
}








