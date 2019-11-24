package inteligenciaartificial.controller;


import inteligenciaartificial.model.Letra;
import inteligenciaartificial.model.LetraDto;
import inteligenciaartificial.utils.BancoDeDados;
import inteligenciaartificial.utils.LetraRanking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MachineLearnController {

    private Connection con;
    
    public MachineLearnController(){
        con = new BancoDeDados().getConnection();
    }

    public boolean inserirML(Letra l){

       boolean retorno = false;
        
        String sql = "INSERT INTO TB_MACHINE_LEARN (letraAnt ,letraPost,letraEscolhida,ic_primeira,ic_ultima,certo ,errado) "
                + "   VALUES('"+l.getLetraAnterior()+"','"+l.getLetraPosterior()+"','"+ l.getLetraEscolhida()+"'"
                + "          ,"+ l.isPrimeira() +","+ l.isUltima()+" ,"+ l.getCerto()+" ,"+ l.getErrado()+""
                + ""
                + ")";
    
        try {
            
            PreparedStatement stm = con.prepareStatement(sql);
            retorno = stm.execute();
            
            stm.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

       return retorno;

    }
    
    public Letra consultarLetra(Letra letra){
        Letra l = new Letra();
        
        String sql = "select TOP 1 * from TB_MACHINE_LEARN \n" +
                     "where 1 = 1\n" +
                     "AND  letraAnt = '"+letra.getLetraAnterior()+"'\n" +
                     "AND  letraPost = '"+letra.getLetraPosterior()+"'\n" +
                     "AND  letraEscolhida = '"+letra.getLetraEscolhida()+"'\n" +
                     "AND  ic_primeira = "+letra.isPrimeira()+"\n" +
                     "AND  ic_ultima = "+letra.isUltima()+"";
        try {
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
            if(rs.next()){
                
                
                l.setId(rs.getInt("id"));
                l.setLetraEscolhida(rs.getString("letraEscolhida"));
                l.setLetraAnterior(rs.getString("letraAnt"));
                l.setLetraPosterior(rs.getString("letraPost"));
                l.setPrimeira(rs.getBoolean("ic_primeira"));
                l.setUltima(rs.getBoolean("ic_ultima"));
                l.setCerto(rs.getInt("certo"));
                l.setErrado(rs.getInt("errado"));
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MachineLearnController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return l;
        
    }
    
        public List<Letra> consultarAllLetra(Letra letra){
        List<Letra> lista = new ArrayList<>();
        
        String sql = "select * from TB_MACHINE_LEARN \n" +
                     "where 1 = 1\n" +
                     "AND letraAnt = '"+letra.getLetraAnterior()+"'\n" +
                     "AND letraPost = '"+letra.getLetraPosterior()+"'\n" +
                     "AND letraEscolhida = '"+letra.getLetraEscolhida()+"'\n" +
                     "AND ic_primeira = "+letra.isPrimeira()+"\n" +
                     "AND ic_ultima = "+letra.isUltima()+"";
        try {
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
            while(rs.next()){
                Letra l = new Letra();
                
                l.setId(rs.getInt("id"));
                l.setLetraEscolhida(rs.getString("letraEscolhida"));
                l.setLetraAnterior(rs.getString("letraAnt"));
                l.setLetraPosterior(rs.getString("letraPost"));
                l.setPrimeira(rs.getBoolean("ic_primeira"));
                l.setUltima(rs.getBoolean("ic_ultima"));
                l.setCerto(rs.getInt("certo"));
                l.setErrado(rs.getInt("errado"));
                
                lista.add(l);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MachineLearnController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
        
    }

    public LetraDto consultarRanking(Letra letra){
        LetraDto l = new LetraDto();

        String sql = "select TOP 1 letraEscolhida,SUM(certo - errado) AS RESULTADO from TB_MACHINE_LEARN \n" +
                     "where 1 = 1\n" +
                     "AND "+letra.getLetraAnterior()+" IS NULL OR letraAnt = '"+letra.getLetraAnterior()+"'\n" +
                     "AND "+letra.getLetraPosterior()+" IS NULL OR letraPost = '"+letra.getLetraPosterior()+"'\n" +
                     "AND "+letra.isPrimeira()+" IS NULL OR ic_primeira = "+letra.isPrimeira()+"\n" +
                     "AND "+letra.isUltima()+" IS NULL OR ic_ultima = "+letra.isUltima()+"\n" +
                     "GROUP BY letraEscolhida\n" +
                     "ORDER BY RESULTADO DESC";
        try {
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
            if(rs.next()){
                
                
                l.setId(rs.getInt("id"));
                l.setLetra(rs.getString("letraEscolhida"));
                l.setResultado(rs.getInt("RESULTADO"));
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MachineLearnController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return l;

    }

    public int qtdeItensCadastrados(){
        int x = 0;

        String sql = "SELECT * FROM TB_MACHINE_LEARN";
        try {
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                
                x++;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return x;
    }

    public boolean alterarLetra(Letra l){

        String sql = "UPDATE TB_MACHINE_LEARN SET "
                + "   letraAnt = '"+ l.getLetraAnterior() +"',"
                + "   letraPost = '"+ l.getLetraPosterior() +"',"
                + "   letraEscolhida = '"+ l.getLetraEscolhida() +"',"
                + "   ic_primeira = "+ l.isPrimeira() +","
                + "   ic_ultima = "+ l.isUltima() +","
                + "   certo = "+l.getCerto()+", "
                + "   errado = " + l.getErrado()+" "
                + "   where id = "+l.getId();
        
        boolean retorno = false;
        
        try {
            
            PreparedStatement stm = con.prepareStatement(sql);
            retorno = stm.execute();
            
            stm.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

       return retorno;
        
    }

//    public List<LetraDto> consultarRankingDeLetras(){
//        List<LetraDto> lista = new ArrayList<>();
//
//        String sql = "select letra, SUM(certo - errado) as Resultado \n" +
//                "from LETRAS\n" +
//                "group by letra\n" +
//                "order by Resultado desc";
//        
//        try {
//        PreparedStatement stm = con.prepareStatement(sql);
//        ResultSet rs = stm.executeQuery();
//        
//        
//    
//            while(rs.next()){
//                
//                LetraDto l = new LetraDto();
//                l.setLetra(rs.getString("letra"));
//                l.setResultado(rs.getInt("resultado"));
//
//                lista.add(l);
//                
//            } 
//        } catch (SQLException ex) {
//            Logger.getLogger(MachineLearnController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        
//
//        return lista;
//    }

}
