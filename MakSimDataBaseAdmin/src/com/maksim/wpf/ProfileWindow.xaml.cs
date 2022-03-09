using MakSimDataBaseAdmin.src.com.maksim.user;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace MakSimDataBaseAdmin.src.com.maksim.wpf
{
    /// <summary>
    /// Логика взаимодействия для ProfileWindow.xaml
    /// </summary>
    public partial class ProfileWindow : Window
    {
        public Staff Staff { get; set; }
        public ProfileWindow(Staff staff)
        {
            InitializeComponent();
            DataContext = this;
            this.Staff = staff;
        }
    }
}
