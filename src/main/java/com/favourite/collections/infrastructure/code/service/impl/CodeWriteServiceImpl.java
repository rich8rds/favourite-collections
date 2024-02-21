package com.favourite.collections.infrastructure.code.service.impl;

import com.favourite.collections.infrastructure.code.data.CodeData;
import com.favourite.collections.infrastructure.code.domain.Code;
import com.favourite.collections.infrastructure.code.repository.CodeRepository;
import com.favourite.collections.infrastructure.code.service.CodeWriteService;
import com.favourite.collections.infrastructure.code.util.ModelMapper;
import com.favourite.collections.infrastructure.core.data.CommandResult;
import com.favourite.collections.infrastructure.core.exceptions.AbstractPlatformException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CodeWriteServiceImpl implements CodeWriteService {
    private final CodeRepository codeRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<CommandResult> createCode(CodeData codeData) {
        String name = codeData.getName();

        if(codeRepository.existsByName(name)){
            throw new AbstractPlatformException("error.msg.code.exists", "Code Data name already exists", 409);
        }
        Code code = codeRepository.saveAndFlush(modelMapper.fromCodeDataToCode(codeData));
        return ResponseEntity.ok().body(CommandResult.builder()
                        .entityId(code.getId())
                        .message("Code created")
                        .resourceId("Code with name " + name + " created")
                .build());
    }
}
