package life.majiang.community.controller;

import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import life.majiang.community.service.QuestionService;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: T.ADOLPH
 * @date: 2020/8/21 16:12
 * @version: 1.0
 * Describe: 当访问(/) 根目录时，返回index.html 页面
 */
@Controller
public class IndexController {
    @Resource
    private UserMapper userMapper;
    
    @Resource
    private QuestionService questionService;
    

    /**
     *
     * @param request
     * @return
     */
    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    User user = userMapper.findUserByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        List<QuestionDTO> questionDTOList = questionService.list();
        model.addAttribute("question", questionDTOList);
        return "index";
    }
}
