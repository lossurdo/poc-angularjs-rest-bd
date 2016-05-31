package com.senac.seriadomodel.rn;

import com.senac.seriadomodel.bd.GeneroBD;
import com.senac.seriadomodel.bean.Genero;
import com.senac.seriadomodel.crud.CrudGenericoRN;
import com.senac.seriadomodel.crud.RNException;
import java.util.List;
import org.apache.commons.lang.StringUtils;

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
        avaliarConsultar(crudBD, bean);
        return crudBD.consultar(bean);
    }

    @Override
    public boolean excluir(Genero bean) {
        avaliarExcluir(crudBD, bean);
        return crudBD.excluir(bean);
    }

    @Override
    public Genero salvar(Genero bean) {
        avaliarSalvar(crudBD, bean);
        
        if(StringUtils.isBlank(bean.getGenero())) {
            throw new RNException(RNException.Tipo.CAMPOS_OBRIGATORIOS_VIOLADOS);
        }
        
        return crudBD.salvar(bean);
    }

    @Override
    public Genero alterar(Genero bean) {
        avaliarAlterar(crudBD, bean);

        if(StringUtils.isBlank(bean.getGenero())) {
            throw new RNException(RNException.Tipo.CAMPOS_OBRIGATORIOS_VIOLADOS);
        }

        return crudBD.alterar(bean);
    }

    @Override
    public List<Genero> pesquisar(Genero bean) {
        return crudBD.pesquisar(bean);
    }
    
    @Override
    public List<Genero> pesquisar(String valor) {
        valor = avaliarPesquisar(valor);
        return crudBD.pesquisar(valor);
    }
    
}
