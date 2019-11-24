package inteligenciaartificial.controller;

import inteligenciaartificial.model.Palavra;
import inteligenciaartificial.utils.BancoDeDados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PalavraController {

    private Connection con;

    public PalavraController(){

        con = new BancoDeDados().getConnection();

    }

    public boolean inserirPalavra(Palavra p){

       boolean retorno = false;
        
        String sql = "INSERT INTO PALAVRAS (palavra) VALUES('"+p.getPalavra()+"')";
    
        try {
            
            PreparedStatement stm = con.prepareStatement(sql);
            retorno = stm.execute();
            
            stm.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

       return retorno;

    }

    public List<Palavra> consultarAllPalavras(){
        List<Palavra> lista = new ArrayList<>();


        String sql = "select * from PALAVRAS";
        
        try {
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        
    
            while(rs.next()){
                
                Palavra p = new Palavra();
                p.setPalavra(rs.getString("palavra"));

                lista.add(p);
                
            } 
        } catch (SQLException ex) {
            Logger.getLogger(LetraController.class.getName()).log(Level.SEVERE, null, ex);
        }

        

        return lista;
    }

    public Palavra consultarByID(int id){
        Palavra p = new Palavra();

        String sql = "SELECT * FROM PALAVRAS WHERE id = "+id+"";

        try {
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        
    
            if(rs.next()){
                p.setId(rs.getInt("id"));
                p.setPalavra(rs.getString("palavra"));
            } 
        } catch (SQLException ex) {
            Logger.getLogger(LetraController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p;

    }

    public int qtdeItensCadastrados(){
        int x = 0;

        String sql = "SELECT * FROM PALAVRAS";
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



    public boolean alterarPalavra(Palavra p){
        String sql = "UPDATE PALAVRAS SET palavra = '"+ p.getPalavra() +"' where id = "+p.getId();
        
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

    public void excluirPalavra(Palavra p){
//        db = bancoDeDados.getReadableDatabase();
//        db.delete("PALAVRAS", "id" + "=" + p.getId() + "", null);
//        db.execSQL("VACUUM");
    }



}
