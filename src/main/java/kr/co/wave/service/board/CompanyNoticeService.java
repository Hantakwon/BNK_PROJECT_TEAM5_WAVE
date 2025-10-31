package kr.co.wave.service.board;

import kr.co.wave.dto.board.CompanyNoticeDTO;
import kr.co.wave.entity.board.CompanyNotice;
import kr.co.wave.repository.CompanyNoticeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyNoticeService {

    private final CompanyNoticeRepository companyNoticeRepository;
    private final ModelMapper modelMapper;

    public List<CompanyNoticeDTO> getCompanyNotice() {
        List<CompanyNoticeDTO> companyNoticeDTOList = new ArrayList<>();
        List<CompanyNotice> companyNoticeList = companyNoticeRepository.findAll();

        for (CompanyNotice companyNotice : companyNoticeList) {
            companyNoticeDTOList.add(modelMapper.map(companyNotice, CompanyNoticeDTO.class));
        }

        return companyNoticeDTOList;
    }

    public CompanyNoticeDTO getCompanyNoticeById(int companyNoticeId) {
        return modelMapper.map(companyNoticeRepository.findById(companyNoticeId).get(), CompanyNoticeDTO.class);
    }

    public void saveCompanyNotice(CompanyNoticeDTO companyNoticeDTO) {
        companyNoticeRepository.save(modelMapper.map(companyNoticeDTO, CompanyNotice.class));
    }

}
