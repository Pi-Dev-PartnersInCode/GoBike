/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Competition;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyConnection;

/**
 *
 * @author mneri
 */
public class CompetitionCRUD {

    Connection cnx;
    Statement st;

    public CompetitionCRUD() {
        cnx = MyConnection.getInstance().getCn();

    }

    public void ajouterCompetition(Competition c) {
        try {
            PreparedStatement pst = cnx.prepareStatement("INSERT INTO competition (nomComp,emplacement,description,dateComp) VALUES (?,?,?,?)");
            pst.setString(1, c.getNom());
            pst.setString(2, c.getEmplacement());
            pst.setString(3, c.getDescription());
            pst.setDate(4, c.getDateComp());
            
            pst.executeUpdate();
            System.out.println("Competition added");
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }
    public void modifierCompetition(Competition c){
        try {
            PreparedStatement pst = cnx.prepareStatement("update competition set nomComp = ? ,emplacement = ?, description = ?, dateComp = ? where idCompetition = ?");
            pst.setString(1, c.getNom());
            pst.setString(2, c.getEmplacement());
            pst.setString(3, c.getDescription());
            pst.setDate(4, c.getDateComp());
            
            pst.setInt(5, c.getIdCompetition());
            
            pst.executeUpdate();
            System.out.println("Competition updated");
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }
    public void supprimerCompetition(int id){
        try {
            PreparedStatement pst = cnx.prepareStatement("delete from competition where idCompetition = ?");
            pst.setInt(1,id);
            pst.executeUpdate();
            System.out.println("Competition deleted");
        } catch (SQLException ex) {
            Logger.getLogger(CompetitionCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public ArrayList<Competition> afficherCompetition(){
        ArrayList<Competition> psr = new ArrayList<>();
        try {
            String req = "SELECT * FROM competition";
            PreparedStatement pst2 = cnx.prepareStatement(req);
            ResultSet rs = pst2.executeQuery();
            while (rs.next())
            {
                Competition c = new Competition();
                
                c.setIdCompetition(rs.getInt("idCompetition"));
                c.setNom(rs.getString("nomComp"));
                c.setEmplacement(rs.getString("emplacement"));
                c.setDescription(rs.getString("description"));
                c.setDateComp(rs.getDate("dateComp"));
               
                psr.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }
        
        
        return psr;             
    }
     public ArrayList<Competition> rechercher(String nom ){
        ArrayList<Competition> psr = new ArrayList<>();
        try {
            String req = "SELECT * FROM competition where nomComp like '?'";
            PreparedStatement pst2 = cnx.prepareStatement(req);
            pst2.setString(1,"%"+nom+"%");
            ResultSet rs = pst2.executeQuery();
            while (rs.next())
            {
                Competition c = new Competition();
                
                c.setIdCompetition(rs.getInt("idCompetition"));
                c.setNom(rs.getString("nomComp"));
                c.setEmplacement(rs.getString("emplacement"));
                c.setDescription(rs.getString("description"));
                c.setDateComp(rs.getDate("dateComp"));
               
                psr.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }
        
        
        return psr;             
    }

    public void FacturePdf(int id) throws SQLException,FileNotFoundException,DocumentException,IOException 
    {
        Document doc = new Document();
        
       
        st= cnx.createStatement();
        ResultSet rs=st.executeQuery("select participation.*,user.* from participation INNER JOIN user ON participation.idUser=User.idUser where participation.idComp='"+id+"'ORDER BY rang");
        PdfWriter.getInstance(doc, new FileOutputStream("e:/pdf/Resultat.pdf"));
        
        doc.open();
        doc.add(new Paragraph("   "));
        doc.add(new Paragraph("  Resultat de la Competition  "));
        doc.add(new Paragraph("   "));
        
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        PdfPCell cell;
        
        cell = new PdfPCell(new Phrase("nom_membre",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("prenom_membre",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("record",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        
        
        cell = new PdfPCell(new Phrase("ranking",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        
        

        
        
        
        
        
        
        
     while (rs.next()) {                
            
               String nom_membre=rs.getString("firstName");
               String prenom_membre=rs.getString("lastName");
               int record=rs.getInt("record");
                  int ranking=rs.getInt("rang");
               
            
               
                 
               
               //Conversion to String
              /*
               String nom  = nom.toString();
               String prenom  = prenom.toString();
*/
                String rank = Integer.toString(ranking);
                String rec = Integer.toString(record);
         
               
               
               
               
               cell = new PdfPCell(new Phrase(nom_membre,FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(BaseColor.GRAY);
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase(prenom_membre,FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(BaseColor.GRAY);
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase(rec,FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(BaseColor.GRAY);
               table.addCell(cell);
        
        
               cell = new PdfPCell(new Phrase(rank,FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(BaseColor.GRAY);
               table.addCell(cell);
        
        
   
        
        
               
              
        
                        }
            doc.add(table);
            doc.close();
            Desktop.getDesktop().open(new File ("e:/pdf/Resultat.pdf"));
            }
     
     
     
     
}













/*


    public ArrayList<Competition> afficherCompetition(){
        ArrayList<Competition> psr = new ArrayList<>();
        try {
            String req = "SELECT * FROM PERSONNE";
            PreparedStatement pst2 = cn2.prepareStatement(req);
            ResultSet rs = pst2.executeQuery();
            while (rs.next())
            {
                Competition c = new Competition();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString(2));
                c.setPrenom(rs.getString("prenom"));
                psr.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }
        
        return psr;
        
        
    }
}
*/
