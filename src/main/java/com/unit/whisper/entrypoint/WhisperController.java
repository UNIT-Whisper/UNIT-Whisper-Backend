package com.unit.whisper.entrypoint;


import com.unit.whisper.common.response.CustomResponseEntity;
import com.unit.whisper.dto.WhisperCreateRequest;
import com.unit.whisper.entrypoint.response.WhisperResponse;
import com.unit.whisper.service.WhisperService;
import io.swagger.v3.oas.annotations.Operation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/whisper")
@RequiredArgsConstructor
public class WhisperController {

    private final WhisperService whisperService;

    @PostMapping
    @Operation(summary = "구름 생성", description = "사용자들의 속삭임을 저장한다.")
    public CustomResponseEntity<Long> createWhisper(
            @Valid @RequestBody WhisperCreateRequest request) {
        whisperService.saveWhisper(request);

        return CustomResponseEntity.success(whisperService.saveWhisper(request));
    }

    @Operation(summary = "구름 조회", description = "사용자들의 저장한 구름 정보를 알림 목록에서 상세 조회한다.")
    @GetMapping("/{whisperId}")
    public CustomResponseEntity<WhisperResponse> getWhisper(@PathVariable Long whisperId) {
        return CustomResponseEntity.success(whisperService.getWhisper(whisperId));
    }
}
