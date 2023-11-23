package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.business.GetOrderByCustomerUseCase;
import fontys.sem3.school.accesoryweb.business.exception.UnauthorizedDataAccessException;

import fontys.sem3.school.accesoryweb.domain.AccessToken;

import fontys.sem3.school.accesoryweb.persistence.OrderRepository;
import fontys.sem3.school.accesoryweb.persistence.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class GetOrderByCustomerImpl implements GetOrderByCustomerUseCase {
    private OrderRepository orderRepository;
    private AccessToken requestAccessToken;

    @Override
    public List<OrderDTO> listOrder(Long customerId) {
        if (!requestAccessToken.hasRole(RoleEnum.ADMIN.name())) {
            if (!requestAccessToken.getUserId().equals(customerId)) {
                throw new UnauthorizedDataAccessException("CUSTOMER_ID_NOT_FROM_LOGGED_IN_USER");
            }
        }
        return orderRepository.getOrders(customerId);
    }


    @Override
    public List<OrderListDTO> getListOrder(Long customerId) {
        if (!requestAccessToken.hasRole(RoleEnum.ADMIN.name())) {
            if (!requestAccessToken.getUserId().equals(customerId)) {
                throw new UnauthorizedDataAccessException("CUSTOMER_ID_NOT_FROM_LOGGED_IN_USER");
            }
        }
        return orderRepository.getOrderList(customerId);
    }


}
