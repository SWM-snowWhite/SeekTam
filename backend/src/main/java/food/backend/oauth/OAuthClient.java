package food.backend.oauth;



public interface OAuthClient {

    TokenInfo requestTokenInfo(LoginParams loginParams);
    OAuthType getOAuthType();
}
