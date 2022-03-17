# Курсовой проект по теме "Сотовые операторы"
## Содержание
1. [Описание предметной области & USE CASE](https://github.com/Lairon1/MacSim/tree/Description-of-the-subject-area#%D0%BE%D0%BF%D0%B8%D1%81%D0%B0%D0%BD%D0%B8%D0%B5-%D0%BF%D1%80%D0%B5%D0%B4%D0%BC%D0%B5%D1%82%D0%BD%D0%BE%D0%B9-%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D0%B8)
2. [EER Диаграмма](https://github.com/Lairon1/MacSim/tree/eer)
3. [Web Api](https://github.com/Lairon1/MacSim/tree/WepApiRequestServer)
   * [Как запустить](https://github.com/Lairon1/MacSim/tree/WepApiRequestServer#%D0%BA%D0%B0%D0%BA-%D0%B7%D0%B0%D0%BF%D1%83%D1%81%D1%82%D0%B8%D1%82%D1%8C)
   * [Что он умеет?](https://github.com/Lairon1/MacSim/tree/WepApiRequestServer#%D1%87%D1%82%D0%BE-%D0%BE%D0%BD-%D1%83%D0%BC%D0%B5%D0%B5%D1%82)
     * [GET-Запросы](https://github.com/Lairon1/MacSim/tree/WepApiRequestServer#get-%D0%B7%D0%B0%D0%BF%D1%80%D0%BE%D1%81%D1%8B)
        + [Список тарифов](https://github.com/Lairon1/MacSim/tree/WepApiRequestServer#%D1%81%D0%BF%D0%B8%D1%81%D0%BE%D0%BA-%D1%82%D0%B0%D1%80%D0%B8%D1%84%D0%BE%D0%B2)
     * [POST-Запросы](https://github.com/Lairon1/MacSim/tree/WepApiRequestServer#post-%D0%B7%D0%B0%D0%BF%D1%80%D0%BE%D1%81%D1%8B)
		  + [Регистрация](https://github.com/Lairon1/MacSim/tree/WepApiRequestServer#Регистрация)
		  + [Авторизация](https://github.com/Lairon1/MacSim/tree/WepApiRequestServer#авторизация)
		  + [Пополнение баланса](https://github.com/Lairon1/MacSim/tree/WepApiRequestServer#Пополнение-баланса)
		  + [Оформление тарифа](https://github.com/Lairon1/MacSim/tree/WepApiRequestServer#%D0%BE%D1%84%D0%BE%D1%80%D0%BC%D0%BB%D0%B5%D0%BD%D0%B8%D0%B5-%D1%82%D0%B0%D1%80%D0%B8%D1%84%D0%B0)
		  + [Изменение пароля](https://github.com/Lairon1/MacSim/tree/WepApiRequestServer#%D0%B8%D0%B7%D0%BC%D0%B5%D0%BD%D0%B5%D0%BD%D0%B8%D0%B5-%D0%BF%D0%B0%D1%80%D0%BE%D0%BB%D1%8F)
4. [Client Mobile app](https://github.com/Lairon1/MacSim/tree/Mobile)
   * [SplashScreen](https://github.com/Lairon1/MacSim/blob/Mobile/README.md#SplashScreen)
	* [Авторизация и регистрация](https://github.com/Lairon1/MacSim/blob/Mobile/README.md#%D0%B0%D0%B2%D1%82%D0%BE%D1%80%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D1%8F-%D0%B8-%D1%80%D0%B5%D0%B3%D0%B8%D1%81%D1%82%D1%80%D0%B0%D1%86%D0%B8%D1%8F)
	* [Главное окно](https://github.com/Lairon1/MacSim/blob/Mobile/README.md#%D0%B3%D0%BB%D0%B0%D0%B2%D0%BD%D0%BE%D0%B5-%D0%BE%D0%BA%D0%BD%D0%BE)
	* [Пополнение баланса](https://github.com/Lairon1/MacSim/blob/Mobile/README.md#%D0%BF%D0%BE%D0%BF%D0%BE%D0%BB%D0%BD%D0%B5%D0%BD%D0%B8%D0%B5-%D0%B1%D0%B0%D0%BB%D0%B0%D0%BD%D1%81%D0%B0)
	* [Тарифы](https://github.com/Lairon1/MacSim/blob/Mobile/README.md#%D1%82%D0%B0%D1%80%D0%B8%D1%84%D1%8B) 
	* [Мой тариф](https://github.com/Lairon1/MacSim/blob/Mobile/README.md#%D0%BC%D0%BE%D0%B9-%D1%82%D0%B0%D1%80%D0%B8%D1%84)
	* [Профиль](https://github.com/Lairon1/MacSim/blob/Mobile/README.md#%D0%BF%D1%80%D0%BE%D1%84%D0%B8%D0%BB%D1%8C)
5. [Приложение для администрирования тарифами](https://github.com/Lairon1/MacSim/tree/Desctop-UnitTests)
	 * [Описание](https://github.com/Lairon1/MacSim/tree/Desctop-UnitTests#%D0%BE%D0%BF%D0%B8%D1%81%D0%B0%D0%BD%D0%B8%D0%B5) 
	* [Авторизация](https://github.com/Lairon1/MacSim/tree/Desctop-UnitTests#%D0%B0%D0%B2%D1%82%D0%BE%D1%80%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D1%8F)
	* [Окно тарифов](https://github.com/Lairon1/MacSim/tree/Desctop-UnitTests#%D0%BE%D0%BA%D0%BD%D0%BE-%D1%82%D0%B0%D1%80%D0%B8%D1%84%D0%BE%D0%B2)
	* [Создание тарифов](https://github.com/Lairon1/MacSim/tree/Desctop-UnitTests#%D1%81%D0%BE%D0%B7%D0%B4%D0%B0%D0%BD%D0%B8%D0%B5-%D1%82%D0%B0%D1%80%D0%B8%D1%84%D0%BE%D0%B2)
	* [Редактирование тарифов](https://github.com/Lairon1/MacSim/tree/Desctop-UnitTests#%D1%80%D0%B5%D0%B4%D0%B0%D0%BA%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5-%D1%82%D0%B0%D1%80%D0%B8%D1%84%D0%BE%D0%B2)
	* [Удаление тарифов](https://github.com/Lairon1/MacSim/tree/Desctop-UnitTests#%D1%83%D0%B4%D0%B0%D0%BB%D0%B5%D0%BD%D0%B8%D0%B5-%D1%82%D0%B0%D1%80%D0%B8%D1%84%D0%BE%D0%B2) 

 Весь курсовой проект раскидан по веткам. 
 Навигацию по веткам можно осуществить в содержании выше.