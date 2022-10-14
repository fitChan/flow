package com.test.flow.controller;

import com.test.flow.valid.ExtensionValid;
import com.test.flow.dto.CustomResponse;
import com.test.flow.service.CustomExService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/customs")
public class CustomExController {
    private final CustomExService customService;
    private final ExtensionValid validator;

    public CustomExController(CustomExService customService, ExtensionValid validator) {
        this.customService = customService;

        this.validator = validator;
    }

    @GetMapping("/find-true")
    public ResponseEntity<List<CustomResponse>> findAllByflagTrue() {
        List<CustomResponse> allByFlagTrue = customService.findAllByFlagTrue();

        return ResponseEntity.status(HttpStatus.OK).body(allByFlagTrue);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody CustomResponse customResponse, Errors errors) {

        validator.customValidate(customResponse, errors);
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }
        int i = customService.create(customResponse);
        if (i == 1) {
            return ResponseEntity.status(HttpStatus.CREATED).body(i);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(i);
        }
    }

    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody CustomResponse customResponse, Errors errors) {

        validator.customValidate(customResponse, errors);
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }
        boolean update = customService.update(customResponse);
//        log.info("☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆={}",update);
//        if (!update) {
//            log.info("☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆={}",update);
//            throw new IllegalStateException("예외 발생");
//        }

        return ResponseEntity.status(HttpStatus.OK).body(update);
    }

}
