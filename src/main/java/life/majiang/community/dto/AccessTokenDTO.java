package life.majiang.community.dto;

import lombok.Data;

/**
 * @author T.ADOLPH
 * @date: 2020/8/22 13:13
 * Describe: 封装github需要的认证信息（id secret code state url）
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String state;
    private String redirect_uri;
}
