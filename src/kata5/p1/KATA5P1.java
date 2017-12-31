package kata5.p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
  
  public class KATA5P1 {
  
    public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection("jdbc:sqlite:KATA5.db");
        Statement st = con.createStatement();
       
        String query = "CREATE TABLE IF NOT EXISTS MAIL ('Id' INTEGER PRIMARY KEY AUTOINCREMENT , 'Mail' TEXT NOT NULL);";
        st.execute(query);
      
        FileReader fl = null;
        BufferedReader bf = null;
        String fileName = "C:\\Users\\ismael\\Documents\\NetBeansProjects\\KATA5-P1\\emails.txt";
        
        try{
            fl = new FileReader(fileName);
            bf = new BufferedReader(fl);
            String mail;
            while((mail = bf.readLine()) != null){
                if(!mail.contains("@")){
                    continue;
                }
                query =	"INSERT	INTO	MAIL	(Mail)	VALUES	('"+ mail +"');”";	
                st.execute(query);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        
        query = "SELECT COUNT(*) FROM MAIL";
        ResultSet rs = st.executeQuery(query);
        System.out.println("Número de registros de la tabla MAIL: " + rs.getInt(1));
    }
}