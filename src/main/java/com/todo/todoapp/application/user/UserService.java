package com.todo.todoapp.application.user;

import com.todo.todoapp.domain.user.model.User;
import com.todo.todoapp.domain.user.repository.UserRepository;
import com.todo.todoapp.global.exception.user.DuplicateNicknameException;
import com.todo.todoapp.global.exception.user.NoSuchUserException;
import com.todo.todoapp.global.exception.user.code.UserErrorCode;
import com.todo.todoapp.presentation.user.dto.request.LoginRequest;
import com.todo.todoapp.presentation.user.dto.request.SignUpRequest;
import com.todo.todoapp.presentation.user.dto.response.VerifyUserResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public long signup(SignUpRequest request) {
        checkDuplicateNickname(request.nickname());
        User user = userRepository.save(request.toEntity());

        return user.getId();
    }

    @Transactional
    public VerifyUserResponse verifyUser(LoginRequest loginRequest) {
        User user = getUserByUserName(loginRequest.userName());

        return VerifyUserResponse.builder()
                .userName(user.getUserName())
                .roles(Set.of(user.getUserRole()))
                .build();
    }

    @Transactional
    public void updateAccessToken(String userName, String accessToken) {
        User user = getUserByUserName(userName);
        user.updateAccessToken(accessToken);
    }

    private User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName).orElseThrow(() -> new NoSuchUserException(UserErrorCode.FIND_FAILED));
    }

    private void checkDuplicateNickname(String nickname) {
        if (userRepository.existsByNickname(nickname)) {
            throw new DuplicateNicknameException(UserErrorCode.DUPLICATE_NICKNAME);
        }
    }

}
