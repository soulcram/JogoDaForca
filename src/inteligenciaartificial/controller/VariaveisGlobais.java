package inteligenciaartificial.controller;

import inteligenciaartificial.model.Letra;
import inteligenciaartificial.model.Palavra;
import java.util.ArrayList;
import java.util.List;

public class VariaveisGlobais {


    public static char[] letrasNaoEscolhidas = {'Q','W','E','R','T','Y','U','I','O','P','A','S','D','F','G','H','J','K','L','Ç','Z','X','C','V','B','N','M'},palavraEscondida,arrayPalavra;

    public static List<String> letrasJaEscolhidas = new ArrayList<>();

    public static Integer pontos = 0,qtdeVidaPlayer1 = 9,qtdeVidaPlayer2 = 9;

    public static boolean vezJogador = true,gameOver = false,novoInimigo = false;

    public static String palavra,pe,atualLetraEscolhida,palavraCorreta = "";

    public static Palavra p1;
    
    public static Letra letraEscolhidaML;
    




    
    public static void reiniciarVariaveis(){
        pontos = 0;
        qtdeVidaPlayer1 = 9;
        qtdeVidaPlayer2 = 9;
        char[] letrasNaoEscolhidasNovo = {'Q','W','E','R','T','Y','U','I','O','P','A','S','D','F','G','H','J','K','L','Ç','Z','X','C','V','B','N','M'};
        letrasNaoEscolhidas = letrasNaoEscolhidasNovo;
        vezJogador = true;
        gameOver = false;
        letrasJaEscolhidas = new ArrayList<>();
        
    }
}
