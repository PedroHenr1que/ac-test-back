package com.api.test_accenture.repository;

import com.api.test_accenture.model.Fornecedor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FornecedorRepository extends BaseCrudRepository<Fornecedor> {


    Optional<Fornecedor> findByDocumento(String documento);
}
