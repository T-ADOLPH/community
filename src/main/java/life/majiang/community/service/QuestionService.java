package life.majiang.community.service;

import life.majiang.community.dto.PaginationDTO;
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
    private UserMapper userMapper;

    @Resource
    private QuestionMapper questionMapper;

    /**
     *
     * @param page
     * @param size
     * @return
     */
    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        // 得到问题总数
        Integer totalCount = questionMapper.count();
        // 计算总页数
        Integer totalPage = (int) Math.ceil((double) totalCount / size);
        paginationDTO.setTotalPage(totalPage);
        // page 越界处理
        if(page < 1){
            page = 1;
        }
        if(page > totalPage){
            page = totalPage;
        }
        paginationDTO.setCurrentPage(page);
        // 设置paginationDTO的值
        paginationDTO.setPagination();
        // 限制查询的偏移量
        Integer offset = size * (page-1);
        // 获取该页所有question记录
        List<Question> questions = questionMapper.list(offset, size);
        // 将所有的question记录联合对应user记录合并成一个questionDTOList
        List<QuestionDTO> questionDTOS = new LinkedList<>();
        for (Question question : questions) {
            User user = userMapper.findUserById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setUser(user);
            BeanUtils.copyProperties(question, questionDTO);
            questionDTOS.add(questionDTO);
        }
        // 赋值questionDTOS
        paginationDTO.setQuestionDTOS(questionDTOS);
        return paginationDTO;
    }

    public PaginationDTO listByUserId(Integer userId, Integer page, Integer size){
        PaginationDTO paginationDTO = new PaginationDTO();
        // 得到问题总数
        Integer totalCount = questionMapper.countByUserId(userId);
        // 计算总页数
        Integer totalPage = (int) Math.ceil((double) totalCount / size);
        paginationDTO.setTotalPage(totalPage);
        // page 越界处理
        if(page < 1){
            page = 1;
        }
        if(page > totalPage){
            page = totalPage;
        }
        paginationDTO.setCurrentPage(page);
        // 设置paginationDTO的值
        paginationDTO.setPagination();
        // 限制查询的偏移量
        Integer offset = size * (page-1);
        // 获取该页所有question记录
        List<Question> questions = questionMapper.listByUserId(userId, offset, size);
        // 将所有的question记录联合对应user记录合并成一个questionDTOList
        List<QuestionDTO> questionDTOS = new LinkedList<>();
        for (Question question : questions) {
            User user = userMapper.findUserById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setUser(user);
            BeanUtils.copyProperties(question, questionDTO);
            questionDTOS.add(questionDTO);
        }
        // 赋值questionDTOS
        paginationDTO.setQuestionDTOS(questionDTOS);
        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.getById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.findUserById(question.getCreator());
        questionDTO.setUser(user);

        return questionDTO;
    }
}
