using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MakSimDataBaseAdmin.src.com.maksim.obj;
using MakSimDataBaseAdmin.src.com.maksim.user;

namespace MakSimDataBaseAdmin.src.com.maksim.provider
{
    public interface IDataProvider
    {
        Task<Staff> Login(string login, string password);

        Task<IEnumerable<Tariff>> GetAllTarifs();

        Task<bool> UpdateTariff(Tariff tariff);

        Task<bool> AddTarif(Tariff tariff);

        Task<bool> DelTariff(Tariff tariff);
    }
}
