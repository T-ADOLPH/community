package life.majiang.community.mapper;

import life.majiang.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Author: T.ADOLPH
 * Date: 2020/8/23 21:00
 * Version: 1.0
 * Describe:
 */
@Mapper
public interface QuestionMapper {

    @Insert("INSERT INTO QUESTION (TITLE, DESCRIPTION, GMT_CREATE, GMT_MODIFIED, CREATOR, TAG) VALUES (#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tag})")
    void create(Question question);

    @Select("SELECT * FROM QUESTION LIMIT #{offset}, #{size}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("SELECT COUNT(1) FROM QUESTION")
    Integer count();

    @Select("SELECT * FROM QUESTION WHERE CREATOR = #{userId} LIMIT #{offset}, #{size}")
    List<Question> listByUserId(@Param(value = "userId") Integer userId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("SELECT COUNT(1) FROM QUESTION WHERE CREATOR = #{userId}")
    Integer countByUserId(@Param(value = "userId") Integer userId);

    @Select("SELECT * FROM QUESTION WHERE ID = #{id}")
    Question getById(@Param(value = "id") Integer id);

    @Update("UPDATE QUESTION SET TITLE=#{title}, DESCRIPTION=#{description}, TAG=#{tag}, GMT_MODIFIED=#{gmtModified} WHERE ID=#{id}")
    void update(Question question);
}
