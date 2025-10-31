package kr.co.wave.service.config;

import jakarta.persistence.EntityNotFoundException;
import kr.co.wave.dto.config.SiteInfoDTO;
import kr.co.wave.dto.config.TermsDTO;
import kr.co.wave.entity.config.SiteInfo;
import kr.co.wave.entity.config.Terms;
import kr.co.wave.repository.config.SiteInfoRepository;
import kr.co.wave.repository.config.TermsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TermsService {

    private final TermsRepository termsRepository;
    private final ModelMapper modelMapper;

    public List<TermsDTO> getTermsAll(){
        List<TermsDTO> termsDTOList = new ArrayList<>();

        List<Terms> termsList = termsRepository.findAll();
        for(Terms term : termsList){
            termsDTOList.add(modelMapper.map(term, TermsDTO.class));
        }

        return termsDTOList;
    }

    public void updateTerms(TermsDTO termsDTO){
        Terms terms = termsRepository.findById(termsDTO.getTermsId())
                .orElseThrow(() -> new EntityNotFoundException("약관을 찾을 수 없습니다."));

        // 안전하게 수정할 필드만 직접 세팅
        terms.setContent(termsDTO.getContent());
        terms.setRequired(termsDTO.isRequired());

        terms.setUpdatedAt(LocalDate.now());

        termsRepository.save(terms);
    }
}
