package work.toolset.code.jvm.web.minapp.util;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;

import java.io.IOException;

public class PrintInterceptor implements Interceptor
{
    
    private static final String TAG = "PrintInterceptor";
    
    @Override
    public Response intercept(Chain chain) throws IOException
    {
        Response resp = chain.proceed(chain.request());
        printResponse(resp);
        return resp;
    }
    
    private void printResponse(Response resp)
    {
        Request req = resp.request();
        StringBuilder sb = new StringBuilder(" \n");
        
        sb.append(req.method()).append(" ").append(req.url());
        if (req.headers().size() > 0)
        {
            sb.append("\nheaders\n").append(req.headers());
        }
        if (req.body() != null)
        {
            Buffer buffer = new Buffer();
            try
            {
                req.body().writeTo(buffer);
                String body = buffer.readByteString().utf8();
                if (!body.equals(""))
                {
                    sb.append("\nbody\n").append(body).append("\n");
                }
            }
            catch (java.io.IOException e)
            {
            }
            finally
            {
                buffer.clear();
            }
        }
        
        sb.append("\n").append(resp.code());
        if (resp.headers().size() > 0)
        {
            sb.append("\nheaders\n").append(resp.headers());
        }
        if (resp.body() != null)
        {
            try
            {
                String str = Util.readString(resp.body().byteStream());
                if (!str.equals(""))
                {
                    sb.append("\nbody\n").append(str).append("\n");
                }
            }
            catch (Exception e)
            {
            }
        }
    }
}
