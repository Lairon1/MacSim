using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MakSimDataBaseAdmin.src.com.maksim.obj;
using MakSimDataBaseAdmin.src.com.maksim.user;
using MySqlConnector;

namespace MakSimDataBaseAdmin.src.com.maksim.provider.mysql
{
    public class MySqlDataProvider : IDataProvider
    {

        private MySqlConnection conn;
        private string host = "kolei.ru";
        private string database = "evseev2";
        private string port = "3306";
        private string username = "evseev2";
        private string password = "123456";


        public MySqlDataProvider()
        {

            string connString = "Server=" + host + ";Database=" + database
                + ";port=" + port + ";User Id=" + username + ";password=" + password;
            conn = new MySqlConnection(connString);
            
        } 


        public async Task<bool> AddTarif(Tariff tariff)
        {
            conn.Open();
            MySqlCommand cmd = new MySqlCommand($"INSERT INTO `Tariff` (`Name`, `Price`, `Description`, `Minutes`, `Gigabytes`, `SMS`) VALUES ('{tariff.Name}', {tariff.Price}, '{tariff.Description}', {tariff.Minutes}, {tariff.Gigabytes}, {tariff.SMS}); ", conn);
            await cmd.ExecuteNonQueryAsync();
            conn.Close();
            return true;
        }

        public async Task<bool> DelTariff(Tariff tariff)
        {
            conn.Open();
            MySqlCommand cmd = new MySqlCommand($"DELETE FROM `Tariff` WHERE ID = {tariff.ID};", conn);
            await cmd.ExecuteNonQueryAsync();
            conn.Close();
            return true;
        }

        public async Task<IEnumerable<Tariff>> GetAllTarifs()
        {
            conn.Open();
            List<Tariff> tariffs = new List<Tariff>();
            MySqlCommand cmd = new MySqlCommand("SELECT * FROM Tariff;", conn);
            MySqlDataReader reader = await cmd.ExecuteReaderAsync();
            while (reader.Read())
            {
                Tariff tariff = new Tariff();
                tariff.ID = reader.GetInt32(0);
                tariff.Name = reader.GetString(1);
                tariff.Price = reader.GetDouble(2);
                tariff.Description = reader.GetString(3);
                tariff.Minutes = reader.GetInt32(4);
                tariff.Gigabytes = reader.GetInt32(5);
                tariff.SMS = reader.GetInt32(6);
                tariffs.Add(tariff);
            }
            conn.Close();
            return tariffs;
        }



        public async Task<Staff> Login(string login, string password)
        {
            Staff staff = new Staff();
            conn.Open();
            MySqlCommand cmd = new MySqlCommand("SELECT Name, Login, Password, FirstName, LastName, MiddleName, Location, Passport, Salary, Date FROM Staff, StaffType WHERE Login = '" + login + "' AND StaffType.ID = TypeID;", conn);
            MySqlDataReader reader = await cmd.ExecuteReaderAsync();
            if (!reader.Read())
            {
                conn.Close();
                return null;
            }
            staff.Position = reader.GetString(0);
            staff.Login = reader.GetString(1);
            staff.Password = reader.GetString(2);
            staff.FirtsName = reader.GetString(3);
            staff.LastName = reader.GetString(4);
            staff.MiddleName = reader.GetString(5);
            staff.Location = reader.GetString(6);
            staff.Passport = reader.GetInt32(7);
            staff.Salary = reader.GetDouble(8);
            staff.Date = reader.GetDateTime(9);
            if (staff.Login == login && staff.Password == password)
            {
                conn.Close();
                return staff;
            }
            conn.Close();
            return null;
        }

        public async Task<bool> UpdateTariff(Tariff tariff)
        {
            conn.Open();
            MySqlCommand cmd = new MySqlCommand(
                $"UPDATE `Tariff` SET `Name` = '{tariff.Name}', `Price` = {tariff.Price}, `Description` = '{tariff.Description}', `Minutes` = {tariff.Minutes}, `Gigabytes` = {tariff.Gigabytes}, `SMS` = {tariff.SMS} WHERE `ID` = {tariff.ID};",
                                                conn);
            await cmd.ExecuteNonQueryAsync();
            conn.Close();
            return true;
        }
    }
}
