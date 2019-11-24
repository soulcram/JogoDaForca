package inteligenciaartificial.utils;

import inteligenciaartificial.controller.LetraController;
import inteligenciaartificial.controller.MachineLearnController;
import inteligenciaartificial.controller.VariaveisGlobais;
import inteligenciaartificial.model.Letra;
import inteligenciaartificial.model.LetraDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EscolherLetra {

    private String letrasJaEscolhidas;
    private int tamanhoDaPalavra;
    private String letra;
    private List<Letra> letrasADecifrar;
    private List<Letra> letrasADecifrarML;
    private List<Letra> possiveisEscolhasML;
    private List<String> palavraDesmembrada;
    private List<LetraDto> rankingLetras;
    private List<LetraDto> rankingMachineLearn;
    private Letra letraEscolhidaML;
    private String possiveisEscolhas = "";
    private String possiveisEscolhasFiltradas = "";

    private LetraController lc;
    private MachineLearnController mlc;

    private final String ALFABETO = "ABCÇDEFGHIJKLMNOPQRSTUVWXYZ";

    public String definir(String palavra, String letrasJaEscolhidas) {
        VariaveisGlobais.letraEscolhidaML = null;
        if (naoEstaCompleta(palavra)) {

            lc = new LetraController();
            mlc = new MachineLearnController();
            rankingLetras = lc.consultarRankingDeLetras();

            this.letrasJaEscolhidas = letrasJaEscolhidas;
            this.tamanhoDaPalavra = palavra.length();
            this.letrasADecifrar = new ArrayList<>();
            this.palavraDesmembrada = new ArrayList<>();

            desmembrarPalavra(palavra);

            carregarMachineLearn();
            if (rankingMachineLearn != null) {
                for (LetraDto l : rankingMachineLearn) {
                    if (possiveisEscolhasFiltradas.contains(l.getLetra())) {
                        System.out.println("letraEscolhidaPeloComputador: " + l.getLetra());
                        return l.getLetra();
                    }
                }
            }

            escolha();
            //System.out.println("Ranking de letras" + lc.consultarRankingDeLetras().toString());
            filtrarEscolhas();
            for (LetraDto l : rankingLetras) {
                if (possiveisEscolhasFiltradas.contains(l.getLetra())) {

                    if (letraEscolhidaML == null || l.getResultado() > (letraEscolhidaML.getCerto() - letraEscolhidaML.getErrado())) {
                        System.out.println("letraEscolhidaPeloComputador: " + l.getLetra());
                        return l.getLetra();
                    } else {
                        System.out.println("letraEscolhidaPeloComputador: " + letraEscolhidaML.getLetraEscolhida());
                        System.out.println("letraEscolhidaPeloComputador foi MachineLearn: " + letraEscolhidaML);
                        VariaveisGlobais.letraEscolhidaML = letraEscolhidaML;
                        return letraEscolhidaML.getLetraEscolhida();
                    }
                }
            }
            System.out.println(possiveisEscolhasFiltradas.toString());
            Random r = new Random();
            int index = r.nextInt(possiveisEscolhasFiltradas.length());
            System.out.println(index);
            letra = possiveisEscolhasFiltradas.substring(index, index + 1);
            System.out.println(letra);
            return this.letra;
        } else {
            System.err.println("A palavra esta completa");
            return "A";
        }
    }

    private void desmembrarPalavra(String palavra) {

        for (int i = 0; i < tamanhoDaPalavra; i++) {

            palavraDesmembrada.add(palavra.substring(i, i + 1));
        }
        int x = 0;
        for (String s : palavraDesmembrada) {

            if (x == 0) {
                if ("_".equals(s)) {
                    Letra l = new Letra(x, null, palavraDesmembrada.get(x + 1));
                    l.setPrimeira(true);
                    l.setUltima(false);
                    letrasADecifrar.add(l);
                }

            } else if (x == tamanhoDaPalavra - 1) {
                if ("_".equals(s)) {
                    Letra l = new Letra(x, palavraDesmembrada.get(x - 1), null);
                    l.setPrimeira(false);
                    l.setUltima(true);
                    letrasADecifrar.add(l);
                }
            } else {
                if ("_".equals(s)) {
                    Letra l = new Letra(x, palavraDesmembrada.get(x - 1), palavraDesmembrada.get(x + 1));
                    l.setPrimeira(false);
                    l.setUltima(false);
                    letrasADecifrar.add(l);
                }
            }
            x++;

        }

    }

    private void escolha() {
        for (Letra l : letrasADecifrar) {
            regras(l);
        }
    }

    private void regras(Letra l) {

        if (l.isPrimeira()) {
            if (ehConsoante(l.getLetraPosterior())) {
                if (ehR(l.getLetraPosterior())) {
                    possiveisEscolhas += ("AEIOUBPTDFGC");
                } else if (ehS(l.getLetraPosterior())) {
                    possiveisEscolhas += ("AEIOUP");
                } else {
                    possiveisEscolhas += ("AEIOU");
                }
            }

            if (ehVogal(l.getLetraPosterior())) {
                possiveisEscolhas += ("QWRTYPSDFGHJKLZXCVBNMÇ");
            }

        } else if (l.isUltima()) {
            if (ehConsoante(l.getLetraAnterior())) {
                possiveisEscolhas += ("AEIOU");
            } else if (ehVogal(l.getLetraAnterior())) {
                possiveisEscolhas += ("MRSAEIOU");
            }
        } else {
            if (ehConsoante(l.getLetraAnterior()) && ehConsoante(l.getLetraPosterior())) {
                possiveisEscolhas += ("AEIOU");
            } else if (ehVogal(l.getLetraAnterior()) && ehVogal(l.getLetraPosterior())) {
                possiveisEscolhas += ("QWRTYPSDFGHJKLZXCVBNMÇAEIOU");
            } else if (ehConsoante(l.getLetraAnterior()) && ehVogal(l.getLetraPosterior())) {
                if (ehS(l.getLetraAnterior())) {
                    possiveisEscolhas += ("AEIOUTPQFGHCVBMN");
                } else if (ehR(l.getLetraAnterior())) {
                    possiveisEscolhas += ("AEIOUGQTPDFGCLSVÇN");
                } else if (ehM(l.getLetraAnterior())) {
                    possiveisEscolhas += ("AEIOUPB");
                } else {
                    possiveisEscolhas += (ALFABETO);
                }
            } else if (ehVogal(l.getLetraAnterior()) && ehConsoante(l.getLetraPosterior())) {
                possiveisEscolhas += ("RTUIEOAPSDFGJLXCBNM");
            } else {
                possiveisEscolhas += (ALFABETO);
            }
        }

    }

    private void filtrarEscolhas() {
        for (int i = 0; i < possiveisEscolhas.length(); i++) {
            if (!letrasJaEscolhidas.contains(possiveisEscolhas.substring(i, i + 1)) && !possiveisEscolhasFiltradas.contains(possiveisEscolhas.substring(i, i + 1))) {
                possiveisEscolhasFiltradas += possiveisEscolhas.substring(i, i + 1);
            }
        }
    }

    private boolean ehConsoante(String s) {
        return "BCDFGHJKLMNPQRSTVWYXZÇ".contains(s);
    }

    private boolean ehVogal(String s) {
        return "AEIOU".contains(s);
    }

    private boolean ehRorS(String s) {
        return "RS".contains(s);
    }

    private boolean ehPorB(String s) {
        return "PB".contains(s);
    }

    private boolean ehR(String s) {
        return "R".contains(s);
    }

    private boolean ehS(String s) {
        return "S".contains(s);
    }

    private boolean ehM(String s) {
        return "M".contains(s);
    }

    private boolean naoEstaCompleta(String palavra) {

        return palavra.contains("_");
    }

    private void carregarMachineLearn() {
        for (Letra l : letrasADecifrar) {
            letrasADecifrarML = mlc.consultarAllLetra(l);
            for (Letra l1 : letrasADecifrarML) {
                if (!l1.getLetraEscolhida().contains(letrasJaEscolhidas)) {
                    possiveisEscolhasML.add(l1);
                }
            }

        }
        if (possiveisEscolhasML != null) {
            for (Letra l : possiveisEscolhasML) {
                if (letraEscolhidaML == null) {
                    letraEscolhidaML = l;
                }

                if ((letraEscolhidaML.getCerto() - letraEscolhidaML.getErrado()) < (l.getCerto() - l.getErrado())) {
                    letraEscolhidaML = l;
                }
            }
        }
    }

}
