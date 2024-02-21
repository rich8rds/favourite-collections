package com.favourite.collections.infrastructure.code.api;

import com.favourite.collections.infrastructure.code.data.CodeData;
import com.favourite.collections.infrastructure.code.service.CodeWriteService;
import com.favourite.collections.infrastructure.core.data.CommandResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Code and CodeValues")
@RestController
@RequestMapping("/codes")
@RequiredArgsConstructor
public class CodeController {
    private final CodeWriteService codeWriteService;

    @PostMapping
    public ResponseEntity<CommandResult> createCode(@RequestBody CodeData codeData) {
        return codeWriteService.createCode(codeData);
    }
}
