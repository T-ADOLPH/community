package life.majiang.community.mapper;

import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author: T.ADOLPH
 * @date: 2020/8/23 21:00
 * @version: Describe:
 */
@Mapper
public interface QuestionMapper {

    /**
     * @param question
     */
    @Insert("INSERT INTO QUESTION (TITLE, DESCRIPTION, GMT_CREATE, GMT_MODIFIED, CREATOR, TAG) VALUES (#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tag})")
    public void create(Question question);

    /**
     *
     * @return
     */
    @Select("SELECT * FROM QUESTION")
    List<Question> list();
}