/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anniversaire;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author taiQui
 */
public class Database {
    static {
        try {
            java.sql.DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
            java.sql.Statement statement = java.sql.DriverManager.getConnection("jdbc:derby:Database;create=true").createStatement();
            
            statement.execute("create table Anniversaire(\n"
                    + "id varchar(10),\n"
                    + "nom varchar(30),\n"
                    + "prenom varchar(30),\n"
                    + "date_naissance Date,\n"
                    + "constraint Anniversaire_key primary key(id))\n");
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   private java.sql.Connection _connection;
   private java.sql.ResultSet resultset;
    
   public Database() throws java.sql.SQLException {
       System.out.println("Database construction");
       _connection = java.sql.DriverManager.getConnection("jdbc:derby:Database");
       _connection.setAutoCommit(false);
   }
   
   
   public ArrayList<Anniversaire> getBirthday(int mois,int jour) throws SQLException{
       
       ArrayList<Anniversaire> liste = new ArrayList<Anniversaire>();
       
       this.resultset = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from Anniversaire where month(date_naissance) = "+mois+" and day(date_naissance) = "+jour+"");
       resultset.beforeFirst();
       while(resultset.next()){
           Anniversaire a = new Anniversaire();
            a.setAnniversaire(resultset.getString("nom"),resultset.getString("prenom"),resultset.getString("date_naissance"));
           liste.add(a);
       }
       
       if(liste.isEmpty())
           System.out.println("List is empty");
       
       return(liste);
   }
   
   private boolean IdIsOk(int id) throws SQLException{
       this.resultset = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from Anniversaire where id = "+id+"");
       resultset.beforeFirst();
       if(resultset.next())
           return false;
       return true;
   }
   
   public void AddBirthday(Anniversaire birthday) throws SQLException{
       
       int random = (int)(Math.random() * (1000 - 0) +0 );
       while(!IdIsOk(random)){
            random = (int)(Math.random() * (1000 - 0) +0 );
       }
       
       
       _connection.createStatement().execute("insert into Anniversaire values('"+random+"','"+birthday.get_nom()+"','"+birthday.get_prenom()+"',DATE('"+Convertisseur.calendarToString(Convertisseur.stringToCalendar(birthday.get_date_naissance(),"yyyy-MM-dd"),"yyyy-MM-dd")+"'))");
       _connection.commit();
   }
   
   public ArrayList<Anniversaire> getAllBirthday() throws SQLException{
              this.resultset = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from Anniversaire");
              
              ArrayList<Anniversaire> liste = new ArrayList<Anniversaire>();
              
              resultset.beforeFirst();
              while(resultset.next()){
                  Anniversaire a = new Anniversaire();
                  a.setAnniversaire(resultset.getString("nom"), resultset.getString("prenom"), resultset.getString("date_naissance"));
                  liste.add(a);
              }
              return liste;
   }
    
}
