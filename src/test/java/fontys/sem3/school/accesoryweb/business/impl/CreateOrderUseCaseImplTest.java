package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.business.impl.CreateOrderUseCaseImpl;
import fontys.sem3.school.accesoryweb.domain.CreateOrderDetailRequest;
import fontys.sem3.school.accesoryweb.domain.CreateOrderRequest;
import fontys.sem3.school.accesoryweb.domain.CreateOrderResponse;
import fontys.sem3.school.accesoryweb.persistence.OrderDetailRepository;
import fontys.sem3.school.accesoryweb.persistence.OrderRepository;
import fontys.sem3.school.accesoryweb.persistence.ProductRepository;
import fontys.sem3.school.accesoryweb.persistence.UserRepository;
import fontys.sem3.school.accesoryweb.persistence.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class CreateOrderUseCaseImplTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private OrderDetailRepository orderDetailRepository;
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CreateOrderUseCaseImpl createOrderUseCase;

    @Test
    void testCreateOrder() {

        CreateOrderDetailRequest orderDetailRequest = CreateOrderDetailRequest.builder()
                .amount(2)
                .productId(1L)
                .build();
        ProductEntity product = ProductEntity.builder()
                .id(1L)
                .category(CategoryEntity.builder().id(1L).name("hat").build())
                .quantity(10)
                .price(25.25)
                .name("redHat")
                .build();
        UserEntity user = UserEntity.builder()
                .id(1L)
                .role(RoleEnum.CUSTOMER)
                .firstname("LY")
                .lastname("LY")
                .address("UTRECHT")
                .email("123@GMAIL.COM")
                .build();
        List<CreateOrderDetailRequest> orderDetailRequests = new ArrayList<>();
        orderDetailRequests.add(orderDetailRequest);

        CreateOrderRequest createOrderRequest = CreateOrderRequest.builder()
                .customerId(user.getId())
                .orderDetailRequests(orderDetailRequests).build();

        OrderDetailEntity orderDetail = OrderDetailEntity.builder()
                .amount(2)
                .product(product)
                .build();
        List<OrderDetailEntity> listOrder = new ArrayList<>();
        listOrder.add(orderDetail);
        OrderEntity savedOrder = OrderEntity.builder()
                .id(1L)
                .orderDate(LocalDateTime.MAX)
                .customer(user)
                .orderDetails(listOrder).build();


        OrderDetailEntity savedOrderDetail = OrderDetailEntity.builder()
                .id(1L)
                .amount(2)
                .product(product)
                .build();

        // Mock repository methods
        when(userRepository.findById(createOrderRequest.getCustomerId())).thenReturn(Optional.of(user));
        when(productRepository.findById(orderDetail.getProduct().getId())).thenReturn(Optional.of(product));
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(savedOrder);
        when(orderDetailRepository.save(any(OrderDetailEntity.class))).thenReturn(savedOrderDetail);

        // Invoke the method
        CreateOrderResponse response = createOrderUseCase.createOrder(createOrderRequest);

        // Verify repository method invocations
         verify(userRepository).findById(user.getId());
        verify(productRepository,times(2)).findById(1L);
        verify(orderRepository, times(1)).save(any(OrderEntity.class));
        verify(orderDetailRepository, times(1)).save(any(OrderDetailEntity.class));

        // Verify the response
        assertEquals(savedOrder.getId(), response.getOrderId());
    }
}
