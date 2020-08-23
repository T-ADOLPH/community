package life.majiang.community.controller;

import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: T.ADOLPH
 * @date: 2020/8/23 19:38
 * @version: Describe:
 */
@Controller
public class PublishController {

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    /**
     * 接受提交信息的请求，并把提交的信息用写入question数据库
     *
     * @param title
     * @param description
     * @param tag
     * @param request
     * @param model 保存请求中的信息
     * @return 提交成功返回到index页，不成功继续在publish页
     */
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            HttpServletRequest request,
            Model model) {

        // 把值存入model
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        // 检查输入是否为空
        if (title == null || title == "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error", "description不能为空");
            return "publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "tag不能为空");
            return "publish";
        }

        User user = null;
// 从Session中直接获取user ？？
//        user = (User)request.getSession().getAttribute("user");

        // 获取user对象
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.findUserByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        if (user == null) {
            // 若获取不到user信息，发布失败，回到发布页
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        // 新建一个Question对象，并填值
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        // 发布成功，回到首页
        questionMapper.create(question);
        return "redirect:/";
    }
}