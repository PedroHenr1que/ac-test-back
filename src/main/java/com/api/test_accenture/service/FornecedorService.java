package com.api.test_accenture.service;

import com.api.test_accenture.model.Fornecedor;
import com.api.test_accenture.model.enums.FornecedorTipo;
import com.api.test_accenture.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FornecedorService extends BaseCrudService<Fornecedor> {

    private final FornecedorRepository fornecedorRepository;

    private final VerifyCEPService verifyCEPService;

    @Autowired
    public FornecedorService(FornecedorRepository fornecedorRepository, VerifyCEPService verifyCEPService) {
        super(fornecedorRepository);
        this.fornecedorRepository = fornecedorRepository;
        this.verifyCEPService = verifyCEPService;
    }


    @Override
    public Fornecedor save(Fornecedor object) {
        verifyCEPService.verifyCEP(object.getCep());
        verifyFornecedorData(object);
        return super.save(object);
    }

    private void verifyFornecedorData(Fornecedor obj) {
        if (obj.getId() == null) {
            if (this.fornecedorRepository.findByDocumento(obj.getDocumento()).isPresent()) {
                throw new RuntimeException("CPF/CNPJ already exists");
            }
        }

        if (FornecedorTipo.FISICA.equals(obj.getTipo())) {
            if (obj.getRg() == null || obj.getRg().isBlank()) {
                throw new RuntimeException("Must provide RG for 'pessoa fisica'");
            }
            if (obj.getDataNascimento() == null) {
                throw new RuntimeException("Must provide birthday for 'pessoa fisica'");
            }
        }
    }
}
