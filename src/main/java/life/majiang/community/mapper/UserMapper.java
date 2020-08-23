package life.majiang.community.mapper;

import life.majiang.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: T.ADOLPH
 * @date: 2020/8/22 20:49
 * Describe: 把从github获取的用户信息写到DB
 */
@Mapper
public interface UserMapper {
    @Insert("INSERT INTO USER (NAME, ACCOUNT_ID, TOKEN, GMT_CREAT, GMT_MODIFIED) VALUES (#{name}, #{accountId}, #{token}, #{gmtCreat}, #{gmtModified})")
    void insert(User user);
}
