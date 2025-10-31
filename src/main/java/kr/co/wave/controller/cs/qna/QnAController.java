package kr.co.wave.controller.cs.qna;

import kr.co.wave.dto.QnADTO;
import kr.co.wave.service.QnAService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class QnAController {

    private final QnAService qnAService;

    // 아이디로 QnA 자세히 보기
    @GetMapping("/cs/qna/view/{id}")
    public String qnAView(@PathVariable int id, Model model) {
        model.addAttribute("qna", qnAService.getQnAById(id));

        return "cs/qna/view";
    }

    // QnA 목록화면 이동
    @GetMapping("/cs/qna/list")
    public String qnAList(@RequestParam(required = false) String searchType,
                          @RequestParam(required = false) String keyword,
                          @RequestParam(defaultValue = "0") int page,
                          @RequestParam(required = false) String sortBy,
                          @RequestParam(defaultValue = "desc") String direction,
                          @RequestParam(required = false) Boolean answered,
                          Model model) {

        Page<QnADTO> qnAList = qnAService.getQnAAllBySearch(searchType, keyword, page, 10, sortBy, direction, answered);

        model.addAttribute("qnAList", qnAList);

        return "cs/qna/list";
    }

    // QnA 등록화면 이동
    @GetMapping("/cs/qna/register")
    public String qnARegister(Model model) {

        return "cs/qna/register";
    }

    // QnA 등록
    @PostMapping("/cs/qna/register")
    public String qnASave(QnADTO qnADTO) {
        qnAService.qnARegister(qnADTO);

        return "redirect:/cs/qna/list";
    }




}
