package com.example.aruba.senddocumentsystem.requestmanage.domain.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
public abstract class ValidatingService<DTO> {

    @Autowired
    private final Validator validator;

    public void validate(DTO dto) {
        log.debug("Validating DTO");
        Set<ConstraintViolation<DTO>> violations = validator.validate(dto);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<DTO> violation : violations) {
                sb.append(violation.getPropertyPath())
                        .append(": ")
                        .append(violation.getMessage())
                        .append("; ");
            }
            throw new IllegalArgumentException("Validation failed: " + sb);
        }
    }
}
