package com.unit.whisper.entrypoint;


import com.unit.whisper.entrypoint.request.AuthRequest;
import com.unit.whisper.entrypoint.response.AuthResponse;
import com.unit.whisper.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "사용자 컨트롤러")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "카카오 소셜 로그인")
    @GetMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return userService.loginKakao(request);
    }

    @Operation(summary = "리프레시 토큰으로 액세스 토큰 재발급")
    @GetMapping("/login/reissue")
    public AuthResponse reissue(@RequestHeader(value = "Refresh-Token") String refreshToken) {
        return userService.reissue(refreshToken);
    }
}
