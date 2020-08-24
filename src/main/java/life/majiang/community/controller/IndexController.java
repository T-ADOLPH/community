package life.majiang.community.controller;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import life.majiang.community.service.QuestionService;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
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
     * @param request
     * @param model
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "3") Integer size) {
        // 获得请求中所有cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                // 遍历cookies 找到名为token的cookie
                if (cookie.getName().equals("token")) {
                    // 获取token的值，并查找对应的user
                    String token = cookie.getValue();
                    User user = userMapper.findUserByToken(token);
                    if (user != null) {
                        // 找到user把user信息放入Session中
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }/**/
        }

        // 根据当前页与页数得到页面信息paginationDTO
        PaginationDTO paginationDTO = questionService.list(page, size);
        // 查询该页所有question记录和页数信息，并通过model传递给index
        model.addAttribute("pagination", paginationDTO);
        return "index";
    }
}
