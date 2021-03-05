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

import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Luis Guisso <luis.guisso at ifnmg.edu.br>
 */
@Stateless
public class PessoaBean
        implements PessoaBeanLocal {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Pessoa pessoa) {
        entityManager.persist(pessoa);
    }

    @Override
    public List<Pessoa> findAllPessoa() {
        Query query = entityManager.createQuery(
                "SELECT p FROM jpqlPessoa p");
//                "NÃO TEM JPQL AQUI!");
        return query.getResultList();
//        return (List<Pessoa>) query.getResultList();
    }
    
    @Override
    public List<Pessoa> findAllPessoaNPlusOne() {
        return entityManager.createNamedQuery("Pessoa.findAllN+1", Pessoa.class).getResultList();
        
    }

    @Override
    public List<Pessoa> findAllPessoaTyped() {
        TypedQuery<Pessoa> typedQuery = entityManager
                .createQuery("SELECT p FROM jpqlPessoa p", Pessoa.class);
        return typedQuery.getResultList();
    }

    @Override
    public Pessoa findPessoaByIdQuery(Long id) {
        Query query = entityManager
                .createQuery(
                        "select p from jpqlPessoa p "
                                + "where p.id = ?1");
        // Parâmetro indexado
        query.setParameter(1, id);
        return (Pessoa) query.getSingleResult();

        //
        // OU
        //
        // Retorno direto
//        return (Pessoa) entityManager
//                .createQuery(
//                        "select p from jpqlPessoa p "
//                        + "where p.id = :id")
        // Parâmetro nomeado
//                .setParameter("id", id)
//                .getSingleResult();
    }

    @Override
    public Pessoa findPessoaByIdTypedQuery(Long id) {
        TypedQuery<Pessoa> typedQuery = entityManager
                .createQuery("select p from jpqlPessoa p where p.id = :id", Pessoa.class);
        typedQuery.setParameter("id", id);
        return typedQuery.getSingleResult();
    }

    @Override
    public List<Pessoa> findAllByNamedQuery() {
        return entityManager
                .createNamedQuery("Pessoa.findAll", Pessoa.class)
                .getResultList();
    }

    @Override
    public List<Pessoa> findByNameBeginningWith(String inicial) {
        return entityManager
                .createNamedQuery("Pessoa.findByNameBeginningWith", Pessoa.class)
                .setParameter("nome", inicial + "%")
                .getResultList();
    }

    @Override
    public List<String> findPessoaGrupoAtivo() {
        return entityManager
                .createNamedQuery("Pessoa.findPessoaInGrupoAtivo", String.class)
                .getResultList();
    }

    @Override
    public List<Object[]> findPessoaGrupoInGrupoAtivo() {
        return entityManager
                .createNamedQuery("Pessoa.findPessoaGrupoInGrupoAtivo", Object[].class)
                .getResultList();
    }

    @Override
    public List<Object[]> findPessoaGrupoCadastroApos(LocalDateTime data) {
        return entityManager
                .createNamedQuery("Pessoa.findPessoaGrupoCadastroApos", Object[].class)
                .setParameter("data", data)
                .getResultList();
    }
    
    //
    // Verificar observação para a consulta respectiva
    // na entidade correlacionada
    //
//    @Override
//    public List<Pessoa> findAllPessoaByIdadeRange(Short idadeInicial, Short idadeFinal) {
//        return entityManager
//                .createNamedQuery("Pessoa.findAllPessoaByIdadeRange", Pessoa.class)
//                .setParameter("idadeInicial", idadeInicial)
//                .setParameter("idadeFinal", idadeFinal)
//                .getResultList();
//    }
    
    

}
