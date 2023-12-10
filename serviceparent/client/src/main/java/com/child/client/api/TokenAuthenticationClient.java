package com.child.client.api;

import com.child.model.response.HealthCheckResponse;
import com.child.model.response.Tokens;

public interface TokenAuthenticationClient {

    public Tokens getToken(String username , String password);
}
