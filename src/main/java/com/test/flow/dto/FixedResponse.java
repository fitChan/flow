package com.test.flow.dto;

import com.test.flow.domain.FixedExtension;
import com.test.flow.domain.enums.FixedEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class FixedResponse {

    private FixedEnum names;


    @Builder
    public FixedResponse(FixedEnum names) {
        this.names = names;
    }






    public static FixedResponse of(FixedExtension fixedExtension) {
        return FixedResponse.builder().names(fixedExtension.getNames()).build();
    }

    public static List<FixedResponse> list(List<FixedExtension> fixedExtension) {
        List<FixedResponse> array = new ArrayList<>();
        for (FixedExtension e : fixedExtension) {
            array.add(of(e));
        }
        return array;
    }


}
