package kr.co.wave.controller.company.board;

import kr.co.wave.dto.board.company.EventDTO;
import kr.co.wave.service.board.company.EventService;
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
public class EventController {

    private final EventService eventService;

    @GetMapping("/company/event/list")
    public String eventList(@RequestParam(required = false) String searchType,
                             @RequestParam(required = false) String keyword,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(required = false) String sortBy,
                             @RequestParam(defaultValue = "desc") String direction,
                             Model model) {

        Page<EventDTO> eventList = eventService.getEventAllBySearch(searchType, keyword, page, 10, sortBy, direction);
        model.addAttribute("eventList", eventList);
        return "company/event/list";
    }

    @GetMapping("/company/event/view/{eventId}")
    public String eventView(@PathVariable int eventId, Model model) {
        model.addAttribute("event", eventService.getEventById(eventId));
        return "company/event/view";
    }

    @GetMapping("/company/event/register")
    public String eventRegister() {
        return "company/event/register";
    }

    @PostMapping("/company/event/register")
    public String eventRegister(EventDTO eventDTO) {
        eventService.saveEvent(eventDTO);
        return "redirect:/company/event/list";
    }

}
