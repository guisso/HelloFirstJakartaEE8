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

import javax.ejb.Local;

/**
 *
 * @author Luis Guisso <luis.guisso at ifnmg.edu.br>
 */
@Local
public interface TarefaBeanLocal {

    public void salvar(Tarefa tarefa);

    public Tarefa mesclar(Tarefa tarefa);

    public Tarefa localizar(Long id);
    
}
