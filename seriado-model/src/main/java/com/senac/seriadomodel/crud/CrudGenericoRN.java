package com.senac.seriadomodel.crud;

public abstract class CrudGenericoRN<T> implements CrudGenerico<T> {

    protected void avaliarSalvar(CrudGenerico crudGenerico, Object object) {
        if (crudGenerico.consultar(object) != null) {
            throw new RNException(RNException.Tipo.REGISTRO_JA_EXISTE, "Registro já existe");
        }
    }

    protected Object avaliarConsultar(CrudGenerico crudGenerico, Object object) {
        Object o = crudGenerico.consultar(object);
        if (o == null) {
            throw new RNException(RNException.Tipo.REGISTRO_NAO_ENCONTRADO, "Registro não encontrado");
        }
        return o;
    }

    protected boolean avaliarExcluir(CrudGenerico crudGenerico, Object object) {
        boolean ret = crudGenerico.excluir(object);
        if (!ret) {
            throw new RNException(RNException.Tipo.REGISTRO_NAO_ENCONTRADO, "Registro não encontrado");
        }
        return ret;
    }

}
