package com.ufpr.domain.produto;

import javax.persistence.*;

@Entity
@Table(name = "Produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduto")
    private Long idProduto;;

    @Column(name = "descricao")
    private String descricao;

    public Produto() {}

    public Produto(Long idProduto, String descricao) {
        this.idProduto = idProduto;
        this.descricao = descricao;
    }

    public Long getId() {
        return idProduto;
    }

    public void setId(Long idProduto) {
        this.idProduto = idProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
