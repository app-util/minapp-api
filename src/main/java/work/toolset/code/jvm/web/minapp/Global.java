package work.toolset.code.jvm.web.minapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import work.toolset.code.jvm.web.minapp.database.GeoPoint;
import work.toolset.code.jvm.web.minapp.database.GeoPolygon;
import work.toolset.code.jvm.web.minapp.database.query.Condition;
import work.toolset.code.jvm.web.minapp.database.query.ConditionNode;
import work.toolset.code.jvm.web.minapp.database.query.WithinCircle;
import work.toolset.code.jvm.web.minapp.database.query.WithinRegion;
import work.toolset.code.jvm.web.minapp.http.api.HttpApi;
import work.toolset.code.jvm.web.minapp.http.api.HttpApiFactory;
import work.toolset.code.jvm.web.minapp.type.adapter.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public abstract class Global
{
    
    private static HttpApi HTTP_API;
    private static Gson GSON;
    private static Gson GSON_ForPrint;
    private static ExecutorService EXECUTOR_SERVICE;
    
    
    public static void postOnMain(Runnable runnable)
    {
        //todo:refactor it with multithreading
        //new android.os.Handler(android.os.Looper.getMainLooper()).post(runnable);
    }
    
    public static HttpApi httpApi()
    {
        if (HTTP_API == null)
        {
            synchronized (Global.class)
            {
                if (HTTP_API == null)
                {
                    HTTP_API = HttpApiFactory.create(gson());
                }
            }
        }
        return HTTP_API;
    }
    
    public static Gson gson()
    {
        if (GSON == null)
        {
            synchronized (Global.class)
            {
                if (GSON == null)
                {
                    GSON = gsonBuilder().create();
                }
            }
        }
        return GSON;
    }
    
    public static Gson gsonPrint()
    {
        if (GSON_ForPrint == null)
        {
            synchronized (Global.class)
            {
                if (GSON_ForPrint == null)
                {
                    GSON_ForPrint = gsonBuilder()
                            .setPrettyPrinting()
                            .create();
                }
            }
        }
        return GSON_ForPrint;
    }
    
    public static Future<?> submit(Runnable task)
    {
        return executorService().submit(task);
    }
    
    static ExecutorService executorService()
    {
        if (EXECUTOR_SERVICE == null)
        {
            synchronized (Global.class)
            {
                if (EXECUTOR_SERVICE == null)
                {
                    EXECUTOR_SERVICE = Executors.newFixedThreadPool(5);
                }
            }
        }
        return EXECUTOR_SERVICE;
    }
    
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
    
}
