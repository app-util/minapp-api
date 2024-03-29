package work.toolset.code.jvm.web.minapp.auth;

import work.toolset.code.jvm.web.minapp.exception.EmptyResponseException;
import work.toolset.code.jvm.web.minapp.exception.HttpException;
import work.toolset.code.jvm.web.minapp.exception.SessionMissingException;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;


/**
 * 受检的 {@link Call} <br />
 * if {@link Response#isSuccessful()} == false, throw {@link HttpException} <br />
 * if T != {@link Void} && {@link Response#body()} == null, throw {@link EmptyResponseException} <br />
 * @param <T>
 */
public interface CheckedCall<T> {

    Response<T> execute() throws IOException, HttpException, EmptyResponseException, SessionMissingException;

    void enqueue(Callback<T> callback);
    boolean isExecuted();
    void cancel();
    boolean isCanceled();
    Call<T> clone();
    Request request();
}
