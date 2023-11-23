package fontys.sem3.school.accesoryweb.controller;

import fontys.sem3.school.accesoryweb.business.CreateOrderUseCase;

import fontys.sem3.school.accesoryweb.business.GetOrderByCustomerUseCase;
import fontys.sem3.school.accesoryweb.configuration.security.isauthenticated.IsAuthenticated;
import fontys.sem3.school.accesoryweb.domain.*;
import fontys.sem3.school.accesoryweb.persistence.OrderRepository;
import fontys.sem3.school.accesoryweb.persistence.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class OrderController {
    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderByCustomerUseCase getOrderByCustomerUseCase;
    private final OrderRepository orderRepository;


    @PostMapping()
    @IsAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_ADMIN"})
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody @Valid CreateOrderRequest request) {
        CreateOrderResponse response = createOrderUseCase.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("{customerId}")
    public ResponseEntity<List<OrderDTO>> getOrders(@PathVariable Long customerId){
        List<OrderDTO> orders = getOrderByCustomerUseCase.listOrder(customerId);
        if (orders.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(orders);
        }
    }
    @GetMapping("customer/{customerId}")
    public ResponseEntity<List<OrderListDTO>> getOrdersCustomer(@PathVariable Long customerId){
        List<OrderListDTO> orders = getOrderByCustomerUseCase.getListOrder(customerId);
        if (orders.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(orders);
        }
    }



}
