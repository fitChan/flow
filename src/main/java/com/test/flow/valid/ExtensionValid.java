package com.test.flow.valid;


import com.test.flow.domain.enums.FixedEnum;
import com.test.flow.dto.CustomResponse;
import com.test.flow.dto.FixedResponse;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.Arrays;
//
//BAT("bat"),
//        CMD("cmd"),
//        COM("com"),
//        CPL("cpl"),
//        EXE("exe"),
//        SCR("scr"),
//        JS("js");
@Component
public class ExtensionValid {

    public void setFixedEnum(FixedResponse r, Errors e) {
        ArrayList<String> a = new ArrayList<>();
        a.add("bat");
        a.add("com");
        a.add("cpl");
        a.add("exe");
        a.add("scr");
        a.add("js");
        try{
            boolean contains = a.contains(r.getNames().toString());
        }catch (NullPointerException n){
            e.reject("wrong input", "Only bat / cmd / com / cpl / exe / scr / js");
        }
    }

    public void customValidate(CustomResponse customResponse, Errors errors) {
        if (customResponse.getNames().length() > 20) {
            errors.reject("wrong input", "The name can be up to 20 characters.");
        }
    }
}
