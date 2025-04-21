package com.api.test_accenture.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "empresa_fornecedor",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_empresa_fornecedor", columnNames = {"empresa_id", "fornecedor_id"})
        }
)
public class EmpresaFornecedor implements BaseEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "empresa_fornecedor_seq")
    @SequenceGenerator(name = "empresa_fornecedor_seq", sequenceName = "empresa_fornecedor_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private Fornecedor fornecedor;

    public EmpresaFornecedor() {
    }

    public EmpresaFornecedor(Long id, Empresa empresa, Fornecedor fornecedor) {
        this.id = id;
        this.empresa = empresa;
        this.fornecedor = fornecedor;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}
