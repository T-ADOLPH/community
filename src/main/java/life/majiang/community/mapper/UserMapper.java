package life.majiang.community.mapper;

import life.majiang.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Author: T.ADOLPH
 * Date: 2020/8/22 20:49
 * Describe:
 */
//@Repository     // 解决UserMapper实例红线，不知原因
@Mapper
public interface UserMapper {
    @Insert("INSERT INTO USER (NAME, ACCOUNT_ID, TOKEN, GMT_CREAT, GMT_MODIFIED) VALUES (#{name}, #{accountId}, #{token}, #{gmtCreat}, #{gmtModified})")
    void insert(User user);
}
