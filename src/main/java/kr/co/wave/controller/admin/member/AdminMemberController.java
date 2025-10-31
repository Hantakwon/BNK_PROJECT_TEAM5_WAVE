package kr.co.wave.controller.admin.member;

import kr.co.wave.dto.MemberDTO;
import kr.co.wave.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminMemberController {

    private final MemberService memberService;

    @GetMapping("/admin/member/list")
    public String adminProductList(Model model) {
        List<MemberDTO> memberList = memberService.getMemberAll();

        model.addAttribute("memberList", memberList);

        return "admin/member/list";
    }

    @GetMapping("/admin/member/view/{memId}")
    public String adminProductView(@PathVariable String memId, Model model) {
        MemberDTO member = memberService.getMemberById(memId);
        model.addAttribute("member", member);

        return "admin/member/view";
    }


}
