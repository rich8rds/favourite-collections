package com.favourite.collections.infrastructure.code.util;

import com.favourite.collections.infrastructure.code.data.CodeData;
import com.favourite.collections.infrastructure.code.domain.Code;

public class ModelMapper {
    public Code fromCodeDataToCode(CodeData code) {
        boolean isSystemDefined = code.isSystemDefined();
        int externalUse = code.getExternalUse();

        return Code.builder()
                .externalUse(externalUse)
                .name(code.getName())
                .systemDefined(isSystemDefined)
                .build();
    }

    public CodeData fromCodeDataToCode(Code code) {
        boolean isSystemDefined = code.isSystemDefined();
        int externalUse = code.getExternalUse();

        return CodeData.builder()
                .externalUse(externalUse)
                .name(code.getName())
                .systemDefined(isSystemDefined)
                .build();
    }
}
