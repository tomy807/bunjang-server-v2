package com.example.bunjangv2.src.user.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class LoginDto {

    @NotEmpty(message = "이메일를 입력해주세요")
    @Email
    private String email;
    @NotEmpty(message = "비밀번호를 입력해주세요")
    private String password;
}
