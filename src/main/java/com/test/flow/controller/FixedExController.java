package com.test.flow.controller;

import com.test.flow.valid.ExtensionValid;
import com.test.flow.dto.FixedResponse;

import com.test.flow.service.FixedExService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController @Slf4j
@RequestMapping("/fixes")
public class FixedExController {
    private final FixedExService service;
private final ExtensionValid valid;
    public FixedExController(FixedExService service, ExtensionValid valid) {
        this.service = service;

        this.valid = valid;
    }

    @GetMapping("/find-true")
    public ResponseEntity<List<FixedResponse>> findAllByFlagTrue() {
        List<FixedResponse> allByFlagTrue = service.findAllByFlagTrue();

        return ResponseEntity.status(HttpStatus.OK).body(allByFlagTrue);
    }

    @PutMapping("/update")
    public ResponseEntity<?> findByNames(@Valid @RequestBody FixedResponse fixedResponse, Errors errors) {
        valid.setFixedEnum(fixedResponse, errors);
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }


        service.findByNames(fixedResponse);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
