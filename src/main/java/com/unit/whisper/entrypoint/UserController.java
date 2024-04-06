package com.unit.whisper.entrypoint;


import com.unit.whisper.entrypoint.request.AuthRequest;
import com.unit.whisper.entrypoint.response.AuthResponse;
import com.unit.whisper.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return userService.loginKakao(request);
    }
}
