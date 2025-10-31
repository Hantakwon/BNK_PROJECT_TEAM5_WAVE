package kr.co.wave.service.config;

import jakarta.transaction.Transactional;
import kr.co.wave.dto.config.BasicConfigDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasicConfigService {

    private final SiteInfoService siteInfoService;
    private final CompanyInfoService companyInfoService;
    private final CustomerServiceInfoService customerServiceInfoService;
    private final ModelMapper modelMapper;

    @Transactional
    public BasicConfigDTO getBasicConfig() {
        return new BasicConfigDTO(
            siteInfoService.getSiteRecentlyOne(),
            companyInfoService.getCompanyInfoSingle(),
            customerServiceInfoService.getCustomerServiceInfoSingle()
        );
    }


}
