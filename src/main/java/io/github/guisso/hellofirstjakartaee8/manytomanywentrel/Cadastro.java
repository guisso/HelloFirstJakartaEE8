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
package io.github.guisso.hellofirstjakartaee8.manytomanywentrel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Luis Guisso <luis.guisso at ifnmg.edu.br>
 */
@Entity(name = "cadastroManyToManyWEntRel")
@Table(name = "tbl_manytomanywentrel_cadastro")
public class Cadastro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @Column(length = 300)
    private String observacao;

    // Para java.time.LocalDateTime, LocalDate, LocalTime:
    // @Column(columnDefinition = "TIMESTAMP") // DATE, TIME
    //
    // ** OU **
    //
    // Para java.util.Date:
    @Temporal(TemporalType.TIMESTAMP) // DATE, TIME
    private Date dataHoraCadastro;

//    @Column(columnDefinition = "TIMESTAMP")
//    private LocalDateTime dataHoraCadastro;
//    
    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonbTransient
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public void setDataHoraCadastro(Date dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }
//
//    public LocalDateTime getDataHoraCadastro() {
//        return dataHoraCadastro;
//    }
//
//    public void setDataHoraCadastro(LocalDateTime dataHoraCadastro) {
//        this.dataHoraCadastro = dataHoraCadastro;
//    }
    //</editor-fold>
//    
    //<editor-fold defaultstate="collapsed" desc="hashCode/equals/toString">

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final Cadastro other = (Cadastro) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Cadastro{" 
                + "id=" + id 
//                + ", pessoa=" + pessoa
                + ", endereco=" + endereco 
                + ", observacao=" + observacao 
                + ", dataHoraCadastro=" + dataHoraCadastro
                + '}';
    }
    //</editor-fold>
}
