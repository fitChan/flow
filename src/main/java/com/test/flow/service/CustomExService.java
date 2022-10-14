package com.test.flow.service;

import com.test.flow.domain.CustomExtension;
import com.test.flow.dto.CustomResponse;
import com.test.flow.repository.CustomExRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomExService {
    private final CustomExRepository customRepository;
    public CustomExService(CustomExRepository customRepository) {
        this.customRepository = customRepository;
    }

    //true List 반환
    @Transactional
    public List<CustomResponse> findAllByFlagTrue(){
        List<CustomExtension> allByFlagTrue = customRepository.findTop200ByFlagTrueOrderByModifiedAtDesc();
        return CustomResponse.list(allByFlagTrue);
    }

    //create
    @Transactional
    public int create(CustomResponse customResponse) {
        Optional<CustomExtension> byNames = customRepository.findByNames(customResponse.getNames());
        if (byNames.isEmpty()) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            CustomExtension customExtension = modelMapper.map(customResponse, CustomExtension.class);
            customRepository.save(customExtension);
            return 1;
        }else {
            CustomExtension isExist = byNames.get();
            if(!isExist.isFlag()){
                ModelMapper modelMapper = new ModelMapper();
                modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
                CustomExtension customExtension = modelMapper.map(byNames, CustomExtension.class);
                customExtension.update(customResponse);
                customRepository.save(customExtension);
                return 1;
            }else{
                return 0;
            }
        }
    }
    //update
    @Transactional
    public boolean update(CustomResponse customResponse){
        Optional<CustomExtension> byNames = customRepository.findByNames(customResponse.getNames());
        if (!byNames.isEmpty() && byNames.get().isFlag()) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            CustomExtension customExtension = modelMapper.map(byNames, CustomExtension.class);
            customExtension.update(customResponse);
            customRepository.save(customExtension);
            return true;
        }else{
            throw new IllegalStateException("예외 발생");
        }
    }
}
