package com.example.bunjangv2.src.user;


import com.example.bunjangv2.config.security.JwtTokenProvider;
import com.example.bunjangv2.entity.User;
import com.example.bunjangv2.exception.AlreadyExistEmailException;
import com.example.bunjangv2.src.user.dto.LoginDto;
import com.example.bunjangv2.src.user.dto.LoginResDto;
import com.example.bunjangv2.src.user.dto.SignDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public void join(SignDto signDto) {

        if (userRepository.findByEmail(signDto.getEmail()).isPresent()) {
            throw new AlreadyExistEmailException();
        }

        User user = User.builder()
                .name(signDto.getUserName())
                .shopName(signDto.getUserName()+"상점")
                .email(signDto.getEmail())
                .password(passwordEncoder.encode(signDto.getPassword()))
                .phone(signDto.getPhone()).build();
        userRepository.save(user);
    }

    public LoginResDto login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        String token = jwtTokenProvider.createToken(user.getId(), user.getEmail());

        return new LoginResDto(token, user.getId());
    }
}
