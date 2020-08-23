package life.majiang.community.service;

import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: T.ADOLPH
 * @date: 2020/8/24 2:10
 * @version: Describe:
 */
@Service
public class QuestionService {

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private UserMapper userMapper;

    public List<QuestionDTO> list() {
        List<Question> questionList = questionMapper.list();
        List<QuestionDTO> questionDTOList = new LinkedList<>();
        for (Question question : questionList) {
            User user = userMapper.findUserById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            /*question 是直接从Question表中拿到的数据，
            其字段名为下划线连接gmt_create，不能转换为驼峰questionDTO中的gmtCreate
            解决办法：在application.properties 中设置MyBatis驼峰写法自动转换
            mybatis.configuration.map-underscore-to-camel-case=true
             */
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
