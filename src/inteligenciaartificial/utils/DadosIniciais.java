package inteligenciaartificial.utils;

import inteligenciaartificial.controller.LetraController;
import inteligenciaartificial.controller.MachineLearnController;
import inteligenciaartificial.controller.PalavraController;
import inteligenciaartificial.controller.PontosController;
import inteligenciaartificial.model.Palavra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DadosIniciais {

    private PalavraController pc;
    private LetraController lc;
    private PontosController pontosC;
    private MachineLearnController mlc;

    public DadosIniciais() {
        pc = new PalavraController();
        lc = new LetraController();
        mlc = new MachineLearnController();
        pontosC = new PontosController();
        if (pc.qtdeItensCadastrados() == 0) {
            criandoTbPalavras();
            cadastrarPalavras();
        }
        if (lc.qtdeItensCadastrados() == 0) {
            criandoTbLetras();
            cadastrarLetra();
        }
        if (pontosC.qtdeItensCadastrados() == 0) {
            criandoTbRecord();
        }
        if (mlc.qtdeItensCadastrados() == 0) {
            criandoTbMachineLearn();
        }
        
        if (pc.qtdeItensCadastrados() < 300) {
            cadastrarNovasPalavras();
        }

    }

    private void criandoTbPalavras() {
        Connection con = new BancoDeDados().getConnection();

        String sqlQuery = "CREATE TABLE PALAVRAS( \n"
                + "   id INT auto_increment, \n"
                + "   palavra VARCHAR(250) NOT NULL\n"
                + ");";

        try {

            PreparedStatement stm = con.prepareStatement(sqlQuery);
            stm.execute();

            stm.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void criandoTbRecord() {
        Connection con = new BancoDeDados().getConnection();

        String sqlQuery = "CREATE TABLE TB_RECORD( \n"
                + "   id INT auto_increment, \n"
                + "   record int NOT NULL\n"
                + ");";

        try {

            PreparedStatement stm = con.prepareStatement(sqlQuery);
            stm.execute();

            stm.close();
            pontosC.inserirPontos(100);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void criandoTbLetras() {
        Connection con = new BancoDeDados().getConnection();

        String sqlQuery = "CREATE TABLE LETRAS( \n"
                + "   id INT auto_increment, \n"
                + "   letra VARCHAR(250) NOT NULL,\n"
                + "   certo bigint not null,\n"
                + "   errado bigint not null\n"
                + ");";

        try {

            PreparedStatement stm = con.prepareStatement(sqlQuery);
            stm.execute();

            stm.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void criandoTbMachineLearn() {
        Connection con = new BancoDeDados().getConnection();

        String sqlQuery = "CREATE TABLE TB_MACHINE_LEARN( \n"
                + "   id INT auto_increment, \n"
                + "   letraAnt VARCHAR(5) NULL,\n"
                + "   letraPost VARCHAR(5) NULL,\n"
                + "   letraEscolhida VARCHAR(2) NULL,\n"
                + "   ic_primeira BIT NULL,\n"
                + "   ic_ultima BIT NULL,\n"
                + "   certo bigint null,\n"
                + "   errado bigint null\n"
                + ");";

        try {

            PreparedStatement stm = con.prepareStatement(sqlQuery);
            stm.execute();

            stm.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void cadastrarLetra() {
        lc.inserirLetra(new LetraRanking(0, "A", 27, 0));
        lc.inserirLetra(new LetraRanking(0, "B", 15, 0));
        lc.inserirLetra(new LetraRanking(0, "C", 16, 0));
        lc.inserirLetra(new LetraRanking(0, "D", 14, 0));
        lc.inserirLetra(new LetraRanking(0, "E", 26, 0));
        lc.inserirLetra(new LetraRanking(0, "F", 17, 0));
        lc.inserirLetra(new LetraRanking(0, "G", 13, 0));
        lc.inserirLetra(new LetraRanking(0, "H", 12, 0));
        lc.inserirLetra(new LetraRanking(0, "I", 25, 0));
        lc.inserirLetra(new LetraRanking(0, "J", 9, 0));
        lc.inserirLetra(new LetraRanking(0, "K", 8, 0));
        lc.inserirLetra(new LetraRanking(0, "L", 18, 0));
        lc.inserirLetra(new LetraRanking(0, "M", 20, 0));
        lc.inserirLetra(new LetraRanking(0, "N", 19, 0));
        lc.inserirLetra(new LetraRanking(0, "O", 24, 0));
        lc.inserirLetra(new LetraRanking(0, "P", 10, 0));
        lc.inserirLetra(new LetraRanking(0, "Q", 7, 0));
        lc.inserirLetra(new LetraRanking(0, "R", 21, 0));
        lc.inserirLetra(new LetraRanking(0, "S", 22, 0));
        lc.inserirLetra(new LetraRanking(0, "T", 11, 0));
        lc.inserirLetra(new LetraRanking(0, "U", 23, 0));
        lc.inserirLetra(new LetraRanking(0, "V", 6, 0));
        lc.inserirLetra(new LetraRanking(0, "W", 3, 0));
        lc.inserirLetra(new LetraRanking(0, "X", 2, 0));
        lc.inserirLetra(new LetraRanking(0, "Y", 4, 0));
        lc.inserirLetra(new LetraRanking(0, "Z", 1, 0));
        lc.inserirLetra(new LetraRanking(0, "Ç", 5, 0));

    }

    private void cadastrarPalavras() {
        pc.inserirPalavra(new Palavra(0, "TOMATE"));
        pc.inserirPalavra(new Palavra(0, "AZEITONA"));
        pc.inserirPalavra(new Palavra(0, "CASA"));
        pc.inserirPalavra(new Palavra(0, "CAMARAO"));
        pc.inserirPalavra(new Palavra(0, "OCEANO"));
        pc.inserirPalavra(new Palavra(0, "ANDAR"));
        pc.inserirPalavra(new Palavra(0, "NADAR"));
        pc.inserirPalavra(new Palavra(0, "EDIFICIO"));
        pc.inserirPalavra(new Palavra(0, "SOBREVIVER"));
        pc.inserirPalavra(new Palavra(0, "COMPUTADOR"));
        pc.inserirPalavra(new Palavra(0, "CARRO"));
        pc.inserirPalavra(new Palavra(0, "MOTO"));
        pc.inserirPalavra(new Palavra(0, "PAREDE"));
        pc.inserirPalavra(new Palavra(0, "TELA"));
        pc.inserirPalavra(new Palavra(0, "RETRATO"));
        pc.inserirPalavra(new Palavra(0, "SOL"));
        pc.inserirPalavra(new Palavra(0, "PRAIA"));
        pc.inserirPalavra(new Palavra(0, "PAO"));
        pc.inserirPalavra(new Palavra(0, "ROTEADOR"));
        pc.inserirPalavra(new Palavra(0, "AMIGOS"));
        pc.inserirPalavra(new Palavra(0, "MERCADO"));
        pc.inserirPalavra(new Palavra(0, "AVIAO"));
        pc.inserirPalavra(new Palavra(0, "ASAS"));
        pc.inserirPalavra(new Palavra(0, "PASSAROS"));
        pc.inserirPalavra(new Palavra(0, "GALINHA"));
        pc.inserirPalavra(new Palavra(0, "PATO"));
        pc.inserirPalavra(new Palavra(0, "CISNE"));
        pc.inserirPalavra(new Palavra(0, "MONTANHA"));
        pc.inserirPalavra(new Palavra(0, "PREDIO"));
        pc.inserirPalavra(new Palavra(0, "CAMPO"));
        pc.inserirPalavra(new Palavra(0, "TRABALHADOR"));
        pc.inserirPalavra(new Palavra(0, "FUNCIONARIO"));
        pc.inserirPalavra(new Palavra(0, "ESTRELA"));
        pc.inserirPalavra(new Palavra(0, "PORTA"));
        pc.inserirPalavra(new Palavra(0, "CHUVEIRO"));
        pc.inserirPalavra(new Palavra(0, "BANHEIRO"));
        pc.inserirPalavra(new Palavra(0, "COZINHA"));
        pc.inserirPalavra(new Palavra(0, "FUTEBOL"));
        pc.inserirPalavra(new Palavra(0, "SEXTAFEIRA"));
        pc.inserirPalavra(new Palavra(0, "QUINTAFEIRA"));
        pc.inserirPalavra(new Palavra(0, "QUARTAFEIRA"));
        pc.inserirPalavra(new Palavra(0, "TERÇAFEIRA"));
        pc.inserirPalavra(new Palavra(0, "SEGUNDAFEIRA"));
        pc.inserirPalavra(new Palavra(0, "DOMINGO"));
        pc.inserirPalavra(new Palavra(0, "SABADO"));
        pc.inserirPalavra(new Palavra(0, "CONTROLADOR"));
        pc.inserirPalavra(new Palavra(0, "CACHORRO"));
        pc.inserirPalavra(new Palavra(0, "PASSARINHO"));
        pc.inserirPalavra(new Palavra(0, "PEIXE"));
        pc.inserirPalavra(new Palavra(0, "SURFISTA"));
        //50
        pc.inserirPalavra(new Palavra(0, "MACONHA"));
        pc.inserirPalavra(new Palavra(0, "CHOCOLATE"));
        pc.inserirPalavra(new Palavra(0, "TERROR"));
        pc.inserirPalavra(new Palavra(0, "FANTASMA"));
        pc.inserirPalavra(new Palavra(0, "LENÇOL"));
        pc.inserirPalavra(new Palavra(0, "FREIRA"));
        pc.inserirPalavra(new Palavra(0, "IGREJA"));
        pc.inserirPalavra(new Palavra(0, "PADRE"));
        pc.inserirPalavra(new Palavra(0, "CAMISETA"));
        pc.inserirPalavra(new Palavra(0, "BLUSA"));
        //60
        pc.inserirPalavra(new Palavra(0, "CELULAR"));
        pc.inserirPalavra(new Palavra(0, "CARTEIRA"));
        pc.inserirPalavra(new Palavra(0, "CARTEIRO"));
        pc.inserirPalavra(new Palavra(0, "MADEIRA"));
        pc.inserirPalavra(new Palavra(0, "MADEREIRA"));
        pc.inserirPalavra(new Palavra(0, "PENDRIVE"));
        pc.inserirPalavra(new Palavra(0, "MOUSE"));
        pc.inserirPalavra(new Palavra(0, "BANDA"));
        pc.inserirPalavra(new Palavra(0, "FERNANDA"));
        pc.inserirPalavra(new Palavra(0, "LIMOEIRO"));
        //70
        pc.inserirPalavra(new Palavra(0, "FIO"));
        pc.inserirPalavra(new Palavra(0, "CABELO"));
        pc.inserirPalavra(new Palavra(0, "CARECA"));
        pc.inserirPalavra(new Palavra(0, "CADEIRA"));
        pc.inserirPalavra(new Palavra(0, "BETERRABA"));
        pc.inserirPalavra(new Palavra(0, "SUCO"));
        pc.inserirPalavra(new Palavra(0, "FREEZER"));
        pc.inserirPalavra(new Palavra(0, "GELADEIRA"));
        pc.inserirPalavra(new Palavra(0, "CINEMA"));
        pc.inserirPalavra(new Palavra(0, "TRAVESSEIRO"));
        pc.inserirPalavra(new Palavra(0, "PACIFICAR"));

    }

    private void cadastrarNovasPalavras() {
        pc.inserirPalavra(new Palavra(0, "IMPRESSORA"));
        pc.inserirPalavra(new Palavra(0, "ESQUERDA"));
        pc.inserirPalavra(new Palavra(0, "DIREITA"));
        pc.inserirPalavra(new Palavra(0, "ENTRADA"));
        pc.inserirPalavra(new Palavra(0, "PAREDE"));
        pc.inserirPalavra(new Palavra(0, "TERRA"));
        pc.inserirPalavra(new Palavra(0, "MARTE"));
        pc.inserirPalavra(new Palavra(0, "ROUPA"));
        pc.inserirPalavra(new Palavra(0, "VESTIDO"));
        pc.inserirPalavra(new Palavra(0, "ESCOLA"));
        pc.inserirPalavra(new Palavra(0, "ESCADA"));
        pc.inserirPalavra(new Palavra(0, "NUMERO"));
        pc.inserirPalavra(new Palavra(0, "AVIAO"));
        pc.inserirPalavra(new Palavra(0, "LUVA"));
        pc.inserirPalavra(new Palavra(0, "CARTEIRA"));
        pc.inserirPalavra(new Palavra(0, "ARMARIO"));
        pc.inserirPalavra(new Palavra(0, "ONIBUS"));
        pc.inserirPalavra(new Palavra(0, "JUPITER"));
        pc.inserirPalavra(new Palavra(0, "SATURNO"));
        pc.inserirPalavra(new Palavra(0, "CAIXA"));
        pc.inserirPalavra(new Palavra(0, "SUPERMERCADO"));
        pc.inserirPalavra(new Palavra(0, "HIPERMERCADO"));
        pc.inserirPalavra(new Palavra(0, "PAPINHA"));
        pc.inserirPalavra(new Palavra(0, "PARCERIA"));
        pc.inserirPalavra(new Palavra(0, "PARTIDA"));
        pc.inserirPalavra(new Palavra(0, "PUBLICA"));
        pc.inserirPalavra(new Palavra(0, "PREFEITURA"));
        pc.inserirPalavra(new Palavra(0, "MUNICIPAL"));
        pc.inserirPalavra(new Palavra(0, "GOVERNO"));
        pc.inserirPalavra(new Palavra(0, "GOVERNADOR"));
        pc.inserirPalavra(new Palavra(0, "PRESIDENTE"));
        pc.inserirPalavra(new Palavra(0, "INTERAGIR"));
        pc.inserirPalavra(new Palavra(0, "MOVER"));
        pc.inserirPalavra(new Palavra(0, "IMOVEL"));
        pc.inserirPalavra(new Palavra(0, "SISTEMA"));
        pc.inserirPalavra(new Palavra(0, "PARTICIPANTES"));
        pc.inserirPalavra(new Palavra(0, "VELHO"));
        pc.inserirPalavra(new Palavra(0, "ARVORE"));
        pc.inserirPalavra(new Palavra(0, "BARRIL"));
        pc.inserirPalavra(new Palavra(0, "ARMA"));
        pc.inserirPalavra(new Palavra(0, "TORRE"));
        pc.inserirPalavra(new Palavra(0, "ILHA"));
        pc.inserirPalavra(new Palavra(0, "INSUFICIENTE"));
        pc.inserirPalavra(new Palavra(0, "VENENO"));
        pc.inserirPalavra(new Palavra(0, "FINO"));
        pc.inserirPalavra(new Palavra(0, "CHAVE"));
        pc.inserirPalavra(new Palavra(0, "FECHADURA"));
        pc.inserirPalavra(new Palavra(0, "MONITOR"));
        pc.inserirPalavra(new Palavra(0, "EQUIPE"));
        pc.inserirPalavra(new Palavra(0, "AJUDA"));
        pc.inserirPalavra(new Palavra(0, "SUPLEMENTOS"));
        pc.inserirPalavra(new Palavra(0, "EXIBIR"));
        pc.inserirPalavra(new Palavra(0, "INSERIR"));
        pc.inserirPalavra(new Palavra(0, "DADOS"));
        pc.inserirPalavra(new Palavra(0, "NORMAL"));
        pc.inserirPalavra(new Palavra(0, "FORMATAR"));
        pc.inserirPalavra(new Palavra(0, "FORMATURA"));
        pc.inserirPalavra(new Palavra(0, "PINCEL"));
        pc.inserirPalavra(new Palavra(0, "BOMBA"));
        pc.inserirPalavra(new Palavra(0, "FACULDADE"));
        pc.inserirPalavra(new Palavra(0, "MOBILIDADE"));
        pc.inserirPalavra(new Palavra(0, "SUPLIMENTOS"));
        pc.inserirPalavra(new Palavra(0, "ARQUIVO"));
        pc.inserirPalavra(new Palavra(0, "PERMITE"));
        pc.inserirPalavra(new Palavra(0, "FINANÇAS"));
        pc.inserirPalavra(new Palavra(0, "HOMEM"));
        pc.inserirPalavra(new Palavra(0, "MULHER"));
        pc.inserirPalavra(new Palavra(0, "TEMPO"));
        pc.inserirPalavra(new Palavra(0, "SENHOR"));
        pc.inserirPalavra(new Palavra(0, "SENHORA"));
        pc.inserirPalavra(new Palavra(0, "ABACAXI"));
        pc.inserirPalavra(new Palavra(0, "MANGA"));
        pc.inserirPalavra(new Palavra(0, "LARANJA"));
        pc.inserirPalavra(new Palavra(0, "ABACATE"));
        pc.inserirPalavra(new Palavra(0, "ABEZERRAR"));
        pc.inserirPalavra(new Palavra(0, "AVICULTURA"));
        pc.inserirPalavra(new Palavra(0, "ABISMO"));
        pc.inserirPalavra(new Palavra(0, "DITADURA"));
        pc.inserirPalavra(new Palavra(0, "NEGAR"));
        pc.inserirPalavra(new Palavra(0, "ABDUZIR"));
        pc.inserirPalavra(new Palavra(0, "ABRASIVO"));
        pc.inserirPalavra(new Palavra(0, "ABREVIAR"));
        pc.inserirPalavra(new Palavra(0, "SACUDIR"));
        pc.inserirPalavra(new Palavra(0, "ABUTRES"));
        pc.inserirPalavra(new Palavra(0, "ACADEMIA"));
        pc.inserirPalavra(new Palavra(0, "ACALMAR"));
        pc.inserirPalavra(new Palavra(0, "ACANHADO"));
        pc.inserirPalavra(new Palavra(0, "AGASALHAR"));
        pc.inserirPalavra(new Palavra(0, "ACEROLA"));
        pc.inserirPalavra(new Palavra(0, "SENSIBILIDADE"));
        pc.inserirPalavra(new Palavra(0, "CROCODILO"));
        pc.inserirPalavra(new Palavra(0, "GIRAFA"));
        pc.inserirPalavra(new Palavra(0, "ELEFANTE"));
        pc.inserirPalavra(new Palavra(0, "HIENA"));
        pc.inserirPalavra(new Palavra(0, "MACACO"));
        pc.inserirPalavra(new Palavra(0, "CACHORRO"));
        pc.inserirPalavra(new Palavra(0, "GATO"));
        pc.inserirPalavra(new Palavra(0, "PASSARINHO"));
        pc.inserirPalavra(new Palavra(0, "PARDAL"));
        pc.inserirPalavra(new Palavra(0, "URUBU"));
        pc.inserirPalavra(new Palavra(0, "URUTU"));
        pc.inserirPalavra(new Palavra(0, "CROMOSSOMOS"));
        pc.inserirPalavra(new Palavra(0, "AÇUCAR"));
        pc.inserirPalavra(new Palavra(0, "CHUCHU"));
        pc.inserirPalavra(new Palavra(0, "CURADO"));
        pc.inserirPalavra(new Palavra(0, "ACIDENTE"));
        pc.inserirPalavra(new Palavra(0, "DESASTRE"));
        pc.inserirPalavra(new Palavra(0, "CABEÇA"));
        pc.inserirPalavra(new Palavra(0, "ADELAIDE"));
        pc.inserirPalavra(new Palavra(0, "ADIVINHAR"));
        pc.inserirPalavra(new Palavra(0, "ADIVINHOSA"));
        pc.inserirPalavra(new Palavra(0, "DURAR"));
        pc.inserirPalavra(new Palavra(0, "DURADOURO"));
        pc.inserirPalavra(new Palavra(0, "BEBEDOURO"));
        pc.inserirPalavra(new Palavra(0, "ADOTAR"));
        pc.inserirPalavra(new Palavra(0, "ADOTADO"));
        pc.inserirPalavra(new Palavra(0, "ADQUIRIR"));
        pc.inserirPalavra(new Palavra(0, "ADRIANO"));
        pc.inserirPalavra(new Palavra(0, "DOCENTE"));
        pc.inserirPalavra(new Palavra(0, "ADULAR"));
        pc.inserirPalavra(new Palavra(0, "BAJULAR"));
        pc.inserirPalavra(new Palavra(0, "MELODRAMA"));
        pc.inserirPalavra(new Palavra(0, "MENOR"));
        pc.inserirPalavra(new Palavra(0, "MEMORIAL"));
        pc.inserirPalavra(new Palavra(0, "MALANDRO"));
        pc.inserirPalavra(new Palavra(0, "MELIANTE"));
        pc.inserirPalavra(new Palavra(0, "MEDROSO"));
        pc.inserirPalavra(new Palavra(0, "MATAR"));
        pc.inserirPalavra(new Palavra(0, "METABOLISMO"));
        pc.inserirPalavra(new Palavra(0, "MENTIRA"));
        pc.inserirPalavra(new Palavra(0, "CADEIA"));
        pc.inserirPalavra(new Palavra(0, "MATILHA"));
        pc.inserirPalavra(new Palavra(0, "MERIDIANO"));
        pc.inserirPalavra(new Palavra(0, "CONTOS"));
        pc.inserirPalavra(new Palavra(0, "FADA"));
        pc.inserirPalavra(new Palavra(0, "MESADA"));
        pc.inserirPalavra(new Palavra(0, "MESCLAR"));
        pc.inserirPalavra(new Palavra(0, "RESENHA"));
        pc.inserirPalavra(new Palavra(0, "MESINHA"));
        pc.inserirPalavra(new Palavra(0, "MACHISMO"));
        pc.inserirPalavra(new Palavra(0, "FEMINISMO"));
        pc.inserirPalavra(new Palavra(0, "MINORIA"));
        pc.inserirPalavra(new Palavra(0, "BAHIA"));
        pc.inserirPalavra(new Palavra(0, "TRICOLOR"));
        pc.inserirPalavra(new Palavra(0, "PORCO"));
        pc.inserirPalavra(new Palavra(0, "RETARDADO"));
        pc.inserirPalavra(new Palavra(0, "GENOMA"));
        pc.inserirPalavra(new Palavra(0, "ESTUDAR"));
        pc.inserirPalavra(new Palavra(0, "APRENDER"));
        pc.inserirPalavra(new Palavra(0, "CRESCER"));
        pc.inserirPalavra(new Palavra(0, "DESENVOLVER"));
        pc.inserirPalavra(new Palavra(0, "MARCOS"));
        pc.inserirPalavra(new Palavra(0, "MARIANA"));
        pc.inserirPalavra(new Palavra(0, "JORGE"));
        pc.inserirPalavra(new Palavra(0, "ALBERTO"));
        pc.inserirPalavra(new Palavra(0, "PAULO"));
        pc.inserirPalavra(new Palavra(0, "RANGEL"));
        pc.inserirPalavra(new Palavra(0, "WANDERLEY"));
        pc.inserirPalavra(new Palavra(0, "GUILHERME"));
        pc.inserirPalavra(new Palavra(0, "MEXIRICA"));
        pc.inserirPalavra(new Palavra(0, "MIGALHAS"));
        pc.inserirPalavra(new Palavra(0, "MILHO"));
        pc.inserirPalavra(new Palavra(0, "MILITAR"));
        pc.inserirPalavra(new Palavra(0, "SINUCA"));
        pc.inserirPalavra(new Palavra(0, "MINEIRO"));
        pc.inserirPalavra(new Palavra(0, "BAIHANO"));
        pc.inserirPalavra(new Palavra(0, "MINEIRAR"));
        pc.inserirPalavra(new Palavra(0, "CONHECER"));
        pc.inserirPalavra(new Palavra(0, "ABUSADO"));
        pc.inserirPalavra(new Palavra(0, "FORMATAR"));
        pc.inserirPalavra(new Palavra(0, "FONE"));
        pc.inserirPalavra(new Palavra(0, "OUVIDO"));
        pc.inserirPalavra(new Palavra(0, "MICROFONE"));
        pc.inserirPalavra(new Palavra(0, "SERROTE"));
        pc.inserirPalavra(new Palavra(0, "SERRA"));
        pc.inserirPalavra(new Palavra(0, "AUTOMATICAMENTE"));
        pc.inserirPalavra(new Palavra(0, "LIBERTADORES"));
        pc.inserirPalavra(new Palavra(0, "BANHEIRA"));
        pc.inserirPalavra(new Palavra(0, "FAMOSOS"));
        pc.inserirPalavra(new Palavra(0, "HOMENAGEM"));
        pc.inserirPalavra(new Palavra(0, "HOMENAGENS"));
        pc.inserirPalavra(new Palavra(0, "CARREIRA"));
        pc.inserirPalavra(new Palavra(0, "CARTA"));
        pc.inserirPalavra(new Palavra(0, "MORTE"));
        pc.inserirPalavra(new Palavra(0, "ESPERA"));
        pc.inserirPalavra(new Palavra(0, "DESFECHOS"));
        pc.inserirPalavra(new Palavra(0, "ELENCO"));
        pc.inserirPalavra(new Palavra(0, "DESPEDIDA"));
        pc.inserirPalavra(new Palavra(0, "INICIOU"));
        pc.inserirPalavra(new Palavra(0, "LIBERTAR"));
        pc.inserirPalavra(new Palavra(0, "DESCONTO"));
        pc.inserirPalavra(new Palavra(0, "DIAGNOSTICADO"));
        pc.inserirPalavra(new Palavra(0, "SUCESSO"));
        pc.inserirPalavra(new Palavra(0, "PARTICIPA"));
        pc.inserirPalavra(new Palavra(0, "FATIADA"));
        pc.inserirPalavra(new Palavra(0, "GARANTIA"));
        pc.inserirPalavra(new Palavra(0, "URUGUAI"));
        pc.inserirPalavra(new Palavra(0, "ALEMANHA"));
        pc.inserirPalavra(new Palavra(0, "HOLANDA"));
        pc.inserirPalavra(new Palavra(0, "FRANÇA"));
        pc.inserirPalavra(new Palavra(0, "ESPANHA"));
        pc.inserirPalavra(new Palavra(0, "PORTUGAL"));
        pc.inserirPalavra(new Palavra(0, "BRASIL"));
        pc.inserirPalavra(new Palavra(0, "PALMEIRAS"));
        pc.inserirPalavra(new Palavra(0, "CORINTHIANS"));
        pc.inserirPalavra(new Palavra(0, "RELACIONADO"));
        pc.inserirPalavra(new Palavra(0, "DECISIVA"));
        pc.inserirPalavra(new Palavra(0, "DICISIVO"));
        pc.inserirPalavra(new Palavra(0, "ASSUSTADOR"));
        pc.inserirPalavra(new Palavra(0, "FILME"));
        pc.inserirPalavra(new Palavra(0, "CASAMENTO"));
        pc.inserirPalavra(new Palavra(0, "PEDIDO"));
        pc.inserirPalavra(new Palavra(0, "HEMATOMAS"));
        pc.inserirPalavra(new Palavra(0, "CIUMENTO"));
        pc.inserirPalavra(new Palavra(0, "PILOTO"));
        pc.inserirPalavra(new Palavra(0, "LIVRO"));
        pc.inserirPalavra(new Palavra(0, "CADERNO"));
        pc.inserirPalavra(new Palavra(0, "MISSIONARIOS"));
        pc.inserirPalavra(new Palavra(0, "RECORDE"));
        pc.inserirPalavra(new Palavra(0, "COMPORTAMENTO"));
        pc.inserirPalavra(new Palavra(0, "INCENTIVAR"));
        pc.inserirPalavra(new Palavra(0, "TREINADOR"));
        pc.inserirPalavra(new Palavra(0, "MELHOR"));
        pc.inserirPalavra(new Palavra(0, "CAMINHADA"));
        pc.inserirPalavra(new Palavra(0, "CICLISMO"));
        pc.inserirPalavra(new Palavra(0, "CONSUMO"));
        pc.inserirPalavra(new Palavra(0, "PRECOCE"));
        pc.inserirPalavra(new Palavra(0, "CIGARRO"));
        pc.inserirPalavra(new Palavra(0, "ESPECIALISTAS"));
        pc.inserirPalavra(new Palavra(0, "TRATAREMOS"));
        pc.inserirPalavra(new Palavra(0, "EXPERIENTE"));
        pc.inserirPalavra(new Palavra(0, "VENCEDOR"));
        pc.inserirPalavra(new Palavra(0, "MOREIRA"));
        pc.inserirPalavra(new Palavra(0, "NEGOCIA"));
        pc.inserirPalavra(new Palavra(0, "MOTORISTA"));
        pc.inserirPalavra(new Palavra(0, "JOVEM"));
        pc.inserirPalavra(new Palavra(0, "ADULTO"));
        pc.inserirPalavra(new Palavra(0, "CAINDO"));
        pc.inserirPalavra(new Palavra(0, "JUSTIÇA"));
        pc.inserirPalavra(new Palavra(0, "JUSTICEIRO"));
        pc.inserirPalavra(new Palavra(0, "ATLETISMO"));
        pc.inserirPalavra(new Palavra(0, "BASQUETE"));
        pc.inserirPalavra(new Palavra(0, "FUTEBOL"));
        pc.inserirPalavra(new Palavra(0, "CAMPEONATO"));
        pc.inserirPalavra(new Palavra(0, "CARTOLA"));
        pc.inserirPalavra(new Palavra(0, "INTERNACIONAL"));
        pc.inserirPalavra(new Palavra(0, "CONCURSOS"));
        pc.inserirPalavra(new Palavra(0, "EMPREGOS"));
        pc.inserirPalavra(new Palavra(0, "MUNDO"));
        pc.inserirPalavra(new Palavra(0, "PERNAMBUCO"));
        pc.inserirPalavra(new Palavra(0, "PLANETA"));
        pc.inserirPalavra(new Palavra(0, "UNIVERSO"));
        pc.inserirPalavra(new Palavra(0, "TECNOLOGIA"));
        pc.inserirPalavra(new Palavra(0, "TURISMO"));
        pc.inserirPalavra(new Palavra(0, "VIAGEM"));
        pc.inserirPalavra(new Palavra(0, "TEATRO"));
        pc.inserirPalavra(new Palavra(0, "PAPARAZZO"));
        pc.inserirPalavra(new Palavra(0, "MESTRE"));
        pc.inserirPalavra(new Palavra(0, "CONOSCO"));
        pc.inserirPalavra(new Palavra(0, "TRABALHE"));
        pc.inserirPalavra(new Palavra(0, "COMPARTILHEI"));
        pc.inserirPalavra(new Palavra(0, "PRAZER"));
        pc.inserirPalavra(new Palavra(0, "FRESCA"));
        pc.inserirPalavra(new Palavra(0, "BILHETINHO"));
        pc.inserirPalavra(new Palavra(0, "RECUPERAR"));
        pc.inserirPalavra(new Palavra(0, "QUILOS"));
        pc.inserirPalavra(new Palavra(0, "PERDER"));
        pc.inserirPalavra(new Palavra(0, "VIVO"));
        pc.inserirPalavra(new Palavra(0, "BRINCAR"));
        pc.inserirPalavra(new Palavra(0, "JOGAR"));
        pc.inserirPalavra(new Palavra(0, "MINHA"));
        pc.inserirPalavra(new Palavra(0, "CONTA"));
        pc.inserirPalavra(new Palavra(0, "ADIVINHAR"));
        pc.inserirPalavra(new Palavra(0, "ATRASADO"));
        pc.inserirPalavra(new Palavra(0, "PASSANDO"));
        pc.inserirPalavra(new Palavra(0, "BARULHO"));
        pc.inserirPalavra(new Palavra(0, "ABAFANDO"));
        pc.inserirPalavra(new Palavra(0, "BODE"));
        pc.inserirPalavra(new Palavra(0, "HUMANOS"));
        pc.inserirPalavra(new Palavra(0, "ANIMAL"));
        pc.inserirPalavra(new Palavra(0, "ANIMAIS"));
        pc.inserirPalavra(new Palavra(0, "HUMANO"));
        pc.inserirPalavra(new Palavra(0, "ETERNO"));
        pc.inserirPalavra(new Palavra(0, "CHORANDO"));
        pc.inserirPalavra(new Palavra(0, "VELOCIDADE"));
        pc.inserirPalavra(new Palavra(0, "SOBRINHO"));
        pc.inserirPalavra(new Palavra(0, "PESSOA"));
        pc.inserirPalavra(new Palavra(0, "DECIFRAR"));
        pc.inserirPalavra(new Palavra(0, "LEVANTAR"));
        pc.inserirPalavra(new Palavra(0, "QUANDO"));
        pc.inserirPalavra(new Palavra(0, "INTROVERTIDOS"));
        pc.inserirPalavra(new Palavra(0, "INFERNO"));
        pc.inserirPalavra(new Palavra(0, "PRESO"));
        pc.inserirPalavra(new Palavra(0, "PARCELAMENTO"));
        pc.inserirPalavra(new Palavra(0, "PARLAMENTO"));
        pc.inserirPalavra(new Palavra(0, "REFLITA"));
        pc.inserirPalavra(new Palavra(0, "PREFERIR"));
        pc.inserirPalavra(new Palavra(0, "CHUTAR"));
        pc.inserirPalavra(new Palavra(0, "COSTELA"));
        pc.inserirPalavra(new Palavra(0, "COSTELETA"));
        pc.inserirPalavra(new Palavra(0, "BANDIDO"));
        pc.inserirPalavra(new Palavra(0, "DINHEIRO"));
        pc.inserirPalavra(new Palavra(0, "ESCLARECIMENTO"));
        pc.inserirPalavra(new Palavra(0, "APRESENTADORES"));
        pc.inserirPalavra(new Palavra(0, "MELHORES"));
        pc.inserirPalavra(new Palavra(0, "PERGUNTANDO"));

    }

}
