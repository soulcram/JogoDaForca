package inteligenciaartificial.controller;


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


public class LetraController {

    private Connection con;
    
    public LetraController(){
        con = new BancoDeDados().getConnection();
    }

    public boolean inserirLetra(LetraRanking l){

       boolean retorno = false;
        
        String sql = "INSERT INTO LETRAS (letra ,certo ,errado) VALUES('"+l.getLetra()+"',"+l.getCerto()+","+l.getErrado()+")";
    
        try {
            
            PreparedStatement stm = con.prepareStatement(sql);
            retorno = stm.execute();
            
            stm.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

       return retorno;

    }

    public LetraRanking consultarByLetra(String letra){
        LetraRanking l = new LetraRanking();

        String sql = "SELECT * FROM LETRAS WHERE letra = '"+letra+"'";
        try {
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
            if(rs.next()){
                
                
                l.setId(rs.getInt("id"));
                l.setLetra(rs.getString("letra"));
                l.setCerto(rs.getInt("certo"));
                l.setErrado(rs.getInt("errado"));
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(LetraController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return l;

    }

    public int qtdeItensCadastrados(){
        int x = 0;

        String sql = "SELECT * FROM LETRAS";
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

    public boolean alterarLetra(LetraRanking l){

        String sql = "UPDATE LETRAS SET letra = '"+ l.getLetra() +"', certo = "+l.getCerto()+", errado = " + l.getErrado()+" where id = "+l.getId();
        
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

    public List<LetraDto> consultarRankingDeLetras(){
        List<LetraDto> lista = new ArrayList<>();

        String sql = "select letra, SUM(certo - errado) as Resultado \n" +
                "from LETRAS\n" +
                "group by letra\n" +
                "order by Resultado desc";
        
        try {
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        
    
            while(rs.next()){
                
                LetraDto l = new LetraDto();
                l.setLetra(rs.getString("letra"));
                l.setResultado(rs.getInt("resultado"));

                lista.add(l);
                
            } 
        } catch (SQLException ex) {
            Logger.getLogger(LetraController.class.getName()).log(Level.SEVERE, null, ex);
        }

        

        return lista;
    }

}
