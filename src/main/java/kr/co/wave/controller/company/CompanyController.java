package kr.co.wave.controller.company;

import kr.co.wave.dto.board.company.EventDTO;
import kr.co.wave.dto.board.company.InvestorInfoDTO;
import kr.co.wave.dto.board.company.NoticeDTO;
import kr.co.wave.dto.board.company.PressDTO;
import kr.co.wave.service.board.company.EventService;
import kr.co.wave.service.board.company.InvestorInfoService;
import kr.co.wave.service.board.company.NoticeService;
import kr.co.wave.service.board.company.PressService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CompanyController {

    private final EventService eventService;
    private final InvestorInfoService investorInfoService;
    private final NoticeService noticeService;
    private final PressService pressService;

    @GetMapping({"/company", "/company/", "/company/index"})
    public String companyIndex(Model model) {

        List<EventDTO> eventList = eventService.getEventRecentlyFive();
        List<InvestorInfoDTO> investorInfoList = investorInfoService.getInvestorInfoRecentlyFive();
        List<NoticeDTO> noticeList = noticeService.getNoticeAll();
        List<PressDTO> pressList = pressService.getPressAll();

        model.addAttribute("eventList", eventList);
        model.addAttribute("investorInfoList", investorInfoList);
        model.addAttribute("noticeList", noticeList);
        model.addAttribute("pressList", pressList);

        return "company/index";
    }


}
