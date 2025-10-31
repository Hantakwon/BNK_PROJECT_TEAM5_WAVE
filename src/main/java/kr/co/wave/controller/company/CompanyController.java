package kr.co.wave.controller.company;

import kr.co.wave.dto.board.CompanyNoticeDTO;
import kr.co.wave.service.board.CompanyNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyNoticeService companyNoticeService;

    @GetMapping({"/company", "/company/", "/company/index"})
    public String companyIndex(Model model) {
        List<CompanyNoticeDTO> noticeList = companyNoticeService.getCompanyNotice();
        model.addAttribute("noticeList", noticeList);
        return "company/index";
    }


}
