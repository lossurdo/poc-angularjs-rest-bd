package com.senac.seriadomodel.auth;

/**
 *
 * @author lossurdo
 */
public class TokenControl {

    //private static final TreeSet<
    
    private TokenControl() {
    }
    
    public static TokenControl getInstance() {
        return TokenControlHolder.INSTANCE;
    }
    
    private static class TokenControlHolder {

        private static final TokenControl INSTANCE = new TokenControl();
    }
}
