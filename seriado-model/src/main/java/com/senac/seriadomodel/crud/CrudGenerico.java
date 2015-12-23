package com.senac.seriadomodel.crud;

public interface CrudGenerico<T> {

    T consultar(T bean);

    boolean excluir(T bean);
    
    T salvar(T bean);    
    
}
