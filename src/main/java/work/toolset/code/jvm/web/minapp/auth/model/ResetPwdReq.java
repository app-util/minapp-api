package work.toolset.code.jvm.web.minapp.auth.model;

import com.google.gson.annotations.SerializedName;

public class ResetPwdReq {

    /**
     * email : aaa@bbb
     */

    @SerializedName("email")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
