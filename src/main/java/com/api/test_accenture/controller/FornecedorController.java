package com.api.test_accenture.controller;

import com.api.test_accenture.model.Fornecedor;
import com.api.test_accenture.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fornecedor")
public class FornecedorController extends BaseRestApiController<Fornecedor> {

    @Autowired
    public FornecedorController(FornecedorService fornecedorService) {
        super(fornecedorService);
    }
}
