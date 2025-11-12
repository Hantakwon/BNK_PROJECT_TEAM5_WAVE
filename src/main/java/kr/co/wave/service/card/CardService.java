package kr.co.wave.service.card;

import jakarta.transaction.Transactional;
import kr.co.wave.dto.card.AnnualFeeDTO;
import kr.co.wave.dto.card.BenefitDTO;
import kr.co.wave.dto.card.CardDTO;
import kr.co.wave.dto.card.CardRequestDTO;
import kr.co.wave.entity.card.AnnualFee;
import kr.co.wave.entity.card.Benefit;
import kr.co.wave.entity.card.Card;
import kr.co.wave.repository.card.AnnualFeeRepository;
import kr.co.wave.repository.card.BenefitRepository;
import kr.co.wave.repository.card.CardRepository;
import kr.co.wave.repository.config.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final AnnualFeeRepository annualFeeRepository;
    private final BenefitRepository benefitRepository;
    private final ModelMapper modelMapper; // Entity와 DTO를 변환해주는 객체

    public Page<CardDTO> getCardAllBySearch(String searchType, String keyword, int page, int size){
        String st = (searchType == null) ? "" : searchType.trim();
        String kw = (keyword == null) ? "" : keyword.trim();
        Pageable pageable = PageRequest.of(page, size);
        return cardRepository.findCardAllBySearch(st, kw, pageable);
    }

    public CardDTO getCardById(int cardId){
        Optional<Card> card = cardRepository.findById(cardId);
        if(card.isPresent()){
            return modelMapper.map(card.get(), CardDTO.class);
        }
        return null;
    }
    @Transactional
    public void registerCard(CardRequestDTO cardRequestDTO){

        // 카드
        Card card = Card.builder()
                .name(cardRequestDTO.getName())
                .engName(cardRequestDTO.getEngName())
                .type(cardRequestDTO.getType())
                .isCompany(cardRequestDTO.getIsCompany())
                .description(cardRequestDTO.getDescription())
                .build();

        System.out.println(card.toString());
        Card savedCard = cardRepository.save(card);

        // 연회비
        for(int i = 0; i<cardRequestDTO.getAnnualFeeName().size(); i++) {
            AnnualFee annualFee = AnnualFee.builder()
                                                    .annualName(cardRequestDTO.getAnnualFeeName().get(i))
                                                    .card(savedCard)
                                                    .build();

            System.out.println(annualFee.toString());

            annualFeeRepository.save(modelMapper.map(annualFee, AnnualFee.class));
        }

        // 혜택
        for(int i = 0; i<cardRequestDTO.getCategory().size(); i++){
            Benefit benefit = Benefit.builder()
                    .benefitType(cardRequestDTO.getBenefitType().get(i))
                    .benefitCategory(String.valueOf(cardRequestDTO.getCategory().get(i)))
                    .unit(cardRequestDTO.getUnit().get(i))
                    .value(cardRequestDTO.getValue().get(i))
                    .limit(cardRequestDTO.getLimit().get(i))
                    .benefitDescription(cardRequestDTO.getBenefitDescription().get(i))
                    .card(savedCard)
                    .build();

            System.out.println(i + "번째" + benefit.toString());

            benefitRepository.save(benefit);
        }
    }

}
