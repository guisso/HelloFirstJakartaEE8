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
package io.github.guisso.hellofirstjakartaee8.onetomany;

import javax.ejb.Local;

/**
 * 
 * @author Luis Guisso <luis.guisso at ifnmg.edu.br>
 */
@Local
public interface PessoaOneToManyBeanLocal {

    public void save(Pessoa pessoa);

    Pessoa find(Long id);
    
}
