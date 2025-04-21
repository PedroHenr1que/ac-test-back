package com.api.test_accenture.model;

import com.api.test_accenture.model.enums.FornecedorTipo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fornecedor")
public class Fornecedor implements BaseEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "fornecedor_seq")
    @SequenceGenerator(name = "fornecedor_seq", sequenceName = "fornecedor_seq", allocationSize = 1)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "cep", length = 8, nullable = false)
    private String cep;

    @Column(name = "tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private FornecedorTipo tipo;

    @Column(name = "documento", unique = true, length = 14, nullable = false)
    private String documento; //cpf or cnpj

    @Column(name = "rg", length = 7)
    private String rg;

    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "fornecedor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmpresaFornecedor> fornecedorEmpresas = new ArrayList<>();

    public Fornecedor() {
    }

    public Fornecedor(Long id, String nome, String email, String cep, FornecedorTipo tipo, String documento, String rg, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cep = cep;
        this.tipo = tipo;
        this.documento = documento;
        this.rg = rg;
        this.dataNascimento = dataNascimento;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public FornecedorTipo getTipo() {
        return tipo;
    }

    public void setTipo(FornecedorTipo tipo) {
        this.tipo = tipo;
    }

    @JsonIgnore
    public boolean isPessoaFisica() {
        return FornecedorTipo.FISICA.equals(this.tipo);
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @JsonIgnore
    public boolean isUnderAge() {

        Period age = Period.between(dataNascimento, LocalDate.now());

        return age.getYears() < 18;
    }
}
