package fontys.sem3.school.accesoryweb.persistence;

import fontys.sem3.school.accesoryweb.persistence.entity.OrderDTO;
import fontys.sem3.school.accesoryweb.persistence.entity.OrderEntity;
import fontys.sem3.school.accesoryweb.persistence.entity.OrderListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {

    @Query("SELECT DISTINCT new fontys.sem3.school.accesoryweb.persistence.entity.OrderDTO(o.id,o.orderDate,od.amount,p) " +
            "FROM OrderEntity o " +
            "INNER JOIN o.orderDetails od " +
            "INNER JOIN od.product p " +
            "WHERE o.customer.id = :customerId " +
            "ORDER BY o.id ASC")
    List<OrderDTO> getOrders(@Param("customerId") Long customerId);
    @Query("select DISTINCT new fontys.sem3.school.accesoryweb.persistence.entity.OrderListDTO( o.id , o.orderDate )from OrderEntity o WHERE o.customer.id = :customerId" )
    List<OrderListDTO> getOrderList(@Param("customerId") Long customerId);



}
