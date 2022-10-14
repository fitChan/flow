package com.test.flow.service;

import com.test.flow.domain.CustomExtension;
import com.test.flow.dto.CustomResponse;
import com.test.flow.repository.CustomExRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomExServiceTest {
    @Autowired
    CustomExRepository customExRepository;
    @Autowired
    CustomExService customExService;

    @AfterEach
    void after() {
        customExRepository.deleteAll();
    }

    @Test
    @DisplayName("개인 설정 확장자 최대 200개")
    void Top200List() {
        //given
        for (long i = 0L; i < 205L; i++) {
            CustomExtension customExtension =
                    CustomExtension.builder()
                            .names("i 번째")
                            .flag(true)
                            .build();
            customExRepository.save(customExtension);
        }
        //when
        List<CustomResponse> allByFlagTrue = customExService.findAllByFlagTrue();
        int size = allByFlagTrue.size();

        //then
        Assertions.assertThat(size).isEqualTo(200);
    }

    @Test
    @DisplayName("개인 설정 확장자 생성")
    void create() {
        //given
        CustomResponse customResponse = new CustomResponse("example");
        //when
        int i = customExService.create(customResponse);
        //then
        Assertions.assertThat(i).isEqualTo(1);
    }

    @Test
    @DisplayName("개인 설정 확장자 2회 중복 생성시 flag = true")
    void doubleDuplicatedCreate() {
        //given
        CustomResponse customResponse = new CustomResponse("example");
        //when
        customExService.create(customResponse);
        customExService.create(customResponse);

        Optional<CustomExtension> example = customExRepository.findByNames("example");
        CustomExtension customExtension = example.get();

        //then
        Assertions.assertThat(customExtension.isFlag()).isTrue();
    }

    @Test
    @DisplayName("개인 설정 확장자 3회 중복 생성시 flag = true")
    void multipleDuplicatedCreate() {
        //given
        CustomResponse customResponse = new CustomResponse("example");
        //when
        customExService.create(customResponse);
        customExService.create(customResponse);
        customExService.create(customResponse);

        Optional<CustomExtension> example = customExRepository.findByNames("example");
        CustomExtension customExtension = example.get();

        //then
        Assertions.assertThat(customExtension.isFlag()).isTrue();
    }

    @Test
    @DisplayName("개인 설정 확장자 flag = false로 변경 (삭제)")
    void update() {
        //given
        CustomResponse customResponse = new CustomResponse("example");
        customExService.create(customResponse);
        //when
        customExService.update(customResponse);

        Optional<CustomExtension> example = customExRepository.findByNames("example");
        CustomExtension customExtension = example.get();

        //then
        Assertions.assertThat(customExtension.isFlag()).isFalse();
    }

    @Test
    @DisplayName("개인 설정 확장자가 없는데 삭제 요청을 넣었을 경우")
    void fakeUpdate() {
        //given
        CustomResponse customResponse = new CustomResponse("example");
        //when
        Optional<CustomExtension> example = customExRepository.findByNames("example");
        Assertions.assertThatThrownBy(() ->
                customExService.update(customResponse))
                .isInstanceOf(IllegalStateException.class);
        //then
        Assertions.assertThat(example.isEmpty()).isTrue();
    }


    @Test
    @DisplayName("개인 설정 확장자 flag = false로 변경 (삭제) 후 재생성")
    void updateAndCreate() {
        //given
        CustomResponse customResponse = new CustomResponse("example");
        customExService.create(customResponse);
        customExService.update(customResponse);

        //when
        customExService.create(customResponse);

        Optional<CustomExtension> example = customExRepository.findByNames("example");
        CustomExtension customExtension = example.get();

        //then
        Assertions.assertThat(customExtension.isFlag()).isTrue();
    }
    @Test
    @DisplayName("커스텀 확장자에 고정확장자를 넣엇을 경우")
    void wrongInputNotCustom() {
        Assertions.assertThatThrownBy(() ->
                customExService.create(new CustomResponse("bat")))
                .isInstanceOf(IllegalArgumentException.class);
    }
}