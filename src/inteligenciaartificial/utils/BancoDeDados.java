package inteligenciaartificial.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class BancoDeDados {

    private static final String NOME_BANCO = "JOGO_DA_FORCA";
    private static final String url = "jdbc:h2:~/" + NOME_BANCO;
    private static final String usuario = "soulcram";
    private static final String senha = "p4r4tud0";

    public BancoDeDados() {

    }

    public Connection getConnection(){
    
           try {
               
               Class.forName("org.h2.Driver");
               
               
               return DriverManager.getConnection(url,usuario,senha);
               } catch (SQLException ex) {
                   Logger.getLogger(BancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
               } catch (ClassNotFoundException ex) {
            Logger.getLogger(BancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
          
           return null;
    
    }
    
//     public static void alterar(String sql) throws SQLException{
//    
//         try {
//               Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
//               Connection con = DriverManager.getConnection(url,usuario,senha); 
//               Statement stm = con.createStatement();     
//               stm.executeUpdate(sql);
//                
//               
//           } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
//               System.err.println(ex);
//           }
//    
//    }
    
//   public static void startBd(){
//    
//       try {
//           
//           System.setProperty("derby.system.home", "/home/usuario/derby");
//           NetworkServerControlImpl networkServer = new NetworkServerControlImpl();
//           networkServer.start(new PrintWriter(System.out));
//           System.out.println("Conectado ao banco de dados.");
//
//       } catch (Exception ex) {
//            
//           System.err.println(ex);
//
//       }
//    
//    
//    
//    
//    }

}
