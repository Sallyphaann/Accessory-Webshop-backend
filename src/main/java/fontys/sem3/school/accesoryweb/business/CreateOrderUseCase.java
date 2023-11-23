package fontys.sem3.school.accesoryweb.business;

import fontys.sem3.school.accesoryweb.domain.CreateOrderRequest;
import fontys.sem3.school.accesoryweb.domain.CreateOrderResponse;

public interface CreateOrderUseCase {
    CreateOrderResponse createOrder (CreateOrderRequest request);
}
