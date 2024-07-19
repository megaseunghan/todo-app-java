package com.todo.todoapp.application.user;

import com.todo.todoapp.domain.user.repository.UserRepository;
import com.todo.todoapp.presentation.user.dto.request.SignUpRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public long signup(SignUpRequest request) {
        checkDuplicateNickname(request.nickname());
        User user = userRepository.save(request.toEntity());

        return user.id();
    }

    private void checkDuplicateNickname(String nickname) {
        if (userRepository.existByNickname(nickname)) {
            throw new DuplicateNicknameException(UserErrorCode.DUPLICATE_NICKNAME);
        }
    }
}
