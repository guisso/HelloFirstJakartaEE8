/*
 * Copyright (C) 2021 Luis Guisso <luis.guisso at ifnmg.edu.br>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This source code is used in software development academic classes
 * at IFNMG - Câmpus Montes Claros by Professor Luis Guisso
 * and is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.guisso.hellofirstjakartaee8;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Luis Guisso <luis.guisso at ifnmg.edu.br>
 */
@Stateless
public class TarefaBean implements TarefaBeanLocal {

    @PersistenceContext(unitName = "SistemaPU")
    EntityManager entityManager;
    
    @Override
    public void salvar(Tarefa tarefa) {
        
        if (tarefa.getId() == null || tarefa.getId() == 0) {
            System.out.println(">> Salvar nova Tarefa");
            entityManager.persist(tarefa);
        } else {
            System.out.println(">> Atualizar Tarefa pré-existente");
//            tarefa = entityManager.find(Tarefa.class, tarefa.getId());
            entityManager.merge(tarefa);
        }
    }

    @Override
    public Tarefa mesclar(Tarefa tarefa) {
        System.out.println(">> Mesclar Tarefa: " + tarefa);
        return entityManager.merge(tarefa);
    }

    @Override
    public Tarefa localizar(Long id) {
        return entityManager.find(Tarefa.class, id);
    }
    
    
    
}
