package kr.co.wave.controller.admin;

import kr.co.wave.dto.QnADTO;
import kr.co.wave.service.board.cs.QnAService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminMainController {

    private final QnAService qnaService;
    @GetMapping({"/admin", "/admin/", "/admin/index"})
    public String admin(Model model) {

        List<QnADTO> qnAList = qnaService.getQnAAll();

        model.addAttribute("qnAList", qnAList);

        return "admin/index";
    }
}
