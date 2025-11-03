package kr.co.wave.controller.company.board;

import kr.co.wave.dto.board.company.InvestorInfoDTO;
import kr.co.wave.service.board.company.InvestorInfoService;
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
public class InvestorInfoController {

    private final InvestorInfoService investorInfoService;

    @GetMapping("/company/investorInfo/list")
    public String investorInfoList(@RequestParam(required = false) String searchType,
                             @RequestParam(required = false) String keyword,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(required = false) String sortBy,
                             @RequestParam(defaultValue = "desc") String direction,
                             Model model) {

        Page<InvestorInfoDTO> investorInfoList = investorInfoService.getInvestorInfoAllBySearch(searchType, keyword, page, 10, sortBy, direction);
        model.addAttribute("investorInfoList", investorInfoList);
        return "company/investorInfo/list";
    }

    @GetMapping("/company/investorInfo/view/{investorInfoId}")
    public String investorInfoView(@PathVariable int investorInfoId, Model model) {
        model.addAttribute("investorInfo", investorInfoService.getInvestorInfoById(investorInfoId));
        return "company/investorInfo/view";
    }

    @GetMapping("/company/investorInfo/register")
    public String investorInfoRegister() {
        return "company/investorInfo/register";
    }

    @PostMapping("/company/investorInfo/register")
    public String investorInfoRegister(InvestorInfoDTO investorInfoDTO) {
        investorInfoService.saveInvestorInfo(investorInfoDTO);
        return "redirect:/company/investorInfo/list";
    }

}
