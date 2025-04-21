package com.api.test_accenture.service;

import com.api.test_accenture.model.EmpresaFornecedor;
import com.api.test_accenture.repository.EmpresaFornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaFornecedorService extends BaseCrudService<EmpresaFornecedor> {

    private final EmpresaService empresaService;
    private final FornecedorService fornecedorService;
    private final VerifyCEPService verifyCEPService;

    private static final String PARANA_UF = "PR";
    @Autowired
    public EmpresaFornecedorService(EmpresaFornecedorRepository empresaFornecedorRepository,
                                    VerifyCEPService verifyCEPService,
                                    EmpresaService empresaService,
                                    FornecedorService fornecedorService) {
        super(empresaFornecedorRepository);
        this.verifyCEPService = verifyCEPService;
        this.empresaService = empresaService;
        this.fornecedorService = fornecedorService;
    }

    public EmpresaFornecedor save(Long empresaId, Long fornecedorId) {
        var empresa = empresaService.findById(empresaId);
        var fornecedor = fornecedorService.findById(fornecedorId);

        var uf = verifyCEPService.getState(empresa.getCep());

        if (uf.equalsIgnoreCase(PARANA_UF) && fornecedor.isPessoaFisica() && fornecedor.isUnderAge()) {
            throw new RuntimeException("Not allowed under age supplier in the state of Parana");
        }

        return super.save(new EmpresaFornecedor(null, empresa, fornecedor));
    }
}
