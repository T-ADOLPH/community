package life.majiang.community.dto;

import life.majiang.community.model.User;
import lombok.Data;

/**
 * @author: T.ADOLPH
 * @date: 2020/8/24 2:02
 * @version: Describe:
 */
@Data
public class QuestionDTO {
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
    private User user;
}
