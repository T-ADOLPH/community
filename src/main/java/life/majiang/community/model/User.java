package life.majiang.community.model;

import lombok.Data;

/**
 * @author: T.ADOLPH
 * @date: 2020/8/22 20:52
 * Describe: 封装数据库中User表的信息
 */
@Data
public class User {
    private Integer id;
    private String accountId;
    private String token;
    private String name;
    private Long gmtCreat;
    private Long gmtModified;
    private String avatarUrl;
}
