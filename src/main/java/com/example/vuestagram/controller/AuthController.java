package com.example.vuestagram.controller;


import com.example.vuestagram.dto.request.LoginRequestDTO;
import com.example.vuestagram.dto.response.ResponseBase;
import com.example.vuestagram.dto.response.ResponseLogin;
import com.example.vuestagram.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // REST API 컨트롤러
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
//   return ResponseEntity.status(200).body(responseBase);
//   리턴을 ResponseEntity 안에 데이터를 responseBase 를 리턴하고
//   ResponseBase 안에 데이터가 responseLogin 이여서 데이터 타입이
//   ResponseEntity<ResponseBase<ResponseLogin>> 이다 .
    public ResponseEntity<ResponseBase<ResponseLogin>> login(
            HttpServletResponse response
            ,@Valid @RequestBody LoginRequestDTO loginRequestDTO
    ) {

//        return loginRequestDTO.getAccount() + "." + loginRequestDTO.getPassword();
        ResponseLogin responseLogin = authService.login(loginRequestDTO, response);

        ResponseBase<ResponseLogin> responseBase =
                ResponseBase.<ResponseLogin>builder()
                        .status(200).message("로그인 성공")
                        .data(responseLogin)
                        .build();
        return ResponseEntity.status(200).body(responseBase);
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
