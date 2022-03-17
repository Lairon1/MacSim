using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Windows;
using System.Windows.Input;
using MakSimDataBaseAdmin.src.com.maksim.globals;
using MakSimDataBaseAdmin.src.com.maksim.obj;
using MakSimDataBaseAdmin.src.com.maksim.provider;

namespace MakSimDataBaseAdmin.src.com.maksim.wpf.tariff
{
    /// <summary>
    /// Логика взаимодействия для TariffWindow.xaml
    /// </summary>
    public partial class TariffWindow : Window, INotifyPropertyChanged
    {
        private IDataProvider Provider;

        public event PropertyChangedEventHandler PropertyChanged;

        private List<Tariff> _SortedTariffs;



        public List<Tariff> SortedTariffs 
        {
            get 
            { 
                return _SortedTariffs; 
            }
            set 
            {
                _SortedTariffs = value;
                OnPropertyChanged("SortedTariffs");
            } 
        }
        private List<Tariff> tariffs;


        public TariffWindow(IDataProvider provider)
        {
            this.Provider = provider;
            InitializeComponent();
            DataContext = this;
            LoadTariffs();
        }

        public void OnPropertyChanged([CallerMemberName] string prop = "")
        {
            if (PropertyChanged != null)
                PropertyChanged(this, new PropertyChangedEventArgs(prop));
        }

        private async void LoadTariffs()
        {
            tariffs = (List<Tariff>)await Provider.GetAllTarifs();
            SortedTariffs = tariffs;
        }

        private void ListView_MouseDoubleClick(object sender, MouseButtonEventArgs e)
        {
            Tariff tariff = (Tariff)TariffListView.SelectedItem;
            RedactTariffWindow redactTariffWindow = new RedactTariffWindow(tariff, Provider);
            redactTariffWindow.ShowDialog();
            if (redactTariffWindow.isChanged) LoadTariffs();
        }
        private void Button_Add_Click(object sender, RoutedEventArgs e)
        {
            AddTariff addTariff = new AddTariff(Provider);
            addTariff.ShowDialog();
            if (addTariff.isCreated) LoadTariffs();
        }

        private void Button_Profile_Click(object sender, RoutedEventArgs e)
        {
            ProfileWindow profileWindow = new ProfileWindow(Globals.Staff);
            profileWindow.ShowDialog();
        }

        private void SerchBox_KeyUp(object sender, KeyEventArgs e)
        {
            string text = SerchBox.Text.ToUpper();
            if (text.Length == 0)
            {
                SortedTariffs = tariffs;
                return;
            }
            SortedTariffs = tariffs.Where(t => t.Name.ToUpper().Contains(text)).ToList();
        }

        private void ComboBox_SelectionChanged(object sender, System.Windows.Controls.SelectionChangedEventArgs e)
        {
            string sortType = SortType.SelectedItem.ToString();
            switch(sortType) {
                case "Не установлено":
                    SortedTariffs = tariffs;
                    break;
                case "По возрастанию цены":
                    SortedTariffs = tariffs.OrderBy(t => t.Price).ToList();
                    break;
                case "По возрастанию гигабайт":
                    SortedTariffs = tariffs.OrderBy(t => t.Gigabytes).ToList();
                    break;
                case "По возрастанию минут":
                    SortedTariffs = tariffs.OrderBy(t => t.Minutes).ToList();
                    break;
                case "По возрастанию sms":
                    SortedTariffs = tariffs.OrderBy(t => t.SMS).ToList();
                    break;
                case "По убыванию цены":
                    SortedTariffs = tariffs.OrderByDescending(t => t.Price).ToList();
                    break;
                case "По убыванию гигабайт":
                    SortedTariffs = tariffs.OrderByDescending(t => t.Gigabytes).ToList();
                    break;
                case "По убыванию минут":
                    SortedTariffs = tariffs.OrderByDescending(t => t.Minutes).ToList();
                    break;
                case "По убыванию sms":
                    SortedTariffs = tariffs.OrderByDescending(t => t.SMS).ToList();
                    break;
            }

        }

        public List<string> SortTypes
        {
            get
            {
                List<string> list = new List<string>();
                list.Add("Не установлено");
                list.Add("По возрастанию цены");
                list.Add("По возрастанию гигабайт");
                list.Add("По возрастанию минут");
                list.Add("По возрастанию sms");

                list.Add("По убыванию цены");
                list.Add("По убыванию гигабайт");
                list.Add("По убыванию минут");
                list.Add("По убыванию sms");
                return list;
            }
        }

    }

    //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
}
