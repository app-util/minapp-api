package work.toolset.code.jvm.web.minapp.exception;

import work.toolset.code.jvm.web.minapp.Global;
import work.toolset.code.jvm.web.minapp.model.ErrorResp;
import okhttp3.ResponseBody;
import retrofit2.Response;

import java.nio.charset.StandardCharsets;


public class HttpException extends Exception {

    private final int code;
    private final String errorMsg;

    public static HttpException valueOf(Response resp) {
        String errorMsg = "";
        ResponseBody errorBody = resp.errorBody();
        if (errorBody != null) {
            try {
                errorMsg = new String(errorBody.bytes(), StandardCharsets.UTF_8);
                ErrorResp json = Global.gson().fromJson(errorMsg, ErrorResp.class);
                errorMsg = json.getErrorMsg();
            } catch (Exception e) {}
        }
        return new HttpException(resp.code(), errorMsg);
    }

    public HttpException(int code) {
        this(code, null, null);
    }

    public HttpException(int code, String errorMsg) {
        this(code, errorMsg, null);
    }

    public HttpException(int code, String errorMsg, Throwable cause) {
        super(String.format("status code %s\n%s", code, errorMsg), cause);
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public int getCode() {
        return code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
