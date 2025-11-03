package kr.co.wave.service.board.company;

import kr.co.wave.dto.board.company.InvestorInfoDTO;
import kr.co.wave.dto.board.company.NoticeDTO;
import kr.co.wave.entity.board.company.InvestorInfo;
import kr.co.wave.repository.board.company.InvestorInfoRepository;
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
public class InvestorInfoService {

    private final InvestorInfoRepository investorInfoRepository;
    private final DTOConverter converter;
    private final ModelMapper modelMapper;

    public List<InvestorInfoDTO> getInvestorInfoAll() {
        List<InvestorInfoDTO> investorInfoDTOList = new ArrayList<>();
        List<InvestorInfo> investorInfoList = investorInfoRepository.findAll();

        for (InvestorInfo investorInfo : investorInfoList) {
            investorInfoDTOList.add(modelMapper.map(investorInfo, InvestorInfoDTO.class));
        }

        return investorInfoDTOList;
    }

    public InvestorInfoDTO getInvestorInfoById(int investorInfoId) {
        return modelMapper.map(investorInfoRepository.findById(investorInfoId).get(), InvestorInfoDTO.class);
    }

    public void saveInvestorInfo(InvestorInfoDTO investorInfoDTO) {
        investorInfoRepository.save(modelMapper.map(investorInfoDTO, InvestorInfo.class));
    }

    public void deleteInvestorInfo(int investorInfoId) {
        investorInfoRepository.deleteById(investorInfoId);
    }
    
    public Page<InvestorInfoDTO> getInvestorInfoAllBySearch(String searchType, String keyword, int page, int size, String sortBy, String direction){
        String st = (searchType == null) ? "" : searchType.trim();
        String kw = (keyword == null) ? "" : keyword.trim();

        if(sortBy == null || sortBy.isEmpty()){
            sortBy = "created_at";
        }

        Sort sort = "desc".equalsIgnoreCase(direction)
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Object[]> result = investorInfoRepository.findInvestorInfoBySearch(st, kw, pageable);

        return converter.convert(result, row -> new InvestorInfoDTO(
                (int) (row[0]),           // noticeId
                (String) row[1],                    // title
                converter.clobToString(row[2]),     // content
                (String) row[3],                    // writer
                converter.toLocalDateTime(row[4])   // createdAt
        ));
    }

    // 최신글 5개 찾아오기
    public List<InvestorInfoDTO> getInvestorInfoRecentlyFive(){
        List<InvestorInfoDTO> InvestorInfoDTOList = new ArrayList<>();

        List<InvestorInfo> investorList = investorInfoRepository.findTop5ByOrderByCreatedAtDesc();

        for (InvestorInfo investorInfo : investorList) {
            InvestorInfoDTOList.add(modelMapper.map(investorInfo, InvestorInfoDTO.class));
        }

        return InvestorInfoDTOList;
    }
}
