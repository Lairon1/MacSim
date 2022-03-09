package com.macsim.http.server.databasecontroller;

import com.macsim.http.server.obj.Client;
import com.macsim.http.server.obj.Tariff;
import com.serializer.json.JSONException;
import com.serializer.parser.JsonObjectSerializer;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class DataBaseController {

    private DataBaseController(){ }

    private static DataBaseController instance = new DataBaseController().init();

    public static DataBaseController getInstance() {
        return instance;
    }

    private Connection connection;

    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    private DataBaseController init(){
        String url = "jdbc:mysql://kolei.ru/evseev2";
        String username = "evseev2";
        String password = "123456";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try{
                Connection connection = DriverManager.getConnection(url, username, password);
                this.connection = connection;

                logger.info("DataBase successfully connection.");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return this;
    }

    public boolean userCanRegister(String login){
        try(Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Client WHERE Login = '" + login+ "';")){
            resultSet.next();
            return resultSet.getRow() == 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public void registerClient(Client client){
        try(Statement statement = connection.createStatement()){
            String sql = "INSERT INTO `Client`(`Login`,`Password`,`FirstName`,`LastName`)VALUES('{Login}', '{Password}', '{FirstName}', '{LastName}');";
            sql = sql.replace("{Login}", client.getLogin());
            sql = sql.replace("{Password}", client.getPassword());
            sql = sql.replace("{FirstName}", client.getFirstname());
            sql = sql.replace("{LastName}", client.getLastname());
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Tariff getTariffByID(int id){
        Tariff tariff = new Tariff();
        try(Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Tariff WHERE ID =" + id)) {
            resultSet.next();
            if(resultSet.getRow() == 0) return null;

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

    public boolean TopUpBalance(Client client, int balance){
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE `Client` SET `Balance` = " + (client.getBalance() + balance) +  " WHERE (`Login` = '" + client.getLogin() + "' AND `Password` = '" + client.getPassword() + "');");
            return true;
        } catch (SQLException throwables) {
            return false;
        }
    }

    public Client tryClientLogin(String username, String password){
        try(Statement statement  = connection.createStatement();
                ResultSet resultSet  = statement.executeQuery("SELECT * FROM Client WHERE Login = '" + username + "';")) {
            while (resultSet.next()){
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
                statement.close();
                client.setUsedTariff(getTariffByID(tariffID));
                if(password.equals(client.getPassword())) return client;
                return null;
            }
        } catch (SQLException throwables) {
            logger.info(throwables.getMessage());
            throwables.printStackTrace();
        }

        return null;
    }

    public ArrayList<Tariff> getAllTariffs(){
        ArrayList<Tariff> tariffs = new ArrayList<>();
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Tariff;")) {
            while (resultSet.next()){
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
