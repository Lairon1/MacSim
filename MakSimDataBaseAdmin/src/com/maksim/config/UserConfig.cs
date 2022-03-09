using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MakSimDataBaseAdmin.src.com.maksim.config
{
    public class UserConfig : IConfig
    {

        private Dictionary<String, String> config = new Dictionary<string, string>();
        private string path = "data/UserConfig.cfg";
        public string getValue(string key)
        {
            if (config.ContainsKey(key))
                return config[key];
            else return null;
        }

        public void load()
        {
            if (!File.Exists(path))
            {
                Directory.CreateDirectory("data");
                File.Create(path).Close();
            }
            foreach (string val in File.ReadLines(path)){
                string[] keySet = val.Split('=');
                config.Add(keySet[0], keySet[1]);
            }
        }

        public void save()
        {
            File.Delete(path);
            StreamWriter streamWriter = new StreamWriter(path, true);
            streamWriter.AutoFlush = true;
            foreach (KeyValuePair<string, string> entry in config)
            {
                streamWriter.WriteLine(entry.Key + "=" + entry.Value);
            }
            streamWriter.Close();
            
        }

        public void setValue(string key, string value)
        {
            if (config.ContainsKey(key)) config[key] = value;
            else config.Add(key, value);
        }
    }
}
