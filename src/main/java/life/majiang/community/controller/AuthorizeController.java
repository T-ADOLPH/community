package life.majiang.community.controller;

import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Author: T.ADOLPH
 * Date: 2020/8/22 11:41
 * Describe:
 */

@Controller
public class AuthorizeController {
    @Resource
    private GithubProvider githubProvider;
    // @Autowired 不推荐使用 建议换成 @Resource

    @Value("${github.client.id}")
    private String clientId;
    // 赋值github账号id

    @Value("${github.client.secret}")
    private String clientSecret;
    // 赋值github账号id对应密钥

    @Value("${github.redirect.url}")
    private String redirectUrl;
    // 赋值callback地址

    @Resource
    private UserMapper userMapper;

    /**
     * 当GitHub访问/callback 时，获取用户信息并写入session
     *
     * @param code
     * @param state
     * @param response
     * @return index.html 刷新首页
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        // 新建AccessToken并根据参数设置对应的值
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id(state);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirectUrl);
        // 向GitHub请求并获取一个token
        String tokenResponse = githubProvider.getAccessToken(accessTokenDTO);
        // 根据token向GitHub请求并获取对应用户信息
        GithubUser githubUser = githubProvider.getUser(tokenResponse);
        if (githubUser != null && githubUser.getId() != null) {
            // 登录成功，获取Session和Cookie

            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            user.setGmtCreat(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreat());
            user.setAvatarUrl(githubUser.getAvatar_url());
            // 登录成功，持久化到DB
            userMapper.insert(user);
            // 把用户信息写入Session中，index会在session中查找到并更改“登录”为用户名
//            request.getSession().setAttribute("user", githubUser);

            // 用token作为绑定前后端的状态，来验证是否登录成功
            // 把token写入cookie，然后与DB中查询，判断是否登录成功
            response.addCookie(new Cookie("token", token));

        } else {    // 登录失败，跳转到Index，重新登录
        }
        return "redirect:/";
    }
}
