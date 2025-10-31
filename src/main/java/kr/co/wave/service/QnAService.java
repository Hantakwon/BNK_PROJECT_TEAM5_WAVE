package kr.co.wave.service;

import kr.co.wave.dto.QnADTO;
import kr.co.wave.entity.QnA;
import kr.co.wave.repository.QnARepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QnAService {

    private final QnARepository qnARepository;
    private final ModelMapper modelMapper;

    public QnADTO getQnAById(Integer id) {
        Optional<QnA> optQnA = qnARepository.findById(id);
        return optQnA.map(qnA -> modelMapper.map(qnA, QnADTO.class)).orElse(null);
    }

    public List<QnADTO> getQnAAll() {
        List<QnA> qnAList = qnARepository.findAll();

        List<QnADTO> qnADTOList = new ArrayList<>();
        for (QnA qnA : qnAList) {
            qnADTOList.add(modelMapper.map(qnA, QnADTO.class));
        }
        return qnADTOList;
    }

    public Page<QnADTO> getQnAAllBySearch(String searchType, String keyword, int page, int size, String sortBy, String direction, Boolean answered){
        String st = (searchType == null) ? "" : searchType.trim();
        String kw = (keyword == null) ? "" : keyword.trim();

        if(sortBy == null || sortBy.isEmpty()){
            sortBy = "createdAt";
        }

        Sort sort = "desc".equalsIgnoreCase(direction)
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return qnARepository.findQnABySearch(st, kw, answered, pageable);
    }

    public void qnARegister(QnADTO qnADTO) {
        qnARepository.save(modelMapper.map(qnADTO, QnA.class));
    }

}
