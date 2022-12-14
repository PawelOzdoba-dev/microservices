package pl.app.user.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.app.user.service.UserService;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UserService userService;

    public boolean hasAccessToUser(Long userId) {
        try {
            return userService.findById(userId).getEmail().equals(SecurityUtils.getCurrentUserEmail());

        } catch (EntityNotFoundException e) {
            return false;
        }
    }
}
