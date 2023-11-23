package fontys.sem3.school.accesoryweb.business;
import fontys.sem3.school.accesoryweb.persistence.entity.OrderDTO;
import fontys.sem3.school.accesoryweb.persistence.entity.OrderListDTO;

import java.util.List;

public interface GetOrderByCustomerUseCase {
    List<OrderDTO> listOrder(Long customerId);
    List<OrderListDTO> getListOrder(Long customerId);

}
