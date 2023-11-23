package fontys.sem3.school.accesoryweb.persistence;

import fontys.sem3.school.accesoryweb.domain.OrderDetail;
import fontys.sem3.school.accesoryweb.persistence.entity.OrderDTO;
import fontys.sem3.school.accesoryweb.persistence.entity.OrderDetailEntity;

import fontys.sem3.school.accesoryweb.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity,Long> {

}
