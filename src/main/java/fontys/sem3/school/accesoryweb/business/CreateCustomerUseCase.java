package fontys.sem3.school.accesoryweb.business;

import fontys.sem3.school.accesoryweb.domain.CreateCustomerRequest;
import fontys.sem3.school.accesoryweb.domain.CreateCustomerResponse;

public interface CreateCustomerUseCase {
    CreateCustomerResponse createCustomer(CreateCustomerRequest request);
}
