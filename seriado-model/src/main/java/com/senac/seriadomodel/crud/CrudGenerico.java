package com.senac.seriadomodel.crud;

import java.util.List;

public interface CrudGenerico<T> {

    T consultar(T bean);

    boolean excluir(T bean);
    
    T salvar(T bean);    
    
    // métodos válidos para as pesquisas REST
    List<T> pesquisar(T bean);    
    List<T> pesquisar(String valor);
    
}