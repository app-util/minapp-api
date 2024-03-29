package work.toolset.code.jvm.web.minapp;

public abstract class Const
{
    
    public static final String TAG = "minapp-android-sdk";
    public static final String HTTP_HOST = "https://cloud.minapp.com/";
    public static final long HTTP_TIMEOUT = 10 * 1000;                              // http 读、写、连接的超时设置，单位毫秒
    public static final String HTTP_HEADER_AUTH_PREFIX = "Hydrogen-r1 ";
    public static final String HTTP_HEADER_AUTH = "authorization";
    public static final String HTTP_HEADER_PLATFORM = "X-Hydrogen-Client-Platform";
    public static final String HTTP_HEADER_VERSION = "X-Hydrogen-Client-Version";
    
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String MEDIA_TYPE_JSON = "application/json";
    public static final String MEDIA_TYPE_FORM = "multipart/form-data";
    
    public static final String HTTP_HEADER_CLIENT_ID = "X-Hydrogen-Client-ID";
    public static final String SDK_PLATFORM = "NATIVE_ANDROID";
    
    public static final String TABLE_USER_PROFILE = "_userprofile";
    
    public static final String COMMA = ",";
    public static final String SP_NAME = "hydrogen_android_sdk";
    
    public static final String VERSION_NAME = "0.1.8";
    
}
