package pl.app.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.app.common.ChangePasswordMailDto;
import pl.app.common.SendEmailDto;
import pl.app.user.client.NotificationClient;
import pl.app.user.domain.User;
import pl.app.user.repository.RoleRepository;
import pl.app.user.repository.UserRepository;
import pl.app.user.service.UserService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final NotificationClient notificationClient;

    @Override
    public User create(User user) {
        roleRepository.findByName("ROLE_USER").ifPresent(role -> user.setRoles(List.of(role)));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional/*(propagation = Propagation.REQUIRES_NEW)*/
// dzięki adnotacji zapisują się zmiany do bazy danych  PAMIETAJ!!!
    public User update(Long id, User user) {
        User userDb = findById(id);
        userDb.setFirstname(user.getFirstname());
        userDb.setLastname(user.getLastname());
        userDb.setEmail(user.getEmail());
        return userDb;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id: " + id + " doesn't exist"));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> getPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public void remindPassword(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Not found email: " + email));
        user.setToken(UUID.randomUUID().toString());
        userRepository.save(user);

        notificationClient.sendEmail(SendEmailDto.<ChangePasswordMailDto>builder()
                .name("change_password")
                .receiver(email)
                .variables(new ChangePasswordMailDto(user.getFirstname(), user.getLastname(), "http://localhost:8081/api/users/valid-token?token=" + user.getToken()))
                .build());

    }

    @Override
    public void validToken(String token) {
        Optional<User> user = userRepository.findByToken(token);
        if (user.isEmpty()) {
            throw new EntityNotFoundException("Token is not exists");
        }
    }

    @Override
    @Transactional
    public void changePassword(String token, String password) {
        User user = userRepository.findByToken(token)
                .orElseThrow(() -> new EntityNotFoundException("Token is not exists"));

        user.setToken(null);
        user.setPassword(passwordEncoder.encode(password));
    }
}
