using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MakSimDataBaseAdmin.src.com.maksim.obj
{
    public class Client
    {
        public int ID { get; set; }
        public string Login { get; set; }
        public string   Firstname { get; set; }
        public string Lastname { get; set; }
        public Tariff Tariff { get; set; }
        public int Minutes { get; set; }
        public int Gigabytes { get; set; }
        public int SMS { get; set; }
    }
}
