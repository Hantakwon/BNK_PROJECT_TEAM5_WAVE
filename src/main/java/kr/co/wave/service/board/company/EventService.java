package kr.co.wave.service.board.company;


import kr.co.wave.dto.board.company.EventDTO;
import kr.co.wave.dto.board.company.InvestorInfoDTO;
import kr.co.wave.entity.board.company.Event;
import kr.co.wave.entity.board.company.InvestorInfo;
import kr.co.wave.repository.board.company.EventRepository;
import kr.co.wave.service.board.util.DTOConverter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final DTOConverter converter;
    private final ModelMapper modelMapper;

    public List<EventDTO> getEventAll() {
        List<EventDTO> eventDTOList = new ArrayList<>();
        List<Event> eventList = eventRepository.findAll();

        for (Event event : eventList) {
            eventDTOList.add(modelMapper.map(event, EventDTO.class));
        }

        return eventDTOList;
    }

    public EventDTO getEventById(int eventId) {
        return modelMapper.map(eventRepository.findById(eventId).get(), EventDTO.class);
    }

    public void saveEvent(EventDTO eventDTO) {
        eventRepository.save(modelMapper.map(eventDTO, Event.class));
    }

    public void deleteEvent(int eventId) {
        eventRepository.deleteById(eventId);
    }

    public Page<EventDTO> getEventAllBySearch(String searchType, String keyword, int page, int size, String sortBy, String direction){
        String st = (searchType == null) ? "" : searchType.trim();
        String kw = (keyword == null) ? "" : keyword.trim();

        if(sortBy == null || sortBy.isEmpty()){
            sortBy = "created_at";
        }

        Sort sort = "desc".equalsIgnoreCase(direction)
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Object[]> result = eventRepository.findEventBySearch(st, kw, pageable);

        return converter.convert(result, row -> new EventDTO(
                (int) (row[0]),           // noticeId
                (String) row[1],                    // title
                converter.clobToString(row[2]),     // content
                (String) row[3],                    // writer
                converter.toLocalDateTime(row[4])   // createdAt
        ));
    }

    // 최신글 5개 찾아오기
    public List<EventDTO> getEventRecentlyFive(){
        List<EventDTO> eventDTOList = new ArrayList<>();

        List<Event> eventList = eventRepository.findTop5ByOrderByCreatedAtDesc();

        for (Event event : eventList) {
            eventDTOList.add(modelMapper.map(event, EventDTO.class));
        }

        return eventDTOList;
    }
}
