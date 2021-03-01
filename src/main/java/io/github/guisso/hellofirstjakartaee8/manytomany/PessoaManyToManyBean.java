/*
 * Copyright (C) 2021 Luis
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
package io.github.guisso.hellofirstjakartaee8.manytomany;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Luis Guisso <luis.guisso at ifnmg.edu.br>
 */
@Stateless
public class PessoaManyToManyBean implements PessoaManyToManyBeanLocal {

//    @PersistenceContext(unitName = "RemotePU")
    @PersistenceContext(unitName = "SistemaPU")
    private EntityManager entityManager;

    @Override
    public void save(Pessoa pessoa) {
        entityManager.persist(pessoa);
    }

    @Override
    public Pessoa find(Long id) {
        
        // TODO Objetos associados não são retornados nesta transação, daí gera-se uma exceção caso algum acesso a esses seja requisitado fora desta
        return entityManager.find(Pessoa.class, id);
        
        // TODO "join fetch" não funcionou para a carga antecipada dos dados afim de evitar erro por inicialização tardia, mesmo dentro de uma transação completa
//        TypedQuery<Pessoa> typedQuery 
//                = entityManager.createQuery("select p from tbl_manytomany_pessoa p left join fetch p.enderecos where p.id = :id",
//                Pessoa.class);
//        typedQuery.setParameter("id", id);
//        return typedQuery.getSingleResult();
        
    }

}
