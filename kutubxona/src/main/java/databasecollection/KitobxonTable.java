package databasecollection;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import registr.Kitobxon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KitobxonTable {
    public  List<SendMessage> kitobxonview(long id ) {
        List<SendMessage> registr = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutubxona", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from kitobxon"); // <4>
            while (rs.next()) {
                SendMessage message = new SendMessage();
                message.setChatId(id);
                message.setText(rs.getString("ismi")+" - "+rs.getString("familyasi"));
                registr.add(message);
            }
            connection.close(); // <5>
        } catch (Exception e) {
            e.printStackTrace();
        }
        return registr;
    }

    public  Kitobxon kitobxon(long chatid){
        Kitobxon kitobxon = new Kitobxon();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // <1>
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutubxona", "root", "root"); // <2>
            Statement statement = con.createStatement(); // <3>
            ResultSet rs = statement.executeQuery("select * from kitobxon"); // <4>
            while (rs.next()) {
                if (chatid==(rs.getLong("chatid"))) {
                    kitobxon.setName(rs.getString("ismi"));
                    kitobxon.setSurname(rs.getString("familyasi"));
                    kitobxon.setPassword(rs.getString("pasword"));
                    kitobxon.setLogin(rs.getString("login"));
                    kitobxon.setStep(rs.getString("step"));
                    kitobxon.setFaoliat(rs.getString("faoliyat"));
                    kitobxon.setChatid(rs.getLong("chatid"));
                }
            }
            con.close(); // <5>
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kitobxon;
    }
    public  String kitobxonname (String parol,String login) {
        String registr =null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // <1>
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutubxona", "root", "root"); // <2>
            Statement statement = con.createStatement(); // <3>
            ResultSet rs = statement.executeQuery("select * from kitobxon"); // <4>
            while (rs.next()) {
                if (parol.equals(rs.getString("pasword"))&&login.equals(rs.getString("login"))){
                    registr = rs.getString("ismi");
                }
            }
            con.close(); // <5>
        } catch (Exception e) {
            e.printStackTrace();
        }
        return registr;
    }

    public  boolean chatid(Long chatid) {
        boolean registr = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // <1>
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutubxona", "root", "root"); // <2>
            Statement statement = con.createStatement(); // <3>
            ResultSet rs = statement.executeQuery("select * from kitobxon"); // <4>
            while (rs.next()) {
                if (chatid.equals(rs.getLong("chatid"))) {
                    registr=true;
                }
            }
            con.close(); // <5>
        } catch (Exception e) {
            e.printStackTrace();
        }
        return registr;
    }
    public  void updatestep( String step , long chatid) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // <1>
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutubxona", "root", "root"); // <2>
            String sql = "UPDATE kitobxon SET step = ? WHERE chatid = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, step);
            statement.setLong(2, chatid);

            int rowsUpdated = statement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public  void updatename( String name , long chatid) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // <1>
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutubxona", "root", "root"); // <2>
            String sql = "UPDATE kitobxon SET ismi = ? WHERE chatid = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, name);
            statement.setLong(2, chatid);

            int rowsUpdated = statement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public  void updatesurname( String surname , long chatid) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // <1>
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutubxona", "root", "root"); // <2>
            String sql = "UPDATE kitobxon SET familyasi = ? WHERE chatid = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, surname);
            statement.setLong(2, chatid);

            int rowsUpdated = statement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    } public  void updatepasword( String pasword , long chatid) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // <1>
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutubxona", "root", "root"); // <2>
            String sql = "UPDATE kitobxon SET pasword = ? WHERE chatid = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, pasword);
            statement.setLong(2, chatid);

            int rowsUpdated = statement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    } public  void updatelogin( String login , long chatid) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // <1>
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutubxona", "root", "root"); // <2>
            String sql = "UPDATE kitobxon SET login = ? WHERE chatid = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, login);
            statement.setLong(2, chatid);

            int rowsUpdated = statement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public  void addbaza(String name ,String surname, String password,String login,long chatid ,String step,String faoliyat) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutubxona", "root", "root");
            Statement statement = connection.createStatement();
            String sql = "insert into kitobxon(ismi,familyasi,pasword,login,chatid,step,faoliyat) values ('%s','%s','%s','%s','%s','%s','%s')";
            sql = String.format(sql, name, surname,password,login,chatid,step,faoliyat);
            statement.executeUpdate(sql);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } public  void ochirishkitobxon(String name){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutubxona", "root", "root");
            String sql = "DELETE FROM kitobxon WHERE ismi = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean istherereader(String text) {
        boolean registr = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // <1>
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutubxona", "root", "root"); // <2>
            Statement statement = con.createStatement(); // <3>
            ResultSet rs = statement.executeQuery("select * from kitobxon"); // <4>
            while (rs.next()) {
                if (text.equals(rs.getString("ismi"))) {
                    registr=true;
                }
            }
            con.close(); // <5>
        } catch (Exception e) {
            e.printStackTrace();
        }
        return registr;
    }

}
