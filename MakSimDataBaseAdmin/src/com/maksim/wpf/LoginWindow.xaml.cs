using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using MakSimDataBaseAdmin.src.com.maksim.config;
using MakSimDataBaseAdmin.src.com.maksim.globals;
using MakSimDataBaseAdmin.src.com.maksim.provider;
using MakSimDataBaseAdmin.src.com.maksim.provider.mysql;
using MakSimDataBaseAdmin.src.com.maksim.user;
using MakSimDataBaseAdmin.src.com.maksim.wpf;
using MakSimDataBaseAdmin.src.com.maksim.wpf.tariff;

namespace MakSimDataBaseAdmin
{
    /// <summary>
    /// Логика взаимодействия для MainWindow.xaml
    /// </summary>
    public partial class LoginWindow : Window, INotifyPropertyChanged
    {
        private IDataProvider provider = new MySqlDataProvider();
        private Configuration userConfig = new Configuration();
        public event PropertyChangedEventHandler PropertyChanged;

        public Visibility ErrorState { get; set; }
        public LoginWindow()
        {
            InitializeComponent();
            DataContext = this;
            ErrorState = Visibility.Hidden;
            Globals.UserConfig = userConfig;
            
            userConfig.load();
            if (userConfig.getValue("Login") != null) LoginBox.Text = userConfig.getValue("Login");
            if (userConfig.getValue("Password") != null) PasswordBox.Password = userConfig.getValue("Password");
        }

        private async void Button_Login_Click(object sender, RoutedEventArgs e)
        {
            string login = LoginBox.Text;
            string password = PasswordBox.Password;
            if (login.Length == 0)
            {
                SendError("Поле для ввода логина пустое.");
                return;
            }
            if(password.Length == 0)
            {
                SendError("Поле для ввода пароля пустое.");
                return;
            }

            Staff staff = await provider.Login(login, password);

            if(staff == null)
            {
                SendError("Неверный логин или пароль!");
                return;
            }
            Globals.Staff = staff;
            if (SaveDataCheck.IsChecked.Value)
            {
                userConfig.setValue("Login", staff.Login);
                userConfig.setValue("Password", staff.Password);
                userConfig.save();
            }
            TariffWindow tariffWindow = new TariffWindow(provider);
            tariffWindow.Show();
            this.Close();
        }

        public void OnPropertyChanged([CallerMemberName] string prop = "")
        {
            if (PropertyChanged != null)
                PropertyChanged(this, new PropertyChangedEventArgs(prop));
        }

        private void SendError(string error)
        {
            ErrorLabel.Content = error;
            ThreadPool.QueueUserWorkItem(ErrorStartFlicker);
        }

        private void ErrorStartFlicker(Object state)
        {
            ErrorState = Visibility.Visible;
            OnPropertyChanged("ErrorState");
            Thread.Sleep(5000);
            ErrorState = Visibility.Hidden;
            OnPropertyChanged("ErrorState");
        }
    }
}
