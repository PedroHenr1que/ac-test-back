package com.api.test_accenture.controller;

import com.api.test_accenture.model.Empresa;
import com.api.test_accenture.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/empresa")
public class EmpresaController extends BaseRestApiController<Empresa> {

    @Autowired
    public EmpresaController(EmpresaService empresaService) {
        super(empresaService);
    }
}
