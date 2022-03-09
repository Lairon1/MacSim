using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MakSimDataBaseAdmin.src.com.maksim.obj
{
    public class Tariff
    {
        public int ID { get; set; }
        public string Name { get; set; }
        public double Price { get; set; }
        public string Description { get; set; }
        public int Minutes { get; set; }
        public int Gigabytes { get; set; }
        public int SMS { get; set; }

        public string WPFMinutes { get
            {
                if (Minutes == -1) return "Безлимит";
                return Minutes + "";
            } 
        }

        public string WPFGigabytes
        {
            get
            {
                if (Gigabytes == -1) return "Безлимит";
                return Gigabytes + "";
            }
        }
        public string WPFSMS
        {
            get
            {
                if (SMS == -1) return "Безлимит";
                return SMS + "";
            }
        }

    }
}
