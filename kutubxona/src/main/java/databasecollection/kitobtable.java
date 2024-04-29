package databasecollection;

import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import registr.Admin;
import registr.USer;
import registr.kitob;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class kitobtable {

    public  void kitob(String name) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutubxona", "root", "root");
            Statement statement = connection.createStatement();
            String sql = "insert into kitob(nomi) values ('%s')";
            sql = String.format(sql, name);
            statement.executeUpdate(sql);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  void addkitob(String name,String file) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutubxona", "root", "root");
            Statement statement = connection.createStatement();
            String sql = "insert into kitob(nomi , file) values ('%s','%s')";
            sql = String.format(sql, name,file);
            statement.executeUpdate(sql);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  void updatefile( String file , String nomi) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // <1>
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutubxona", "root", "root"); // <2>
            String sql = "UPDATE kitob SET file = ? WHERE nomi = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, file);
            statement.setString(2, nomi);

            int rowsUpdated = statement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public  kitob kitobreturn(String nomi) {
        kitob kitobb = new kitob();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // <1>
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutubxona", "root", "root"); // <2>
            Statement statement = con.createStatement(); // <3>
            ResultSet rs = statement.executeQuery("select * from kitob"); // <4>
            while (rs.next()) {
                if (nomi.equals(rs.getString("nomi"))) {
                    kitobb.setName(rs.getString("nomi"));
                    kitobb.setFile(rs.getString("file"));
                }
            }
            con.close(); // <5>
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kitobb;
    }
    public   kitob kitobreturnb() {
        kitob kitobb = new kitob();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // <1>
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutubxona", "root", "root"); // <2>
            Statement statement = con.createStatement(); // <3>
            ResultSet rs = statement.executeQuery("select * from kitob"); // <4>
            while (rs.next()) {
                if (!rs.getString("nomi").equals("null") && rs.getString("file").equals("null")) {
                    kitobb.setName(rs.getString("nomi"));
                    kitobb.setFile(rs.getString("file"));
                }
            }
            con.close(); // <5>
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kitobb;
    }

    public List<SendMessage> kitobwiev(long id) {
        List<SendMessage> registr = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutubxona", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from kitob"); // <4>
            while (rs.next()) {
                SendMessage message = new SendMessage();
                message.setChatId(id);
                message.setText(rs.getString("nomi"));
                registr.add(message);
            }
            connection.close(); // <5>
        } catch (Exception e) {
            e.printStackTrace();
        }
        return registr;
    }

    public  SendDocument kitobfile(String name, long chatid) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatid);
        String st = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutubxona", "root", "root");
            String sql = "SELECT file FROM kitob WHERE nomi = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                st = rs.getString("file");
            }
            sendDocument.setDocument(new InputFile(st));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sendDocument;
    }
    public  void updatetavsif(String nomi , USer uSer) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // <1>
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutubxona", "root", "root"); // <2>
            String sql = "UPDATE kitob SET tavsif = ? WHERE nomi = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, uSer.getName());
            statement.setString(2, nomi);

            int rowsUpdated = statement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public  void ochirish(String name) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutubxona", "root", "root");
            String sql = "DELETE FROM kitob WHERE nomi = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  boolean istherebook(String text) {
        boolean registr = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // <1>
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutubxona", "root", "root"); // <2>
            Statement statement = con.createStatement(); // <3>
            ResultSet rs = statement.executeQuery("select * from kitob"); // <4>
            while (rs.next()) {
                if (text.equals(rs.getString("nomi"))) {
                    registr=true;
                }
            }
            con.close(); // <5>
        } catch (Exception e) {
            e.printStackTrace();
        }
        return registr;
    }
    public  boolean istherebookfile(String text) {
        boolean registr = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // <1>
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutubxona", "root", "root"); // <2>
            String sql = "SELECT file FROM kitob WHERE nomi = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, text);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                registr= (rs.getString("file").equals("null"))?false:true;
            }
            con.close(); // <5>
        } catch (Exception e) {
            e.printStackTrace();
        }
        return registr;
    }

}
