/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anniversaire;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
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
            
            //statement.execute("insert into Anniversaire values ('1','Calibration','test','2000-01-01')");
            
        } catch (SQLException ex) {
            System.err.println("'Database' probably already exists? " + ex.getMessage());
            java.sql.Connection connection;
            java.sql.Statement clean_up;
            try {
                connection = java.sql.DriverManager.getConnection("jdbc:derby:Database");
                clean_up = connection.createStatement();
                /*
                Nettoyage de la base
                 */
                connection.commit();
            } catch (java.sql.SQLException sqle2) {
                System.err.println("'bank_database' persistent error: " + sqle2.getMessage());
                System.exit(-1);
            }
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
            a.setAnniversaire(resultset.getString("nom"),resultset.getString("prenom"),resultset.getString("date_naissance"),resultset.getString("id"));
           liste.add(a);
       }
       
       if(liste.isEmpty())
           System.out.println("List is empty");
       
       return(liste);
   }
   
   public boolean IdIsOk(String id) throws SQLException{
       this.resultset = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from Anniversaire where id = '"+id+"'");
       resultset.beforeFirst();
       if(resultset.next())
           return false;
       return true;
   }
   
   public void AddBirthday(Anniversaire birthday) throws SQLException{
       
       int random = (int)(Math.random() * (1000 - 0) +0 );
       while(!IdIsOk(String.valueOf(random))){
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
                  a.setAnniversaire(resultset.getString("nom"), resultset.getString("prenom"), resultset.getString("date_naissance"),resultset.getString("id"));
                  liste.add(a);
              }
              return liste;
   }
    
   
   public void DeleteBirthday(String id) throws SQLException{
       _connection.createStatement().executeUpdate("delete from Anniversaire where id = '"+id+"'");
       _connection.commit();
   }
   
//   public Anniversaire NextBirthday() throws SQLException{
//       
//       int mois = Calendar.getInstance().get(Calendar.MONTH)+1;
//       
//       this.resultset = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from Anniversaire where MONTH(date_naissance) >= "+mois+" ORDER BY date_naissance ASC FETCH FIRST 1 ROWS ONLY");
//       
//       resultset.beforeFirst();
//       Anniversaire an = null;
//       if(resultset.next()){
//            an = new Anniversaire();
//           an.setAnniversaire(resultset.getString("nom"), resultset.getString("prenom"), resultset.getString("date_naissance"), resultset.getString("id"));
//       }
//       if(an == null){
//                  this.resultset = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from Anniversaire where MONTH(date_naissance) <= "+mois+" ORDER BY date_naissance Desc FETCH FIRST 1 ROWS ONLY");
//       
//       resultset.beforeFirst();
//       
//       if(resultset.next()){
//            an = new Anniversaire();
//           an.setAnniversaire(resultset.getString("nom"), resultset.getString("prenom"), resultset.getString("date_naissance"), resultset.getString("id"));
//       }
//       return(an);
//       }
//      return(an);
//   }
   public Anniversaire NextBirthday() throws SQLException {
       
       ArrayList<Anniversaire> Liste = new ArrayList<>();
       this.resultset = _connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY).executeQuery("select * from Anniversaire");
       Anniversaire an = null;
       this.resultset.beforeFirst();
       while(this.resultset.next()){
           an = new Anniversaire();
           an.setAnniversaire(resultset.getString("nom"), resultset.getString("prenom"), resultset.getString("date_naissance"), resultset.getString("id"));
           Liste.add(an);
       }
       if(!Liste.isEmpty()) {
       System.out.println("loooooooool");
       ArrayList<Anniversaire> After = new ArrayList<>();
       ArrayList<Anniversaire> Before = new ArrayList<>();
       
       for( int i = 0 ; i < Liste.size() ; i++) {
           Calendar c = Convertisseur.stringToCalendar(Liste.get(i).get_date_naissance(), "yyyy-MM-dd");
           Calendar today = Calendar.getInstance();
           System.out.println("Date 1 : " + c.toString() + "\nDate 2 : "+today.toString());
           if(((c.get(Calendar.MONTH)+1) == (today.get(Calendar.MONTH)+1)) && (c.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH)) ) {
               System.out.println("test1");
               After.add(Liste.get(i));
           } else if (c.get(Calendar.MONTH) > today.get(Calendar.MONTH)) {
               System.out.println("test2");
               After.add(Liste.get(i));
           } else {
               System.out.println("test3");
               Before.add(Liste.get(i));
           }
       }
       
       if(!After.isEmpty()) {
        Collections.sort(After);
        return(After.get(0));
       } else {
        Collections.sort(Before);
        return(Before.get(0));
       }
//       Collections.sort(Liste, new Comparator<Anniversaire>(){
//           @Override
//           public int compare(Anniversaire a1, Anniversaire a2) {
//               return()
//           }
//       });
       
       
       } else {
           System.out.println("VIDE");
           return(null);
       }
   }
   
 
   
}
