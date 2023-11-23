package fontys.sem3.school.accesoryweb.business;

import fontys.sem3.school.accesoryweb.domain.AccessToken;

public interface AccessTokenDecoder {
    AccessToken decode(String accessTokenEncoded);
}