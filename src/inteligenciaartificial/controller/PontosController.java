/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Soulcram
 */
public class PontosController {
    
        private Connection con;

    public PontosController(){

        con = new BancoDeDados().getConnection();

    }

    public boolean inserirPontos(Integer p){

       boolean retorno = false;
        
        String sql = "INSERT INTO TB_RECORD (record) VALUES("+p+")";
    
        try {
            
            PreparedStatement stm = con.prepareStatement(sql);
            retorno = stm.execute();
            
            stm.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

       return retorno;

    }

    public List<Integer> consultarAllRecords(){
        List<Integer> lista = new ArrayList<>();


        String sql = "select * from TB_RECORD";
        
        try {
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        
    
            while(rs.next()){
                lista.add(rs.getInt("record"));
                
            } 
        } catch (SQLException ex) {
            Logger.getLogger(LetraController.class.getName()).log(Level.SEVERE, null, ex);
        }

        

        return lista;
    }

    public Integer consultarRecord(){

        String sql = "SELECT * FROM TB_RECORD WHERE id = 1 ";
        Integer record = 0;
        try {
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        
    
            if(rs.next()){
                record = rs.getInt("record");
            } 
        } catch (SQLException ex) {
            Logger.getLogger(LetraController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return record;

    }

    public boolean alterarRecord(Integer pontos){
        String sql = "UPDATE TB_RECORD SET record = "+ pontos +" where id = 1";
        
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
    
        public int qtdeItensCadastrados(){
        int x = 0;

        String sql = "SELECT * FROM TB_RECORD";
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
    
}
