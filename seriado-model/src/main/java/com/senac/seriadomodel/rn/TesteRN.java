package com.senac.seriadomodel.rn;

import com.senac.seriadomodel.bd.TesteBD;
import com.senac.seriadomodel.bean.Teste;
import com.senac.seriadomodel.crud.CrudGenericoRN;
import java.util.List;

/**
 *
 * @author lossurdo
 */
public class TesteRN extends CrudGenericoRN<Teste> {
    private final TesteBD crudBD;

    public TesteRN() {
        crudBD = new TesteBD();        
    }

    @Override
    public Teste consultar(Teste bean) {
        avaliarConsultar(crudBD, bean);
        return crudBD.consultar(bean);
    }

    @Override
    public boolean excluir(Teste bean) {
        avaliarExcluir(crudBD, bean);
        return crudBD.excluir(bean);
    }

    @Override
    public Teste salvar(Teste bean) {
        avaliarSalvar(crudBD, bean);
        return crudBD.salvar(bean);
    }

    public List<Teste> pesquisar(String valor) {
        return crudBD.namedQuery("findAll");
    }
    
}
