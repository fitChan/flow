package com.test.flow.dto;

import com.test.flow.domain.CustomExtension;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CustomResponse {

    @NotEmpty
    private String names;


    @Builder
    public CustomResponse(String names) {
        this.names = names;

    }

    public static CustomResponse of(CustomExtension customExtension) {
        return CustomResponse.builder()
                .names(customExtension.getNames())
                .build();
    }

    public static List<CustomResponse> list(List<CustomExtension> extensions) {
        List<CustomResponse> array = new ArrayList<>();
        for (CustomExtension e : extensions) {
            array.add(of(e));
        }
        return array;
    }

}
