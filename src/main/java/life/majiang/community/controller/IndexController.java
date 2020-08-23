package life.majiang.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: T.ADOLPH
 * @date: 2020/8/21 16:12
 * @version: 1.0
 * Describe: 当访问(/) 根目录时，返回index.html 页面
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {return "index";}
}
