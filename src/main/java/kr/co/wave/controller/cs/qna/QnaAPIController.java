package kr.co.wave.controller.cs.qna;/*
package kr.co.draft.controller.qna;

import kr.co.draft.service.QnAService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class QnaAPIController {
    private final QnAService qnAService;

    @PostMapping("/ask")
    public ResponseEntity<String> askFastAPI(@RequestBody Map<String, Object> requestBody) {
        RestTemplate restTemplate = new RestTemplate();
        String fastApiUrl = "http://localhost:8000/ask";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        requestBody.put("context", "카드 설명:\n국내외 결제 2% 캐시백\n해외 결제 1% 캐시백\n연회비 1만 원\n연간 최대 캐시백 한도 30만 원");

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    fastApiUrl,
                    HttpMethod.POST,
                    entity,
                    String.class
            );
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("FastAPI 요청 실패: " + e.getMessage());
        }
    }
}
*/