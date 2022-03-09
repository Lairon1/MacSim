using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MakSimDataBaseAdmin.src.com.maksim.config
{
    public interface IConfig
    {
        void load();
        string getValue(string key);
        void setValue(string key, string value);
        void save();
    }
}
