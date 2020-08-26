package life.majiang.community.controller;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * Author: T.ADOLPH
 * Date: 2020/8/21 16:12
 * Version: 1.0
 * Describe: 当访问(/) 根目录时，返回index.html 页面
 */
@Controller
public class IndexController {

    @Resource
    private QuestionService questionService;

    @GetMapping("/")
    public String index(
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "3") Integer size) {
        // 根据当前页与页数得到页面信息paginationDTO
        PaginationDTO paginationDTO = questionService.list(page, size);
        // 查询该页所有question记录和页数信息，并通过model传递给index
        model.addAttribute("pagination", paginationDTO);
        return "index";
    }
}
