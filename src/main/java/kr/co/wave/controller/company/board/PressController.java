package kr.co.wave.controller.company.board;

import kr.co.wave.dto.board.company.PressDTO;
import kr.co.wave.service.board.company.PressService;
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
public class PressController {

    private final PressService pressService;

    @GetMapping("/company/press/list")
    public String pressList(@RequestParam(required = false) String searchType,
                             @RequestParam(required = false) String keyword,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(required = false) String sortBy,
                             @RequestParam(defaultValue = "desc") String direction,
                             Model model) {

        Page<PressDTO> pressList = pressService.getPressAllBySearch(searchType, keyword, page, 10, sortBy, direction);
        model.addAttribute("pressList", pressList);
        return "company/press/list";
    }

    @GetMapping("/company/press/view/{pressId}")
    public String pressView(@PathVariable int pressId, Model model) {
        model.addAttribute("press", pressService.getPressById(pressId));
        return "company/press/view";
    }

    @GetMapping("/company/press/register")
    public String pressRegister() {
        return "company/press/register";
    }

    @PostMapping("/company/press/register")
    public String pressRegister(PressDTO pressDTO) {
        pressService.savePress(pressDTO);
        return "redirect:/company/press/list";
    }

}
