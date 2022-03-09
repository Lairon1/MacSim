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
using System.Windows.Shapes;
using MakSimDataBaseAdmin.src.com.maksim.obj;
using MakSimDataBaseAdmin.src.com.maksim.provider;

namespace MakSimDataBaseAdmin.src.com.maksim.wpf.tariff
{
    /// <summary>
    /// Логика взаимодействия для AddTariff.xaml
    /// </summary>
    public partial class AddTariff : Window, INotifyPropertyChanged

    {
        public event PropertyChangedEventHandler PropertyChanged;
        public Visibility ErrorState { get; set; }
        public bool isCreated { get; set; }

        private IDataProvider Provider { get; set; }
        public AddTariff(IDataProvider provider)
        {
            InitializeComponent();
            isCreated = false;
            this.Provider = provider;
        }

        private async void Button_Add_Click(object sender, RoutedEventArgs e)
        {
            string name = NameBox.Text;
            string description = DescriptionBox.Text;
            string priceStr = PriceBox.Text;
            string minutesStr = MinutesBox.Text;
            string gigabytesStr = GigagytesBox.Text;
            string smsStr = SMSBox.Text;

            if (name.Length == 0)
            {
                SendError("Поле названия не может быть пустым.");
                return;
            }
            if (priceStr.Length == 0)
            {
                SendError("Поле цены не может быть пустым.");
                return;
            }
            if (minutesStr.Length == 0)
            {
                SendError("Поле минут не может быть пустым.");
                return;
            }
            if (gigabytesStr.Length == 0)
            {
                SendError("Поле гигабайт не может быть пустым.");
                return;
            }
            if (smsStr.Length == 0)
            {
                SendError("Поле SMS не может быть пустым.");
                return;
            }

            double price;
            int minutes, gigabytes, sms;

            try
            {
                minutes = int.Parse(minutesStr);
            }
            catch
            {
                SendError("В поле минут может быть только целочисленное число.");
                return;
            }
            try
            {
                gigabytes = int.Parse(gigabytesStr);
            }
            catch
            {
                SendError("В поле гигабайты может быть только целочисленное число.");
                return;
            }
            try
            {
                sms = int.Parse(smsStr);
            }
            catch
            {
                SendError("В поле SMS может быть только целочисленное число.");
                return;
            }
            try
            {
                price = double.Parse(priceStr);
            }
            catch
            {
                SendError("В поле цена может быть только число.");
                return;
            }
            Tariff tariff = new Tariff();
            tariff.Name = name;
            tariff.Description = description;
            tariff.Price = price;
            tariff.Minutes = minutes;
            tariff.Gigabytes = gigabytes;
            tariff.SMS = sms;

            if (MessageBox.Show("Вы действительно хотите создать в этот тариф? Клиенты сразу смогут приобрести его.", "Подтверждение", MessageBoxButton.YesNo) == MessageBoxResult.No)
            {
                return;
            }
            await Provider.AddTarif(tariff);
            isCreated = true;
            this.Close();
        }

        public void OnPropertyChanged([CallerMemberName] string prop = "")
        {
            if (PropertyChanged != null)
                PropertyChanged(this, new PropertyChangedEventArgs(prop));
        }

        private void SendError(string error)
        {
            ErrorLabel.Text = error;
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
