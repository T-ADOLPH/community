package life.majiang.community.dto;

/**
 * @author T.ADOLPH
 * @date: 2020/8/22 13:13
 * Describe: 封装github需要的认证信息（id secret code state url）
 */
public class AccessTokenDTO {

    private String client_id;
    private String client_secret;
    private String code;
    private String state;
    private String redirect_url;

    @Override
    public String toString() {
        return "AccessTokenDTO{" +
                "client_id='" + client_id + '\'' +
                ", client_secret='" + client_secret + '\'' +
                ", code='" + code + '\'' +
                ", state='" + state + '\'' +
                ", redirect_url='" + redirect_url + '\'' +
                '}';
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRedirect_url() {
        return redirect_url;
    }

    public void setRedirect_url(String redirect_url) {
        this.redirect_url = redirect_url;
    }
}
