package work.toolset.code.jvm.web.minapp.auth;

import work.toolset.code.jvm.web.minapp.Config;
import work.toolset.code.jvm.web.minapp.Const;
import work.toolset.code.jvm.web.minapp.exception.UninitializedException;
import okhttp3.Interceptor;
import okhttp3.Request;

import java.io.IOException;

/**
 * 在 okhttp request 中加入认证头
 */
public class AuthInterceptor implements Interceptor {
    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        String clientId = Config.getClientId();
        if (clientId == null) {
            throw new IOException(new UninitializedException());
        }
        builder.header(Const.HTTP_HEADER_CLIENT_ID, clientId);

        String token = Auth.token();
        if (token != null) {
            builder.header(Const.HTTP_HEADER_AUTH, Const.HTTP_HEADER_AUTH_PREFIX + token);
        }

        builder.header(Const.HTTP_HEADER_PLATFORM, Const.SDK_PLATFORM);
        builder.header(Const.HTTP_HEADER_VERSION, Const.VERSION_NAME);
        return chain.proceed(builder.build());
    }
}
