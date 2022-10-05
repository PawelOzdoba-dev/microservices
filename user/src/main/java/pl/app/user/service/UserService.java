package pl.app.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.app.user.domain.User;

import java.util.List;

public interface UserService {

    User create(User user);
    List<User> findAll();
    User update(Long id, User user);
    User findById(Long id);
    void deleteById(Long id);
    Page<User> getPage(Pageable pageable);
    void remindPassword(String email);
    void validToken(String token);
    void changePassword(String token, String password);
}
