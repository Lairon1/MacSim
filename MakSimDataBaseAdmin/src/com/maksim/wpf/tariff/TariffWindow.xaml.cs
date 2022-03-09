using System.Collections.Generic;
using System.ComponentModel;
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

        public IEnumerable<Tariff> Tariffs { get; set; }

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
            Tariffs = await Provider.GetAllTarifs();
            OnPropertyChanged("Tariffs");
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
    }
}
