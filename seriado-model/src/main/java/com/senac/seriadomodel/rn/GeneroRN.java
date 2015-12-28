package com.senac.seriadomodel.rn;

import com.senac.seriadomodel.bd.GeneroBD;
import com.senac.seriadomodel.bean.Genero;
import com.senac.seriadomodel.crud.CrudGenericoRN;
import java.util.List;

/**
 *
 * @author lossurdo
 */
public class GeneroRN extends CrudGenericoRN<Genero> {
    private final GeneroBD crudBD;

    public GeneroRN() {
        crudBD = new GeneroBD();
    }

    @Override
    public Genero consultar(Genero bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluir(Genero bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Genero salvar(Genero bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Genero> pesquisar(Genero bean) {
        return crudBD.namedQuery("Genero.findAll");
    }
    
}
