package com.backend.EEA.common.restcaller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RestApiCallerImpl  {

    public RestApiCallerImpl(
            @Value("${rest.template.req.connection.time.out}") final int requestConnectionTimeout,
            @Value("${rest.template.read.time.out}") final int readTimeout) {
       // super(requestConnectionTimeout, readTimeout);
    }
}
