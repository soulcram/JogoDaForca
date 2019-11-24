package inteligenciaartificial.controller;

import inteligenciaartificial.model.Letra;
import inteligenciaartificial.utils.EscolherLetra;
import inteligenciaartificial.utils.LetraRanking;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;
import lombok.Setter;

public class InteligenciaArtificialMaquina implements Runnable {

    private JLabel msgInimigo, lPalavraEscondida, lPontos, tamPalavra, palavraCompleta;
    private JPanel painelTeclas;

    @Setter
    private Vida vida;

    @Setter
    private Letras letras;

    private LetraController lc;
    private PontosController pontosC;
    private MachineLearnController mlc;
    Integer danoInimigo = 2, inimigoDaVez, cora = 255, corb = 0, corc = 125;

    @Override
    public void run() {

        lc = new LetraController();
        pontosC = new PontosController();
        mlc = new MachineLearnController();

        while (true) {
            atualizarTeclas();
            painelTeclas.setVisible(VariaveisGlobais.vezJogador);
            lPontos.setText(String.valueOf(VariaveisGlobais.pontos));
            if(VariaveisGlobais.palavraEscondida != null){
                tamPalavra.setText("A palavra tem "+ VariaveisGlobais.palavraEscondida.length +" letras.");
            }
            if (VariaveisGlobais.qtdeVidaPlayer1 <= 0) {
                VariaveisGlobais.gameOver = true;
                mensagem("Eu venci hahaha");
                if (VariaveisGlobais.pontos > pontosC.consultarRecord()) {
                    lPalavraEscondida.setText("Você fez um novo Record");
                    sleep(2000);
                    pontosC.alterarRecord(VariaveisGlobais.pontos);
                } else {

                    lPalavraEscondida.setText("Você Fez: " + VariaveisGlobais.pontos + " pontos.");

                }
            }
            if (!VariaveisGlobais.gameOver) {

                if (!VariaveisGlobais.vezJogador) {
                    vezComputador();
                    verificarVitoriaPalavra();
                }
            }

            atualizarVidaPlayer();
            atualizarVidainimigo();
            
            if(!"".equals(VariaveisGlobais.palavraCorreta)){
                mensagem("Humm !!! Então a palavra era " + VariaveisGlobais.palavraCorreta);
                palavraCompleta.setText("Palavra completa: " + VariaveisGlobais.palavraCorreta);
            }else{
                palavraCompleta.setText("");
            }
            if(VariaveisGlobais.novoInimigo){
                mensagem("Eu derrotarei você!!!");
                VariaveisGlobais.novoInimigo = false;
            }
            
        }

    }

    private void verificarVitoriaPalavra() {
        //char [] teste = pe.getText().toString().toCharArray();
        int x = 0;
        String p = "";
        for (char c : VariaveisGlobais.palavraEscondida) {
            if (c == '_') {
                x++;
            }else{
                p += String.valueOf(c);
            }
        }
        if (x <= 0) {
            mensagem("Então a palavra era " + p);
            palavraCompleta.setText("Palavra completa: " + p);
            sleep(1000);
            gerarNovaPalavra();
        }
    }

    private void gerarNovaPalavra() {
        escolherPalavra();
        lPalavraEscondida.setText(enconderPalavra());

        char[] novoletrasNaoEscolhidas = {'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Ç', 'Z', 'X', 'C', 'V', 'B', 'N', 'M'};
        VariaveisGlobais.letrasNaoEscolhidas = novoletrasNaoEscolhidas;
        VariaveisGlobais.letrasJaEscolhidas = new ArrayList<>();
        atualizarTeclas();

    }

    public String enconderPalavra() {
        VariaveisGlobais.palavraCorreta = "";
        String x = "";
        VariaveisGlobais.arrayPalavra = VariaveisGlobais.p1.getPalavra().toCharArray();
        for (char c : VariaveisGlobais.arrayPalavra) {
            x += "_ ";
        }

        VariaveisGlobais.palavraEscondida = new char[VariaveisGlobais.arrayPalavra.length];
        return x;
    }

    public void escolherPalavra() {
        PalavraController pc = new PalavraController();

        Random rand = new Random();
        int n = rand.nextInt(pc.qtdeItensCadastrados());

        VariaveisGlobais.p1 = pc.consultarByID(n);

    }

    private void atualizarTeclas() {
        if (VariaveisGlobais.letrasJaEscolhidas.contains("Q")) {
            letras.getQ().setVisible(false);
        } else {
            letras.getQ().setVisible(true);
        }
        if (VariaveisGlobais.letrasJaEscolhidas.contains("W")) {
            letras.getW().setVisible(false);
        } else {
            letras.getW().setVisible(true);
        }
        if (VariaveisGlobais.letrasJaEscolhidas.contains("E")) {
            letras.getE().setVisible(false);
        } else {
            letras.getE().setVisible(true);
        }
        if (VariaveisGlobais.letrasJaEscolhidas.contains("R")) {
            letras.getR().setVisible(false);
        } else {
            letras.getR().setVisible(true);
        }
        if (VariaveisGlobais.letrasJaEscolhidas.contains("T")) {
            letras.getT().setVisible(false);
        } else {
            letras.getT().setVisible(true);
        }
        if (VariaveisGlobais.letrasJaEscolhidas.contains("Y")) {
            letras.getY().setVisible(false);
        } else {
            letras.getY().setVisible(true);
        }
        if (VariaveisGlobais.letrasJaEscolhidas.contains("U")) {
            letras.getU().setVisible(false);
        } else {
            letras.getU().setVisible(true);
        }
        if (VariaveisGlobais.letrasJaEscolhidas.contains("I")) {
            letras.getI().setVisible(false);
        } else {
            letras.getI().setVisible(true);
        }
        if (VariaveisGlobais.letrasJaEscolhidas.contains("O")) {
            letras.getO().setVisible(false);
        } else {
            letras.getO().setVisible(true);
        }
        if (VariaveisGlobais.letrasJaEscolhidas.contains("P")) {
            letras.getP().setVisible(false);
        } else {
            letras.getP().setVisible(true);
        }
        if (VariaveisGlobais.letrasJaEscolhidas.contains("A")) {
            letras.getA().setVisible(false);
        } else {
            letras.getA().setVisible(true);
        }
        if (VariaveisGlobais.letrasJaEscolhidas.contains("S")) {
            letras.getS().setVisible(false);
        } else {
            letras.getS().setVisible(true);
        }
        if (VariaveisGlobais.letrasJaEscolhidas.contains("D")) {
            letras.getD().setVisible(false);
        } else {
            letras.getD().setVisible(true);
        }
        if (VariaveisGlobais.letrasJaEscolhidas.contains("F")) {
            letras.getF().setVisible(false);
        } else {
            letras.getF().setVisible(true);
        }
        if (VariaveisGlobais.letrasJaEscolhidas.contains("G")) {
            letras.getG().setVisible(false);
        } else {
            letras.getG().setVisible(true);
        }
        if (VariaveisGlobais.letrasJaEscolhidas.contains("H")) {
            letras.getH().setVisible(false);
        } else {
            letras.getH().setVisible(true);
        }
        if (VariaveisGlobais.letrasJaEscolhidas.contains("J")) {
            letras.getJ().setVisible(false);
        } else {
            letras.getJ().setVisible(true);
        }
        if (VariaveisGlobais.letrasJaEscolhidas.contains("K")) {
            letras.getK().setVisible(false);
        } else {
            letras.getK().setVisible(true);
        }
        if (VariaveisGlobais.letrasJaEscolhidas.contains("L")) {
            letras.getL().setVisible(false);
        } else {
            letras.getL().setVisible(true);
        }
        if (VariaveisGlobais.letrasJaEscolhidas.contains("Ç")) {
            letras.getÇ().setVisible(false);
        } else {
            letras.getÇ().setVisible(true);
        }
        if (VariaveisGlobais.letrasJaEscolhidas.contains("Z")) {
            letras.getZ().setVisible(false);
        } else {
            letras.getZ().setVisible(true);
        }
        if (VariaveisGlobais.letrasJaEscolhidas.contains("X")) {
            letras.getX().setVisible(false);
        } else {
            letras.getX().setVisible(true);
        }
        if (VariaveisGlobais.letrasJaEscolhidas.contains("C")) {
            letras.getC().setVisible(false);
        } else {
            letras.getC().setVisible(true);
        }
        if (VariaveisGlobais.letrasJaEscolhidas.contains("V")) {
            letras.getV().setVisible(false);
        } else {
            letras.getV().setVisible(true);
        }
        if (VariaveisGlobais.letrasJaEscolhidas.contains("B")) {
            letras.getB().setVisible(false);
        } else {
            letras.getB().setVisible(true);
        }
        if (VariaveisGlobais.letrasJaEscolhidas.contains("N")) {
            letras.getN().setVisible(false);
        } else {
            letras.getN().setVisible(true);
        }
        if (VariaveisGlobais.letrasJaEscolhidas.contains("M")) {
            letras.getM().setVisible(false);
        } else {
            letras.getM().setVisible(true);
        }

    }

    private void vezComputador() {
        mensagem("Agora é minha vez.");

        sleep(1000);

        String novoLetrasJaEscolhidas = "";
        for (String s : VariaveisGlobais.letrasJaEscolhidas) {
            novoLetrasJaEscolhidas += s;
        }
        String novaPalavraEscondida = "";
        for (int i = 0; i < VariaveisGlobais.palavraEscondida.length; i++) {
            novaPalavraEscondida += VariaveisGlobais.palavraEscondida[i];
        }
        String teste = VariaveisGlobais.palavraEscondida.toString();

        EscolherLetra el = new EscolherLetra();
        String letraEscolhida = el.definir(novaPalavraEscondida, novoLetrasJaEscolhidas);
        mensagem("Eu escolho a letra " + letraEscolhida);
        VariaveisGlobais.letrasJaEscolhidas.add(letraEscolhida);
        sleep(1500);

        lPalavraEscondida.setText(verificarLetraEscolhaPC(letraEscolhida.charAt(0)));
    }

    public String verificarLetraEscolhaPC(char letra) {
        boolean controle = false;
        String x = "";
        int y = 0;
        boolean hasAcerto = false;
        VariaveisGlobais.arrayPalavra = VariaveisGlobais.p1.getPalavra().toCharArray();

        for (char c : VariaveisGlobais.arrayPalavra) {
            if (c == letra) {
                hasAcerto = true;
                VariaveisGlobais.palavraEscondida[y] = c;
                controle = true;
                VariaveisGlobais.pontos -= 20;
                Letra letraML = new Letra(null, 
                                         y, 
                                        (y == 0 ? null : String.valueOf(VariaveisGlobais.palavraEscondida[y - 1])), 
                                        (y == (VariaveisGlobais.palavraEscondida.length - 1) ? null : String.valueOf(VariaveisGlobais.palavraEscondida[y + 1])),
                                        String.valueOf(letra), 
                                        (y == 0), 
                                        (y == VariaveisGlobais.palavraEscondida.length - 1),
                                         1, 
                                        0);
                
                Letra verificadaML = mlc.consultarLetra(letraML);
                
                if(verificadaML.getId() == null ){
                    mlc.inserirML(letraML);
                }else{
                    if(verificadaML.getCerto() == null){
                        verificadaML.setCerto(0);
                    }
                    verificadaML.setCerto(verificadaML.getCerto() + 1);
                    mlc.alterarLetra(verificadaML);
                }
                

            } else if (c == VariaveisGlobais.palavraEscondida[y]) {
            } else {

                VariaveisGlobais.palavraEscondida[y] = '_';

            }

            y++;

        }

        for (char c : VariaveisGlobais.palavraEscondida) {
            if (c == '_') {
                x += "_ ";
            } else {
                x += c;
            }
        }

        if (hasAcerto) {
            LetraRanking lr = lc.consultarByLetra(String.valueOf(letra));
            lr.setCerto(lr.getCerto() + 1);
            hitPlayer1();
            mensagem("Eu acertei haha");
            sleep(1000);

            lc.alterarLetra(lr);

        } else {
            mensagem("Que pena, eu errei.");
            sleep(500);
            mensagem("É sua vez.");
            LetraRanking lr = lc.consultarByLetra(String.valueOf(letra));
            lr.setErrado(lr.getErrado() + 1);
            lc.alterarLetra(lr);
            if(VariaveisGlobais.letraEscolhidaML != null){
                if(VariaveisGlobais.letraEscolhidaML.getErrado() == null ){
                    VariaveisGlobais.letraEscolhidaML.setErrado(0);
                }
                VariaveisGlobais.letraEscolhidaML.setErrado(VariaveisGlobais.letraEscolhidaML.getErrado() + 1);
                mlc.alterarLetra(VariaveisGlobais.letraEscolhidaML);
            }
            VariaveisGlobais.vezJogador = !VariaveisGlobais.vezJogador;
        }

        return x;

    }

    private void mensagem(final String msg) {
        msgInimigo.setText(msg);
        sleep(1000);
    }

    private void sleep(int tempo) {
        try {
            Thread.sleep(tempo);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    private void hitPlayer1() {

        VariaveisGlobais.qtdeVidaPlayer1 -= 1;

    }

    private void atualizarVidaPlayer() {
        if (VariaveisGlobais.qtdeVidaPlayer1 < 9) {
            vida.getVidaPlayer9().setBackground(Color.red);
        }else{
            vida.getVidaPlayer9().setBackground(Color.yellow);
        }
        
        if (VariaveisGlobais.qtdeVidaPlayer1 < 8) {
            vida.getVidaPlayer8().setBackground(Color.red);
        }else{
            vida.getVidaPlayer8().setBackground(Color.yellow);
        }
        
        if (VariaveisGlobais.qtdeVidaPlayer1 < 7) {
            vida.getVidaPlayer7().setBackground(Color.red);
        }else{
            vida.getVidaPlayer7().setBackground(Color.yellow);
        }
        
        if (VariaveisGlobais.qtdeVidaPlayer1 < 6) {
            vida.getVidaPlayer6().setBackground(Color.red);
        }else{
            vida.getVidaPlayer6().setBackground(Color.yellow);
        }
        
        if (VariaveisGlobais.qtdeVidaPlayer1 < 5) {
            vida.getVidaPlayer5().setBackground(Color.red);
        }else{
            vida.getVidaPlayer5().setBackground(Color.yellow);
        }
        
        if (VariaveisGlobais.qtdeVidaPlayer1 < 4) {
            vida.getVidaPlayer4().setBackground(Color.red);
        }else{
            vida.getVidaPlayer4().setBackground(Color.yellow);
        }
        
        if (VariaveisGlobais.qtdeVidaPlayer1 < 3) {
            vida.getVidaPlayer3().setBackground(Color.red);
        }else{
            vida.getVidaPlayer3().setBackground(Color.yellow);
        }
        
        if (VariaveisGlobais.qtdeVidaPlayer1 < 2) {
            vida.getVidaPlayer2().setBackground(Color.red);
        }else{
            vida.getVidaPlayer2().setBackground(Color.yellow);
        }
        
        if (VariaveisGlobais.qtdeVidaPlayer1 < 1) {
            vida.getVidaPlayer1().setBackground(Color.red);
        }else{
            vida.getVidaPlayer1().setBackground(Color.yellow);
        }
        

    }

    private void atualizarVidainimigo() {
        if (VariaveisGlobais.qtdeVidaPlayer2 < 9) {
            vida.getVidainimigo9().setBackground(Color.red);
        } else {
            vida.getVidainimigo9().setBackground(Color.yellow);
        }

        if (VariaveisGlobais.qtdeVidaPlayer2 < 8) {
            vida.getVidainimigo8().setBackground(Color.red);
        } else {
            vida.getVidainimigo8().setBackground(Color.yellow);
        }

        if (VariaveisGlobais.qtdeVidaPlayer2 < 7) {
            vida.getVidainimigo7().setBackground(Color.red);
        } else {
            vida.getVidainimigo7().setBackground(Color.yellow);
        }

        if (VariaveisGlobais.qtdeVidaPlayer2 < 6) {
            vida.getVidainimigo6().setBackground(Color.red);
        } else {
            vida.getVidainimigo6().setBackground(Color.yellow);
        }

        if (VariaveisGlobais.qtdeVidaPlayer2 < 5) {
            vida.getVidainimigo5().setBackground(Color.red);
        } else {
            vida.getVidainimigo5().setBackground(Color.yellow);
        }
        if (VariaveisGlobais.qtdeVidaPlayer2 < 4) {
            vida.getVidainimigo4().setBackground(Color.red);
        } else {
            vida.getVidainimigo4().setBackground(Color.yellow);
        }

        if (VariaveisGlobais.qtdeVidaPlayer2 < 3) {
            vida.getVidainimigo3().setBackground(Color.red);
        } else {
            vida.getVidainimigo3().setBackground(Color.yellow);
        }

        if (VariaveisGlobais.qtdeVidaPlayer2 < 2) {
            vida.getVidainimigo2().setBackground(Color.red);
        } else {
            vida.getVidainimigo2().setBackground(Color.yellow);
        }

        if (VariaveisGlobais.qtdeVidaPlayer2 < 1) {
            vida.getVidainimigo1().setBackground(Color.red);
        } else {
            vida.getVidainimigo1().setBackground(Color.yellow);
        }

    }

    public InteligenciaArtificialMaquina() {
    }

    public void setMsgInimigo(JLabel msgInimigo) {
        this.msgInimigo = msgInimigo;
    }

    public void setPainelTeclas(JPanel painelTeclas) {
        this.painelTeclas = painelTeclas;
    }

    public void setlPalavraEscondida(JLabel lPalavraEscondida) {
        this.lPalavraEscondida = lPalavraEscondida;
    }

    public void setlPontos(JLabel lPontos) {
        this.lPontos = lPontos;
    }

    public void setTamPalavra(JLabel tamPalavra) {
        this.tamPalavra = tamPalavra;
    }

    public void setPalavraCompleta(JLabel palavraCompleta) {
        this.palavraCompleta = palavraCompleta;
    }
    
    

}
