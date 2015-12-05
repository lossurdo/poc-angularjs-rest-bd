package com.senac.seriadomodel.infra;

/**
 *
 * @author lossurdo
 */
public interface ValidacaoRN<T> {

    void validarSalvar(T bean);
    void validarExcluir(T bean);
    void validarConsultar(T bean);
    void validarAlterar(T bean);
    
}
