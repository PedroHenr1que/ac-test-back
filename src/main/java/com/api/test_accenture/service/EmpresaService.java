package com.api.test_accenture.service;

import com.api.test_accenture.model.Empresa;
import com.api.test_accenture.model.Fornecedor;
import com.api.test_accenture.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService extends BaseCrudService<Empresa> {

    private final VerifyCEPService verifyCEPService;
    @Autowired
    protected EmpresaService(EmpresaRepository empresaRepository, VerifyCEPService verifyCEPService) {
        super(empresaRepository);
        this.verifyCEPService = verifyCEPService;
    }

    @Override
    public Empresa save(Empresa object) {
        verifyCEPService.verifyCEP(object.getCep());
        return super.save(object);
    }
}
