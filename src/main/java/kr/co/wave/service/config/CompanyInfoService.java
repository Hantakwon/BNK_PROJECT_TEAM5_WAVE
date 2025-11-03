package kr.co.wave.service.config;

import jakarta.persistence.EntityNotFoundException;
import kr.co.wave.dto.config.CompanyInfoDTO;
import kr.co.wave.entity.config.CompanyInfo;
import kr.co.wave.repository.config.CompanyInfoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyInfoService {

    private final CompanyInfoRepository companyInfoRepository;
    private final ModelMapper modelMapper;

    public CompanyInfoDTO getCompanyInfoSingle() {
        CompanyInfo companyInfo = companyInfoRepository.findCompanyInfoSingle()
                .orElseThrow(() -> new EntityNotFoundException("회사 정보가 존재하지 않습니다."));

        CompanyInfoDTO companyInfoDTO = modelMapper.map(companyInfo, CompanyInfoDTO.class);

        System.out.println("확인용 : " + companyInfoDTO);
        System.out.println("확인용 : " + companyInfoDTO.getCompanyId());

        return companyInfoDTO;
    }

    public void updateCompanyInfo(CompanyInfoDTO companyInfoDTO){
        companyInfoRepository.save(modelMapper.map(companyInfoDTO, CompanyInfo.class));
    }


}
