package fontys.sem3.school.accesoryweb.business;

import fontys.sem3.school.accesoryweb.domain.LoginRequest;
import fontys.sem3.school.accesoryweb.domain.LoginResponse;

public interface LoginUseCase {
    LoginResponse login(LoginRequest loginRequest);
}
