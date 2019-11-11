package de.fklappan.app.webnotes.service;

import java.io.IOException;

import de.fklappan.app.webnotes.BuildConfig;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        request = request.newBuilder()
                .addHeader("user", BuildConfig.USER)
                .addHeader("password", BuildConfig.PASSWORD)
                .build();
        return chain.proceed(request);
    }
}
