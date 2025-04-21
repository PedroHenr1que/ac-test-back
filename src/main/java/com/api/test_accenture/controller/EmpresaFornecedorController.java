package com.api.test_accenture.controller;


import com.api.test_accenture.model.EmpresaFornecedor;
import com.api.test_accenture.service.EmpresaFornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/empresa-fornecedor")
public class EmpresaFornecedorController extends BaseRestApiController<EmpresaFornecedor> {

    private final EmpresaFornecedorService empresaFornecedorService;
    @Autowired
    public EmpresaFornecedorController(EmpresaFornecedorService empresaFornecedorService) {
        super(empresaFornecedorService);
        this.empresaFornecedorService = empresaFornecedorService;
    }

    @PostMapping(value = "create") //?empresaId=1&fornecedorId=2
    public ResponseEntity<?> create(@RequestParam Long empresaId,
                                  @RequestParam Long fornecedorId) {

        return new ResponseEntity<>(empresaFornecedorService.save(empresaId, fornecedorId), HttpStatus.OK);
    }
}
