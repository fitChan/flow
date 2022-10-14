package com.test.flow.service;

import com.test.flow.domain.FixedExtension;
import com.test.flow.domain.enums.FixedEnum;
import com.test.flow.dto.FixedResponse;
import com.test.flow.repository.FixedExRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FixedExService {
    private final FixedExRepository fixedExRepository;

    public FixedExService(FixedExRepository fixedExRepository) {
        this.fixedExRepository = fixedExRepository;
    }

    //리스트 반환
    @Transactional
    public List<FixedResponse> findAllByFlagTrue() {
        List<FixedExtension> allByFlagTrue = fixedExRepository.findAllByFlagTrue();
        return FixedResponse.list(allByFlagTrue);
    }

    @Transactional
    public void findByNames(FixedResponse fixedResponse) {
        exceptionThow(fixedResponse);
        try {
            FixedExtension byNames = fixedExRepository.findByNames(fixedResponse.getNames());
            byNames.update(fixedResponse);
            fixedExRepository.save(byNames);
        } catch (NullPointerException e) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            FixedExtension map = modelMapper.map(fixedResponse, FixedExtension.class);
            fixedExRepository.save(map);
        }
    }

    private void exceptionThow(FixedResponse fixedResponse) {
        if(!fixedResponse.getNames().equals(FixedEnum.BAT) ||
                !fixedResponse.getNames().equals(FixedEnum.EXE)||
                !fixedResponse.getNames().equals(FixedEnum.CMD)||
                !fixedResponse.getNames().equals(FixedEnum.COM)||
                !fixedResponse.getNames().equals(FixedEnum.SCR)||
                !fixedResponse.getNames().equals(FixedEnum.CPL) ||
                !fixedResponse.getNames().equals(FixedEnum.JS)){
            throw new IllegalArgumentException("적합하지 않은 입력입니다.");
        }
    }
}
