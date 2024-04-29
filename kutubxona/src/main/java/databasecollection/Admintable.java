package databasecollection;

import registr.Admin;

import java.sql.*;

public class Admintable {
    public Boolean view(String parol, String login) {
        boolean registr = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // <1>
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutubxona", "root", "root"); // <2>
            Statement statement = con.createStatement(); // <3>
            ResultSet rs = statement.executeQuery("select * from admin"); // <4>
            while (rs.next()) {
                if (parol.equals(rs.getString("pasword")) && login.equals(rs.getString("login"))) {
                    registr = true;
                }
            }
            con.close(); // <5>
        } catch (Exception e) {
            e.printStackTrace();
        }
        return registr;
    }

    public String name(String parol, String login) {
        String registr = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // <1>
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutubxona", "root", "root"); // <2>
            Statement statement = con.createStatement(); // <3>
            ResultSet rs = statement.executeQuery("select * from admin"); // <4>
            while (rs.next()) {
                if (parol.equals(rs.getString("pasword")) && login.equals(rs.getString("login"))) {
                    registr = rs.getString("ismi");
                }
            }
            con.close(); // <5>
        } catch (Exception e) {
            e.printStackTrace();
        }
        return registr;
    }

    public boolean chatid(Long chatid) {
        boolean registr = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // <1>
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutubxona", "root", "root"); // <2>
            Statement statement = con.createStatement(); // <3>
            ResultSet rs = statement.executeQuery("select * from admin"); // <4>
            while (rs.next()) {
                if (chatid.equals(rs.getLong("chatid"))) {
                    registr = true;
                }
            }
            con.close(); // <5>
        } catch (Exception e) {
            e.printStackTrace();
        }
        return registr;
    }

    public Admin admin(Admin admin) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // <1>
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutubxona", "root", "root"); // <2>
            Statement statement = con.createStatement(); // <3>
            ResultSet rs = statement.executeQuery("select * from admin"); // <4>
            while (rs.next()) {
                if (admin.getChatid() == (rs.getLong("chatid"))) {
                    admin.setName(rs.getString("ismi"));
                    admin.setSurname(rs.getString("familyasi"));
                    admin.setPassword(rs.getString("pasword"));
                    admin.setLogin(rs.getString("login"));
                }
            }
            con.close(); // <5>
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }

    public void updatestep( String step , long chatid) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // <1>
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutubxona", "root", "root"); // <2>
            String sql = "UPDATE admin SET step = ? WHERE chatid = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, step);
            statement.setLong(2, chatid);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Admin admin1(long chatid) {
        Admin admin = new Admin();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // <1>
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutubxona", "root", "root"); // <2>
            Statement statement = con.createStatement(); // <3>
            ResultSet rs = statement.executeQuery("select * from admin"); // <4>
            while (rs.next()) {
                if (chatid == (rs.getLong("chatid"))) {
                    admin.setChatid(rs.getLong("chatid"));
                    admin.setName(rs.getString("ismi"));
                    admin.setSurname(rs.getString("familyasi"));
                    admin.setPassword(rs.getString("pasword"));
                    admin.setLogin(rs.getString("login"));
                    admin.setStep(rs.getString("step"));
                    admin.setFaoliat(rs.getString("faoliyat"));
                }
            }
            con.close(); // <5>
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }

    public void insertIntoTableadmin(String name, String login, String password, String surname, long chatid, String step) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutubxona", "root", "root");
            Statement statement = con.createStatement();

            String sql = "insert into admin(ismi,familyasi,pasword,login,chatid,step) values ('%s','%s','%s','%s','%s','%s')";
            sql = String.format(sql, name, surname, password, login, chatid, step);
            statement.executeUpdate(sql);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*public void insertIntoTablekitobxon(String name, String login, String password, String surname, Long chatid) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutubxona", "root", "root");
            Statement statement = con.createStatement();

            String sql = "insert into kitobxon(ismi,familyasi,pasword,login.chatid) values ('%s','%s','%s','%s','%s')";
            sql = String.format(sql, name, surname, password, login,chatid);
            statement.executeUpdate(sql);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
