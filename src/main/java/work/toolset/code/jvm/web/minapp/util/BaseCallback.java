package work.toolset.code.jvm.web.minapp.util;


public interface BaseCallback<T> {

    void onSuccess(T t);

    void onFailure(Throwable e);

}
