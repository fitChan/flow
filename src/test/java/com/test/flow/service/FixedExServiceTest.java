package com.test.flow.service;

import com.test.flow.domain.FixedExtension;
import com.test.flow.domain.enums.FixedEnum;
import com.test.flow.dto.FixedResponse;
import com.test.flow.repository.FixedExRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.assertj.core.api.Assertions;
import java.util.List;

@SpringBootTest
class FixedExServiceTest {
    @Autowired
    private FixedExService fixedExService;
    @Autowired
    private FixedExRepository fixedExRepository;

    @AfterEach
    void after() {
        fixedExRepository.deleteAll();
    }

    @Test
    @DisplayName("고정 확장자 추가")
    void findList(){
        //given
        FixedResponse bat= new FixedResponse(FixedEnum.BAT);
        FixedResponse cmd= new FixedResponse(FixedEnum.CMD);

        //when
        fixedExService.findByNames(bat);
        fixedExService.findByNames(cmd);

        List<FixedExtension> allByFlagTrue = fixedExRepository.findAllByFlagTrue();
        int size = allByFlagTrue.size();
        //then
        Assertions.assertThat(size).isEqualTo(2);
        Assertions.assertThat(allByFlagTrue.get(0).getNames()).isEqualTo(FixedEnum.BAT);
        Assertions.assertThat(allByFlagTrue.get(1).getNames()).isEqualTo(FixedEnum.CMD);
    }

    @Test
    @DisplayName("고정 확장자 삭제")
    void deleteExtension(){
        //given
        FixedResponse bat= new FixedResponse(FixedEnum.BAT);
        FixedResponse cmd= new FixedResponse(FixedEnum.CMD);

        //when
        fixedExService.findByNames(bat);
        fixedExService.findByNames(cmd);
        fixedExService.findByNames(bat);

        List<FixedExtension> allByFlagTrue = fixedExRepository.findAllByFlagTrue();
        int size = allByFlagTrue.size();
        //then
        Assertions.assertThat(size).isEqualTo(1);
        Assertions.assertThat(allByFlagTrue.get(0).getNames()).isEqualTo(FixedEnum.CMD);
    }

    @Test
    @DisplayName("적합하지 않은 고정 확장자")
    void extensionError(){
        Assertions.assertThatThrownBy(
                ()-> fixedExService.findByNames(new FixedResponse(FixedEnum.valueOf("notExistEnum")))
        ).isInstanceOf(IllegalArgumentException.class);
    }




}