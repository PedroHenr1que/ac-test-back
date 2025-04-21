package com.api.test_accenture.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "empresa")
public class Empresa implements BaseEntity{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "empresa_seq")
    @SequenceGenerator(name = "empresa_seq", sequenceName = "empresa_seq", allocationSize = 1)
    private Long id;

    @Column(name = "cnpj", unique = true, length = 14, nullable = false)
    private String cnpj;

    @Column(name = "cep", length = 8, nullable = false)
    private String cep;

    @Column(name = "nome_fantasia", nullable = false)
    private String nomeFantasia;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmpresaFornecedor> empresaFornecedores = new ArrayList<>();

    public Empresa() {
    }

    public Empresa(Long id, String cnpj, String cep, String nomeFantasia) {
        this.id = id;
        this.cnpj = cnpj;
        this.cep = cep;
        this.nomeFantasia = nomeFantasia;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }
}
