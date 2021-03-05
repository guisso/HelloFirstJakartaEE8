/*
 * Copyright (C) 2021 Luis Guisso <luis.guisso at ifnmg.edu.br>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.guisso.hellofirstjakartaee8.jpql;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Luis Guisso <luis.guisso at ifnmg.edu.br>
 */
@Entity(name = "jpqlPessoa")
@Table(name = "tbl_jpql_pessoa")
//<editor-fold defaultstate="collapsed" desc="@NamedQueries">
@NamedQueries({
    @NamedQuery(
            name = "Pessoa.findAll",
            query = "SELECT p "
            + "FROM jpqlPessoa p"),
    @NamedQuery(
            name = "Pessoa.findAllN+1",
            query = "SELECT DISTINCT p "
            + "FROM jpqlPessoa p, "
            + "IN (p.telefones) t"),
    @NamedQuery(
            name = "Pessoa.findByNameBeginningWith",
            query = "SELECT p "
            + "FROM jpqlPessoa p "
            + "WHERE LOWER(p.nome) LIKE LOWER(:nome)"),
    @NamedQuery(
            name = "Pessoa.findPessoaInGrupoAtivo",
            query = "SELECT DISTINCT p.nome "
            + "FROM jpqlPessoa p "
            + "JOIN p.cadastros c "
            + "JOIN c.grupo g "
            + "WHERE g.ativo = true"),
    // ATENÇÃO! Somente funciona com JDK15... :(
//            query = """
//                    SELECT DISTINCT p.nome
//                    FROM jpqlPessoa p
//                    JOIN p.cadastros c
//                    JOIN c.grupo g
//                    WHERE g.ativo = true
//            """),
    @NamedQuery(
            name = "Pessoa.findPessoaGrupoInGrupoAtivo",
            query = "SELECT p.nome, g.nome "
            + "FROM jpqlPessoa p "
            + "JOIN p.cadastros c "
            + "JOIN c.grupo g "
            + "WHERE g.ativo = true"),
    @NamedQuery(
            name = "Pessoa.findPessoaGrupoCadastroApos",
            query = "SELECT p.nome, g.nome, c.dataHoraCadastro "
            + "FROM jpqlPessoa p "
            + "JOIN p.cadastros c "
            + "JOIN c.grupo g "
            + "WHERE c.dataHoraCadastro >= :data"),
//            query = "NÃO TEM JPQL AQUI")
    //
    // Falha porque idade, apesar de ser um atributo
    // acessível no objeto, não corresponde a um campo
    // presente no banco de dados (@Transient)
    //
//    @NamedQuery(
//            name = "Pessoa.findAllPessoaByIdadeRange",
//            query = "SELECT p "
//                    + "FROM jpqlPessoa p "
//                    + "WHERE p.idade "
//                    + "BETWEEN :idadeInicial AND :idadeFinal"
//    )
})
//</editor-fold>
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            length = 65,
            nullable = false)
    private String nome;

    @Column(columnDefinition = "DATE")
    private LocalDate nascimento;

    @Transient
    private Short idade;

    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(
            name = "endereco_id")
    private Endereco endereco;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(
            name = "pessoa_id")
    private List<Telefone> telefones;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(
            name = "pessoa_id")
    private List<Cadastro> cadastros;

    public Pessoa() {
        telefones = new ArrayList<>();
        cadastros = new ArrayList<>();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public List<Cadastro> getCadastros() {
        return cadastros;
    }

    public void setCadastros(List<Cadastro> cadastros) {
        this.cadastros = cadastros;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
        // Cálculo simplificado
        this.idade = (short) (LocalDate.now().getYear()
                - this.nascimento.getYear());
    }

    public Short getIdade() {
        return this.idade;
    }

    // TODO Excluir após testes
    public void setIdade(Short idade) {
        this.idade = idade;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="hashCode/equals/toString">
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pessoa other = (Pessoa) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Pessoa{"
                + "id=" + id
                + ", nome=" + nome
                + ", endereco=" + endereco
                + ", nascimento=" + nascimento
                + ", idade=" + idade
                + ", telefones=" + telefones
                + ", cadastros=" + cadastros
                + '}';
    }
    //</editor-fold>

}
