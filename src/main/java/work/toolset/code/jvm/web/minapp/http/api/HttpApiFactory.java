package work.toolset.code.jvm.web.minapp.http.api;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import work.toolset.code.jvm.web.minapp.auth.AuthInterceptor;
import work.toolset.code.jvm.web.minapp.auth.CheckedCallAdapterFactory;
import work.toolset.code.jvm.web.minapp.util.ContentTypeInterceptor;
import work.toolset.code.jvm.web.minapp.util.MemoryCookieJar;
import work.toolset.code.jvm.web.minapp.util.PrintInterceptor;

import java.util.concurrent.TimeUnit;

public class HttpApiFactory
{
    private static final String HTTP_HOST = "https://cloud.minapp.com/";
    private static final long HTTP_TIMEOUT = 10 * 1000; // http 读、写、连接的超时设置，单位毫秒
    
    public static HttpApi create(Gson gson)
    {
        OkHttpClient client = new OkHttpClient.Builder()
                .followRedirects(true)
                .followSslRedirects(true)
                .connectTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS)
                .cookieJar(new MemoryCookieJar())
                .retryOnConnectionFailure(true)
                .addNetworkInterceptor(new AuthInterceptor())
                .addNetworkInterceptor(new ContentTypeInterceptor())
                .addNetworkInterceptor(new PrintInterceptor())
                .build();
        
        
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(new CheckedCallAdapterFactory())
                .baseUrl(HTTP_HOST)
                .build();
        
        return retrofit.create(HttpApi.class);
    }
    /*
    private static GsonBuilder gsonBuilder()
    {
        return new GsonBuilder()
                .setLenient()
                .registerTypeAdapter(Condition.class, new Condition.Serializer())
                .registerTypeAdapter(ConditionNode.class, new ConditionNode.Serializer())
                .registerTypeAdapter(Calendar.class, new CalendarSerializer())
                .registerTypeAdapter(Calendar.class, new CalendarDeserializer())
                .registerTypeAdapter(GregorianCalendar.class, new GregorianCalendarSerializer())
                .registerTypeAdapter(GregorianCalendar.class, new GregorianCalendarDeserializer())
                .registerTypeAdapterFactory(new RecordTypeAdapterFactory())
                .registerTypeAdapter(GeoPoint.class, new GeoPoint.Serializer())
                .registerTypeAdapter(GeoPoint.class, new GeoPoint.Deserializer())
                .registerTypeAdapter(GeoPolygon.class, new GeoPolygon.Serializer())
                .registerTypeAdapter(GeoPolygon.class, new GeoPolygon.Deserializer())
                .registerTypeAdapter(WithinCircle.class, new WithinCircleSerializer())
                .registerTypeAdapter(WithinRegion.class, new WithinRegionSerializer());
    }
    */
}
