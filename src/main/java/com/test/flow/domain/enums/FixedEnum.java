package com.test.flow.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum FixedEnum{
    BAT("bat"),
    CMD("cmd"),
    COM("com"),
    CPL("cpl"),
    EXE("exe"),
    SCR("scr"),
    JS("js");


    private final String value;

    FixedEnum(String value) {
        this.value = value;
    }
    public String value() {
        return value;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static FixedEnum findByValue(String value){
        return Stream.of(FixedEnum.values())
                .filter(c -> c.value.equals(value))
                .findFirst()
                .orElse(null);
    }
}
