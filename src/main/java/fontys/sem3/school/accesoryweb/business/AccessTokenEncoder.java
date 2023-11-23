package fontys.sem3.school.accesoryweb.business;

import fontys.sem3.school.accesoryweb.domain.AccessToken;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}