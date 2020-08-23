package life.majiang.community.model;

import lombok.Data;

/**
 * @author: T.ADOLPH
 * @date: 2020/8/23 20:52
 * @version: Describe:
 */
@Data
public class Question {
    private Integer id;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
}