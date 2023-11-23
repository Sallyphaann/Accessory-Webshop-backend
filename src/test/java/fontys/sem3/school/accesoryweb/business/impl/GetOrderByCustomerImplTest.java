package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.business.exception.InvalidCustomerException;
import fontys.sem3.school.accesoryweb.business.exception.UnauthorizedDataAccessException;
import fontys.sem3.school.accesoryweb.domain.AccessToken;
import fontys.sem3.school.accesoryweb.domain.LoginRequest;
import fontys.sem3.school.accesoryweb.persistence.OrderRepository;
import fontys.sem3.school.accesoryweb.persistence.UserRepository;
import fontys.sem3.school.accesoryweb.persistence.entity.OrderDTO;
import fontys.sem3.school.accesoryweb.persistence.entity.OrderListDTO;
import fontys.sem3.school.accesoryweb.persistence.entity.RoleEnum;
import fontys.sem3.school.accesoryweb.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetOrderByCustomerImplTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AccessToken requestAccessToken;
    @InjectMocks
    private GetOrderByCustomerImpl getOrderByCustomer;
    @Test
     void testListOrder_ValidCustomer_ReturnsOrderDTOList() {
        Long customerId = 123L;

        UserEntity userEntity = UserEntity.builder()
                .id(customerId)
                .firstname("Sally")
                .lastname("Phan")
                .email("sally@gmail.com")
                .address("Utrecht")
                .phoneNumber("06178962523")
                .role(RoleEnum.CUSTOMER)
                .build();

        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(false);
        when(requestAccessToken.getUserId()).thenReturn(customerId);

        when(orderRepository.getOrders(customerId)).thenReturn(Arrays.asList(new OrderDTO(), new OrderDTO()));

        // Act
        List<OrderDTO> result = getOrderByCustomer.listOrder(customerId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(requestAccessToken).hasRole(RoleEnum.ADMIN.name());
        verify(requestAccessToken).getUserId();
        verify(orderRepository).getOrders(customerId);
    }

    @Test
     void testListOrder_UnauthorizedCustomer_ThrowsUnauthorizedDataAccessException() {
        // Arrange
        Long customerId = 456L;
        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(false);
        when(requestAccessToken.getUserId()).thenReturn(789L);
        String message = "CUSTOMER_ID_NOT_FROM_LOGGED_IN_USER";
        UnauthorizedDataAccessException actualException = assertThrows(UnauthorizedDataAccessException.class,()->{
            getOrderByCustomer.listOrder(customerId);
        });
        assertEquals(message,actualException.getReason());


    }


    @Test
     void testGetListOrder_UnauthorizedCustomer_ThrowsUnauthorizedDataAccessException() {
        // Arrange
        Long customerId = 456L;
        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(false);
        when(requestAccessToken.getUserId()).thenReturn(789L);
        String message = "CUSTOMER_ID_NOT_FROM_LOGGED_IN_USER";
        UnauthorizedDataAccessException actualException = assertThrows(UnauthorizedDataAccessException.class,()->{
            getOrderByCustomer.getListOrder(customerId);
        });
        assertEquals(message,actualException.getReason());
    }


}