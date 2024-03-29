package settleup.backend.domain.user.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import settleup.backend.domain.user.entity.dto.UserInfoDto;
import settleup.backend.domain.user.entity.dto.SettleUpTokenDto;
import settleup.backend.domain.user.service.KakaoService;
import settleup.backend.domain.user.service.LoginService;
import settleup.backend.domain.user.entity.dto.KakaoTokenDto;
import settleup.backend.global.api.ResponseDto;

import java.util.HashMap;
import java.util.Map;


@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final KakaoService kakaoService;
    private final LoginService loginService;


    @GetMapping("/kakao/callback")
    public ResponseEntity<ResponseDto> getTokenFromSocial(@RequestParam("code") String validCode) {
        KakaoTokenDto tokenInfo = kakaoService.getKakaoAccessToken(validCode);
        UserInfoDto userInfo = kakaoService.getUserInfo(tokenInfo.getAccess_token());
        SettleUpTokenDto settleUpLogin = kakaoService.registerUser(userInfo);
        ResponseDto responseDto = new ResponseDto(true, "successfully login", settleUpLogin);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);

    }

    @GetMapping("/checkToken")
    public ResponseEntity<ResponseDto> checkToken(@RequestHeader(value = "Authorization") String token) {
        UserInfoDto userInfoDto = loginService.validTokenOrNot(token);
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("userId", userInfoDto.getUserId());
        userInfo.put("userName", userInfoDto.getUserName());
        ResponseDto<Map<String, Object>> responseDto = new ResponseDto<>(true, "Token is valid, Login success", userInfo, null);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}




