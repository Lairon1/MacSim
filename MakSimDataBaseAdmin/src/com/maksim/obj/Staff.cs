using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MakSimDataBaseAdmin.src.com.maksim.user
{
    public class Staff
    {
        public string Position { get; set; }
        public string Login { get; set; }
        public string Password { get; set; }
        public string FirtsName { get; set; }
        public string LastName { get; set; }
        public string MiddleName { get; set; }
        public string Location { get; set; }
        public int Passport { get; set; }
        public double Salary { get; set; }
        public DateTime Date { get; set; }
    }
}
