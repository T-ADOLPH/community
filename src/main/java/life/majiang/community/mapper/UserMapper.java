package life.majiang.community.mapper;

import life.majiang.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author: T.ADOLPH
 * @date: 2020/8/22 20:49
 * Describe: 把从github获取的用户信息写到DB
 */
@Mapper
public interface UserMapper {
    /**
     * 把github用户信息 写入DB, 持久化操作
     * @param user github User class
     */
    @Insert("INSERT INTO USER (NAME, ACCOUNT_ID, TOKEN, GMT_CREAT, GMT_MODIFIED) VALUES (#{name}, #{accountId}, #{token}, #{gmtCreat}, #{gmtModified})")
    void insert(User user);

    /**
     *
     * @param token
     * @return
     */
    @Select("SELECT * FROM USER WHERE TOKEN = #{token}")
    User findUserByToken(@Param("token") String token);
}
