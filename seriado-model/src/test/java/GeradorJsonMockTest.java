
import com.google.gson.Gson;
import com.senac.seriadomodel.bean.Genero;
import com.senac.seriadomodel.bean.Seriado;
import java.util.Arrays;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lossurdo
 */
public class GeradorJsonMockTest {

    @Test
    public void gerar() {
        Genero g1 = new Genero(1);
        g1.setGenero("Adventure");
        Genero g2 = new Genero(2);
        g2.setGenero("Sci-Fi");
        Genero g3 = new Genero(3);
        g3.setGenero("Crime");
        Genero g4 = new Genero(4);
        g4.setGenero("Fantasy");
        Genero g5 = new Genero(5);
        g5.setGenero("Drama");
        Genero g6 = new Genero(5);
        g6.setGenero("Mystery");
        Genero g7 = new Genero(5);
        g7.setGenero("Thriller");
        Genero g8 = new Genero(5);
        g8.setGenero("Action");
        
        Seriado s1 = new Seriado(1);
        s1.setGeneros(Arrays.asList(g1, g5, g4));
        s1.setElenco("Peter Dinklage, Lena Headey, Emilia Clarke, Kit Harington");
        s1.setAno(2011);
        s1.setCriadores("David Benioff, D.B. Weiss");
        s1.setTitulo("Game of Thrones");
        s1.setNota(9.5);
        s1.setSumario("Several noble families fight for control of the mythical land of Westeros.");

        Seriado s2 = new Seriado(2);
        s2.setGeneros(Arrays.asList(g5, g6, g7));
        s2.setElenco("Claire Danes, Mandy Patinkin, Rupert Friend, Damian Lewis");
        s2.setAno(2011);
        s2.setCriadores("Alex Gansa, Howard Gordon");
        s2.setTitulo("Homeland");
        s2.setNota(8.4);
        s2.setSumario("When Marine Nicolas Brody is hailed as a hero after he returns home from eight years of captivity in Iraq, intelligence officer Carrie Mathison is the only one who suspects that he may have been turned.");
        
        Seriado s3 = new Seriado(3);
        s3.setGeneros(Arrays.asList(g5, g6, g2));
        s3.setElenco("Gillian Anderson, David Duchovny, Mitch Pileggi");
        s3.setAno(1993);
        s3.setFinalizado(true);
        s3.setCriadores("Chris Carter");
        s3.setTitulo("The X-Files");
        s3.setNota(8.7);
        s3.setSumario("Two FBI agents, Fox Mulder the believer and Dana Scully the skeptic, investigate the strange and unexplained while hidden forces work to impede their efforts.");
        
        Gson g = new Gson();
        System.out.println(g.toJson(Arrays.asList(g1,g2,g3,g4,g5,g6,g7,g8)));
        System.out.println(g.toJson(Arrays.asList(s1, s2, s3)));
    }
    
}
