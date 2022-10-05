package pl.app.notification.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.app.notification.model.Template;
import pl.app.notification.service.TemplateService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/templates")
public class TemplateController {

    private final TemplateService templateService;

    @PostMapping
    public void saveTemplate(@RequestBody Template template) {//przerobić na TemplateDto
        templateService.saveTemplate(template);
    }

    @PutMapping("/{id}")
    public void updateTemplate(@RequestBody Template template, @PathVariable Long id) {//przerobić na TemplateDto
        templateService.updateTemplate(template, id);
    }
}
