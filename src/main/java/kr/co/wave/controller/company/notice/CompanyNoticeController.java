package kr.co.wave.controller.company.notice;

import kr.co.wave.dto.board.CompanyNoticeDTO;
import kr.co.wave.service.board.CompanyNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CompanyNoticeController {

    private final CompanyNoticeService companyNoticeService;

    @GetMapping("/company/notice/list")
    public String noticeList(Model model) {
        List<CompanyNoticeDTO> noticeList = companyNoticeService.getCompanyNotice();
        model.addAttribute("noticeList", noticeList);
        return "company/notice/list";
    }

    @GetMapping("/company/notice/view/{companyNoticeId}")
    public String qnAView(@PathVariable int companyNoticeId, Model model) {
        model.addAttribute("notice", companyNoticeService.getCompanyNoticeById(companyNoticeId));
        return "company/notice/view";
    }

    @GetMapping("/company/notice/register")
    public String noticeRegister() {
        return "company/notice/register";
    }

    @PostMapping("/company/notice/register")
    public String noticeRegister(CompanyNoticeDTO companyNoticeDTO) {
        companyNoticeService.saveCompanyNotice(companyNoticeDTO);
        return "company/notice/list";
    }
}
