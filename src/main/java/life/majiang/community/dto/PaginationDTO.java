package life.majiang.community.dto;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: T.ADOLPH
 * @date: 2020/8/24 7:15
 * @version: Describe:
 */
@Data
public class PaginationDTO {
    private Integer currentPage;
    private Integer totalPage;
    private List<Integer> pages;
    private List<QuestionDTO> questionDTOS;
    private Boolean showFirstPage;
    private Boolean showPreviousPage;
    private Boolean showNextPage;
    private Boolean showLastPage;

    public void setPagination(Integer size) {

        // 计算pageList并赋值
        int count = 0;
        pages = new LinkedList<>();
        for (int i = currentPage - 2; i <= totalPage && count++ < 5; i++) {
            if (i < 1) {
                continue;
            }
            pages.add(i);
        }

        boolean hasFirst = false, hasPrevious = false, hasLast = false, hasNext = false;
        // 是否展示上一页、下一页
        if (currentPage != 1) {
            hasPrevious = true;
        }
        if (!currentPage.equals(totalPage)) {
            hasNext = true;
        }
        // 是否展示首页、尾页、
        if (!pages.contains(1)) {
            hasFirst = true;
        }
        if (!pages.contains(totalPage)) {
            hasLast = true;
        }
        // 赋值
        showPreviousPage = hasPrevious;
        showNextPage = hasNext;
        showFirstPage = hasFirst;
        showLastPage = hasLast;
    }
}
