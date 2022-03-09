using MakSimDataBaseAdmin.src.com.maksim.user;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MakSimDataBaseAdmin.src.com.maksim.obj
{
    public class Chat
    {
        public int ID { get; set; }
        public Client client { get; set; }
        public List<ChatMessage> ChatMessages { get; set; }
    }

    public class ChatMessage
    {
        public int ID { get; set; }
        public DateTime Date { get; set; }
        public string Message { get; set; }
        public bool StaffMessage { get; set; }
        public Staff Staff { get; set; }
        public Chat Chat { get; set; }
    }
}
