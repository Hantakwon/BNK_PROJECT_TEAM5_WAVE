package kr.co.wave.controller.cs;

import kr.co.wave.dto.QnADTO;
import kr.co.wave.service.QnAService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CsController {

    private final QnAService qnAService;

    @GetMapping({"/cs", "/cs/", "/cs/index"})
    public String index(Model model) {
        List<QnADTO> qnAList = qnAService.getQnAAll();
        model.addAttribute("qnAList", qnAList);
        return "/cs/index";
    }
}
