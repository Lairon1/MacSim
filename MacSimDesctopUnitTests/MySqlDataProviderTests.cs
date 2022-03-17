using System;
using System.Collections.Generic;
using MakSimDataBaseAdmin.src.com.maksim.obj;
using MakSimDataBaseAdmin.src.com.maksim.provider;
using MakSimDataBaseAdmin.src.com.maksim.provider.mysql;
using MakSimDataBaseAdmin.src.com.maksim.user;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace MacSimDesctopUnitTests
{
    [TestClass]
    public class MySqlDataProviderTests
    {



        [TestMethod]
        public void MySqlDataProvider_GetAllTariffs_Test()
        {
            IDataProvider dp = new MySqlDataProvider();
            IList<Tariff> tariffs = (IList<Tariff>) dp.GetAllTarifs().Result;

            Assert.AreEqual(tariffs.Count, 8);
        }

        [TestMethod]
        public void MySqlDataProvider_LoginTest_Lairon1_Test()
        {
            IDataProvider dp = new MySqlDataProvider();
            Staff lairon1 = dp.Login("Lairon1", "Igor1983").Result;
            Assert.IsNotNull(lairon1);

            Staff kotenokMoy = dp.Login("KotenokMoy", "IgorKotik1241").Result;
            Assert.IsNotNull(kotenokMoy);

            Staff ivanko = dp.Login("ivanko", "IvanIvanko").Result;
            Assert.IsNotNull(ivanko);
        }
        
     

    }
}
