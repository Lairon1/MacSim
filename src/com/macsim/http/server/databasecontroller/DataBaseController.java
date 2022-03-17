package com.macsim.http.server.databasecontroller;

import com.macsim.http.server.obj.Client;
import com.macsim.http.server.obj.Tariff;
import com.macsim.http.server.utils.PhoneNumberGenerator;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class DataBaseController {


    public DataBaseController() {
        init();
    }

    private Connection connection;

    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    public DataBaseController init() {
        String url = "jdbc:mysql://kolei.ru/evseev2";
        String username = "evseev2";
        String password = "123456";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                this.connection = connection;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return this;
    }


    public boolean userCanRegister(String login) {
        init();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Client WHERE Login = '" + login + "';")) {
            resultSet.next();
            return resultSet.getRow() == 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public void registerClient(Client client) {
        init();
        try (Statement statement = connection.createStatement()) {
            String sql = "INSERT INTO `Client`(`Login`,`Password`,`FirstName`,`LastName`,`Phonenumber`)VALUES('{Login}', '{Password}', '{FirstName}', '{LastName}', '{PhoneNumber}');";
            sql = sql.replace("{Login}", client.getLogin());
            sql = sql.replace("{Password}", client.getPassword());
            sql = sql.replace("{FirstName}", client.getFirstname());
            sql = sql.replace("{LastName}", client.getLastname());
            String generate = PhoneNumberGenerator.generate();
            System.out.println(generate);
            sql = sql.replace("{PhoneNumber}", generate);
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Tariff getTariffByID(int id) {
        init();
        Tariff tariff = new Tariff();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Tariff WHERE ID =" + id)) {
            resultSet.next();
            if (resultSet.getRow() == 0) return null;

            tariff.setId(resultSet.getInt(1));
            tariff.setName(resultSet.getString(2));
            tariff.setPrice(resultSet.getDouble(3));
            tariff.setDescription(resultSet.getString(4));
            tariff.setMinutes(resultSet.getInt(5));
            tariff.setGigabytes(resultSet.getInt(6));
            tariff.setSms(resultSet.getInt(7));
        } catch (SQLException throwables) {
            logger.info(throwables.getMessage());
            throwables.printStackTrace();
        }
        return tariff;
    }


    public boolean updateClient(Client client) {
        init();
        try {
            String sql = "UPDATE `evseev2`.`Client`" +
                    "SET" +
                    "`Login` = \"{Login}\"," +
                    "`Password` = \"{Password}\"," +
                    "`FirstName` = \"{FirstName}\"," +
                    "`LastName` = \"{LastName}\"," +
                    "`UsedTariffID` = {UsedTariffID}," +
                    "`Minutes` = {Minutes}," +
                    "`Gigabytes` = {Gigabytes}," +
                    "`SMS` = {SMS}," +
                    "`Balance` = {Balance},\n" +
                    "`Phonenumber` = {Phonenumber}\n" +
                    "WHERE `ID` = {expr};\n";
            sql = sql.replace("{Login}", client.getLogin());
            sql = sql.replace("{Password}", client.getPassword());
            sql = sql.replace("{FirstName}", client.getFirstname());
            sql = sql.replace("{LastName}", client.getLastname());
            sql = sql.replace("{UsedTariffID}", client.getUsedTariff() == null ? "NULL" : client.getUsedTariff().getId() + "");
            sql = sql.replace("{Minutes}", client.getMinutes() + "");
            sql = sql.replace("{Gigabytes}", client.getGigabytes() + "");
            sql = sql.replace("{SMS}", client.getSms() + "");
            sql = sql.replace("{Balance}", client.getBalance()  + "");
            sql = sql.replace("{Phonenumber}", client.getPhoneNumber() + "");
            sql =sql.replace("{expr}", client.getId() + "");
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public Client tryClientLogin(String username, String password) {
        init();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Client WHERE Login = '" + username + "';")) {
            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt(1));
                client.setLogin(resultSet.getString(2));
                client.setPassword(resultSet.getString(3));
                client.setFirstname(resultSet.getString(4));
                client.setLastname(resultSet.getString(5));
                int tariffID = resultSet.getInt(6);
                client.setMinutes(resultSet.getInt(7));
                client.setGigabytes(resultSet.getInt(8));
                client.setSms(resultSet.getInt(9));
                client.setBalance(resultSet.getInt(10));
                client.setPhoneNumber(resultSet.getLong(11));
                statement.close();
                client.setUsedTariff(getTariffByID(tariffID));
                if (password.equals(client.getPassword())) return client;
                return null;
            }
        } catch (SQLException throwables) {
            logger.info(throwables.getMessage());
            throwables.printStackTrace();
        }

        return null;
    }

    public ArrayList<Tariff> getAllTariffs() {
        init();
        ArrayList<Tariff> tariffs = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Tariff;")) {
            while (resultSet.next()) {
                Tariff tariff = new Tariff();
                tariff.setId(resultSet.getInt(1));
                tariff.setName(resultSet.getString(2));
                tariff.setPrice(resultSet.getDouble(3));
                tariff.setDescription(resultSet.getString(4));
                tariff.setMinutes(resultSet.getInt(5));
                tariff.setGigabytes(resultSet.getInt(6));
                tariff.setSms(resultSet.getInt(7));
                tariffs.add(tariff);
            }
        } catch (SQLException throwables) {
            logger.info(throwables.getMessage());
            throwables.printStackTrace();
        }
        return tariffs;
    }
}
