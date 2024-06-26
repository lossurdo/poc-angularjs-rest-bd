package com.senac.seriadomodel.infra;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Logger;


/**
 * Exemplo do Design Pattern: Singleton
 * https://sourcemaking.com/design_patterns/singleton
 *
 * @author lossurdo
 */
public class Propriedades {

    private static final Logger logger = Logger.getLogger(Propriedades.class.getName());
    private static Properties prop = null;

    private Propriedades() {
        prop = new Properties();
        try {
            // TENTATIVA 1
            prop.load(this.getClass().getClassLoader().getResourceAsStream("/propriedades.properties"));
            logger.info("Arquivo propriedades.properties lido com sucesso");
        } catch (Exception e1) {
            try {
                // TENTATIVA 2
                prop.load(this.getClass().getClassLoader().getResourceAsStream("propriedades.properties"));
                logger.info("Arquivo propriedades.properties lido com sucesso");
            } catch (Exception e) {
                try {
                    // TENTATIVA 3
                    String propFile = System.getProperty("prop");
                    logger.info("Lendo arquivo propriedades.properties: " + System.getProperty("prop"));
                    FileInputStream fileInputStream = new FileInputStream(propFile);
                    prop.load(fileInputStream);
                    fileInputStream.close();
                    logger.info("Arquivo propriedades.properties lido com sucesso");
                } catch (Exception e3) {
                    throw new RuntimeException("Erro ao carregar arquivo de propriedades", e3);
                }
            }
        }
    }

    public static Propriedades getInstance() {
        return PropriedadeHolder.INSTANCE;
    }

    private static class PropriedadeHolder {
        private static final Propriedades INSTANCE = new Propriedades();
    }

    public String get(String key) {
        return prop.getProperty(key);
    }
}
