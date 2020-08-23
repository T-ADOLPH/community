package life.majiang.community.dto;

import lombok.Data;

/**
 * @author: T.ADOLPH
 * @date: 2020/8/22 14:42
 * Describe: 保存从github获得的用户信息
 */
@Data
public class GithubUser {
    private Long id;
    private String name;
    private String bio;
    private String avatar_url;
}
