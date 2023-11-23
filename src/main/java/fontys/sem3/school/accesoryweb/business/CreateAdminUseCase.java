package fontys.sem3.school.accesoryweb.business;

import fontys.sem3.school.accesoryweb.domain.CreateAdminRequest;
import fontys.sem3.school.accesoryweb.domain.CreateAdminResponse;


public interface CreateAdminUseCase {
    CreateAdminResponse createAdmin(CreateAdminRequest request);
}
