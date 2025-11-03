package kr.co.wave.service.board.company;


import kr.co.wave.dto.QnADTO;
import kr.co.wave.dto.board.company.NoticeDTO;
import kr.co.wave.dto.board.company.PressDTO;
import kr.co.wave.entity.board.company.Press;
import kr.co.wave.repository.board.company.PressRepository;
import kr.co.wave.service.board.util.DTOConverter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PressService {

    private final PressRepository pressRepository;
    private final DTOConverter converter;
    private final ModelMapper modelMapper;

    public List<PressDTO> getPressAll() {
        List<PressDTO> pressDTOList = new ArrayList<>();
        List<Press> pressList = pressRepository.findAll();

        for (Press press : pressList) {
            pressDTOList.add(modelMapper.map(press, PressDTO.class));
        }

        return pressDTOList;
    }

    public PressDTO getPressById(int pressId) {
        return modelMapper.map(pressRepository.findById(pressId).get(), PressDTO.class);
    }

    public void savePress(PressDTO pressDTO) {
        pressRepository.save(modelMapper.map(pressDTO, Press.class));
    }

    public void deletePress(int pressId) {
        pressRepository.deleteById(pressId);
    }
    
    public Page<PressDTO> getPressAllBySearch(String searchType, String keyword, int page, int size, String sortBy, String direction){
        String st = (searchType == null) ? "" : searchType.trim();
        String kw = (keyword == null) ? "" : keyword.trim();

        if(sortBy == null || sortBy.isEmpty()){
            sortBy = "created_at";
        }

        Sort sort = "desc".equalsIgnoreCase(direction)
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Object[]> result = pressRepository.findPressBySearch(st, kw, pageable);

        return converter.convert(result, row -> new PressDTO(
                (int) (row[0]),           // pressId
                (String) row[1],                    // title
                converter.clobToString(row[2]),     // content
                (String) row[3],                    // writer
                converter.toLocalDateTime(row[4])   // createdAt
        ));
    }

    // 최신글 5개 찾아오기
    public List<PressDTO> getPressRecentlyFive(){
        List<PressDTO> pressDTOList = new ArrayList<>();

        List<Press> pressList = pressRepository.findTop5ByOrderByCreatedAtDesc();

        for (Press press : pressList) {
            pressDTOList.add(modelMapper.map(press, PressDTO.class));
        }

        return pressDTOList;
    }
}
