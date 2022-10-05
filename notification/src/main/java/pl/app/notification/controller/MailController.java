package pl.app.notification.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.app.common.ChangePasswordMailDto;
import pl.app.common.MailDto;
import pl.app.notification.service.MailService;

@RestController
@RequestMapping("/api/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping
    public void sendMail(@RequestBody MailDto mailDto) {
        mailService.sendEmail(mailDto.getReceiver(), mailDto.getVariables(), mailDto.getName());
    }

//    @PostMapping
//    public void sendChangePasswordMail(@RequestBody ChangePasswordMailDto changePasswordMailDto) {
//        mailService.sendEmail(changePasswordMailDto.getFirstname(), changePasswordMailDto.getSurname(), changePasswordMailDto.getLink());
//    }
}
