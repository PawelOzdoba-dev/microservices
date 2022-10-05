package pl.app.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.app.user.dto.ChangePasswordDto;
import pl.app.user.dto.RemindPasswordDto;
import pl.app.user.dto.UserDto;
import pl.app.user.mapper.UserMapper;
import pl.app.user.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

//    @GetMapping
//    public Map<String, String> getUserTest() {
//        log.info("from user controller");
//        return new HashMap<>();
//    }

//    @GetMapping
//    public Map<String, String> getUserTestRestTemplate() {
//        log.info("from user controller");
//        return new HashMap<>();
//    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated() && (hasRole('ADMIN') || @securityService.hasAccessToUser(#id))")
    public UserDto getUser(@PathVariable Long id) {
        return userMapper.toDto(userService.findById(id));
    }

    @PostMapping
//    @Validated(CreateUser.class)
    @PreAuthorize("isAnonymous()")//tworzymy usera jeśli jesteśmy niezalogowani
    public UserDto createUser(@RequestBody @Valid UserDto user) {
        return userMapper.toDto(userService.create(userMapper.toDao(user)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated() && (hasRole('ADMIN') || @securityService.hasAccessToUser(#id))")//dla zalogowanego użytkownika robi update, czy user ma role admina lub czy ma dostęp do usera
    public UserDto updateUser(@PathVariable Long id, @RequestBody @Valid UserDto user) {
        return userMapper.toDto(userService.update(id, userMapper.toDao(user)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")//sprawdza czy mamy jakąkolwiek z ról
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")//silnik spel, wywołuje metodę hasRole
    public Page<UserDto> getUserPage(@RequestParam int page, @RequestParam int size) {
        return userService.getPage(PageRequest.of(page, size)).map(userMapper::toDto);
    }

    @PatchMapping("/remind-password")
    public void remindPassword(@RequestBody RemindPasswordDto remindPasswordDto) {
        userService.remindPassword(remindPasswordDto.getEmail());
    }

    @GetMapping("/valid-token")
    public void validToken(@RequestParam ("token") String token){
        userService.validToken(token);
    }

    @PatchMapping("/change-password")
    public void changePassword(@RequestBody ChangePasswordDto changePasswordDto){
        userService.changePassword(changePasswordDto.getToken(), changePasswordDto.getPassword());
    }

    @PatchMapping("/avatar")
    public void updateAvatar(@RequestParam MultipartFile file) {

    }
}
