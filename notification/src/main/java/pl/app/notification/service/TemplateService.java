package pl.app.notification.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.app.notification.model.Template;
import pl.app.notification.repository.TemplateRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class TemplateService {

    private final TemplateRepository templateRepository;

    public Template findTemplateByName(String name) {
        return templateRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Template does not exist: " + name));
    }

    @Transactional
    public void updateTemplate(Template template, Long id) {
        Template templateDb = templateRepository.getById(id);
        templateDb.setName(template.getName());
        templateDb.setBody(template.getBody());
        templateDb.setSubject(template.getSubject());
    }

    public void saveTemplate(Template template) {
        templateRepository.save(template);
    }

}
