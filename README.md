

# Web API
Для курсового проекта было разработано Web request API.

# Содержание
1. [Как запустить](https://github.com/Lairon1/MacSim/tree/WepApiRequestServer#%D0%BA%D0%B0%D0%BA-%D0%B7%D0%B0%D0%BF%D1%83%D1%81%D1%82%D0%B8%D1%82%D1%8C)
2. [Что он умеет?](https://github.com/Lairon1/MacSim/tree/WepApiRequestServer#%D1%87%D1%82%D0%BE-%D0%BE%D0%BD-%D1%83%D0%BC%D0%B5%D0%B5%D1%82)
   * [GET-Запросы](https://github.com/Lairon1/MacSim/tree/WepApiRequestServer#get-%D0%B7%D0%B0%D0%BF%D1%80%D0%BE%D1%81%D1%8B)
      + [Список тарифов](https://github.com/Lairon1/MacSim/tree/WepApiRequestServer#%D1%81%D0%BF%D0%B8%D1%81%D0%BE%D0%BA-%D1%82%D0%B0%D1%80%D0%B8%D1%84%D0%BE%D0%B2)
   * [POST-Запросы](https://github.com/Lairon1/MacSim/tree/WepApiRequestServer#post-%D0%B7%D0%B0%D0%BF%D1%80%D0%BE%D1%81%D1%8B)
		+ [Регистрация](https://github.com/Lairon1/MacSim/tree/WepApiRequestServer#Регистрация)
		+ [Авторизация](https://github.com/Lairon1/MacSim/tree/WepApiRequestServer#авторизация)
		+ [Пополнение баланса](https://github.com/Lairon1/MacSim/tree/WepApiRequestServer#Пополнение-баланса)
		+ [Оформление тарифа](https://github.com/Lairon1/MacSim/tree/WepApiRequestServer#%D0%BE%D1%84%D0%BE%D1%80%D0%BC%D0%BB%D0%B5%D0%BD%D0%B8%D0%B5-%D1%82%D0%B0%D1%80%D0%B8%D1%84%D0%B0)
		+ [Изменение пароля](https://github.com/Lairon1/MacSim/tree/WepApiRequestServer#%D0%B8%D0%B7%D0%BC%D0%B5%D0%BD%D0%B5%D0%BD%D0%B8%D0%B5-%D0%BF%D0%B0%D1%80%D0%BE%D0%BB%D1%8F)
## Как запустить
Для того чтобы запустить сервер нужно иметь на компьютере установленную java 11 версии не меньше.
Нужно загрузить Server.jar из этой ветки и с помощью консольной команды:

    java -jar Server.jar

После этого сервер запустится на порту 80.
## Что он умеет?
### GET-Запросы
#### Список тарифов
Чтобы получить список тарифов нужно отправить GET запрос по адресу serverip/tariff
Ответ придет в виде JSON.
Пример:

	{
	    "Tariffs": [
	        {
	            "ID": 1,
	            "Name": "Безлимитный интернет",
	            "Price": 899,
	            "Description": "Безлимитный интернет на каждом уголке планеты",
	            "Minutes": 400,
	            "Gigabytes": -1,
	            "SMS": 300
	        },
	        {
	            "ID": 2,
	            "Name": "Безлимитные минуты и SMS",
	            "Price": 599,
	            "Description": "Общайтесь сколько влезет!",
	            "Minutes": -1,
	            "Gigabytes": 10,
	            "SMS": -1
	        }
	    ]
    }

Коды ответа:
| Код | значение |
|--|--|
| 500 | Серверная ошибка |
| 200| Удачно |

В любом случае при ошибке сервер отправит JSON с сообщением ошибки
Пример:

    {
	    "ERROR":"Error Message"
    }

### POST-Запросы
#### Регистрация
Чтобы зарегистрировать пользователя нужно отправить POST запрос на адрес serverip/register
Тело запроса должно быть в JSON и иметь такой вид:

    {
	    "Login":"LoginExemple",
	    "Password":"PasswordExemple",
	    "Firstname":"FirstnameExemple",
	    "Lastname":"LastnameExemple"
    }
Ответ придет в виде JSON
Пример:

	{
	    "Client": {
	        "ID": 39,
	        "Login": "LoginExemple",
	        "Password": "PasswordExemple",
	        "FirstName": "FirstnameExemple",
	        "LastName": "LastnameExemple",
	        "Minutes": 0,
	        "Gigabytes": 0,
	        "SMS": 0,
	        "Balance": 0,
	        "PhoneNumber": 87773631335
	    }
	}
Коды ответа:
| Код | Значение |
|--|--|
| 500 | Серверная ошибка |
| 200| Удачно |
| 405| Был использован не POST запрос |
| 401| Логин или пароль не соответствуют требованиям |
| 400 | Не валидное тело json запроса |
В любом случае при ошибке сервер отправит JSON с сообщением ошибки
Пример:

    {
	    "ERROR":"Error Message"
    }
#### Авторизация
Чтобы зарегистрировать пользователя нужно отправить POST запрос на адрес serverip/login
Тело запроса должно быть в JSON и иметь такой вид:

    {
	    "Login":"LoginExemple",
	    "Password":"PasswordExemple"
    }
Ответ придет в виде JSON
Пример:

	{
	    "Client": {
	        "ID": 36,
	        "Login": "LoginExemple",
	        "Password": "PasswordExemple",
	        "FirstName": "FirstNameExemple",
	        "LastName": "LastNameExemple",
	        "UsedTariff": {
	            "ID": 4,
	            "Name": "Безлимит на все!",
	            "Price": 999,
	            "Description": "Безлимитные минуты, гигабайты и SMS!",
	            "Minutes": -1,
	            "Gigabytes": -1,
	            "SMS": -1
	        },
	        "Minutes": -1,
	        "Gigabytes": -1,
	        "SMS": -1,
	        "Balance": 1,
	        "PhoneNumber": 87778452434
	    }
	}

Коды ответа:
| Код | Значение |
|--|--|
| 200| Удачно |
| 405| Был использован не POST запрос |
| 401| Неверный логин или пароль |
| 400 | Не валидное тело json запроса |
В любом случае при ошибке сервер отправит JSON с сообщением ошибки
Пример:

    {
	    "ERROR":"Error Message"
    }
#### Пополнение баланса
Чтобы зарегистрировать пользователя нужно отправить POST запрос на адрес serverip/topUpBalance
Тело запроса должно быть в JSON и иметь такой вид:

    {
	    "Login":"LoginExemple",
	    "Password":"PasswordExemple",
	    "TopUpBalance":1
    }
Ответ придет в виде JSON
Пример:

	{
	    "Client": {
	        "ID": 36,
	        "Login": "LoginExemple",
	        "Password": "PasswordExemple",
	        "FirstName": "FirstNameExemple",
	        "LastName": "LastNameExemple",
	        "UsedTariff": {
	            "ID": 4,
	            "Name": "Безлимит на все!",
	            "Price": 999,
	            "Description": "Безлимитные минуты, гигабайты и SMS!",
	            "Minutes": -1,
	            "Gigabytes": -1,
	            "SMS": -1
	        },
	        "Minutes": -1,
	        "Gigabytes": -1,
	        "SMS": -1,
	        "Balance": 1,
	        "PhoneNumber": 87778452434
	    }
	}

Коды ответа:
| Код | Значение |
|--|--|
| 200 | Удачно |
| 405 | Был использован не POST запрос |
| 401 | Неверный логин или пароль (Или не валидная сумма пополнения) |
| 400 | Не валидное тело json запроса |
В любом случае при ошибке сервер отправит JSON с сообщением ошибки
Пример:

    {
	    "ERROR":"Error Message"
    }
#### Оформление тарифа
Чтобы зарегистрировать пользователя нужно отправить POST запрос на адрес serverip/hookUpTariff
Тело запроса должно быть в JSON и иметь такой вид:

    {
	    "Login":"LoginExemple",
	    "Password":"PasswordExemple",
	    "TariffID":1
    }
Ответ придет в виде JSON
Пример:

	{
	    "Client": {
	        "ID": 36,
	        "Login": "LoginExemple",
	        "Password": "PasswordExemple",
	        "FirstName": "FirstNameExemple",
	        "LastName": "LastNameExemple",
	        "UsedTariff": {
	            "ID": 4,
	            "Name": "Безлимит на все!",
	            "Price": 999,
	            "Description": "Безлимитные минуты, гигабайты и SMS!",
	            "Minutes": -1,
	            "Gigabytes": -1,
	            "SMS": -1
	        },
	        "Minutes": -1,
	        "Gigabytes": -1,
	        "SMS": -1,
	        "Balance": 1,
	        "PhoneNumber": 87778452434
	    }
	}

Коды ответа:
| Код | Значение |
|--|--|
| 500 | Серверная ошибка |
| 200 | Удачно |
| 405 | Был использован не POST запрос |
| 401 | Неверный логин или пароль (Или не валидный ID тарифа или недостаточно средств) |
| 400 | Не валидное тело json запроса |
В любом случае при ошибке сервер отправит JSON с сообщением ошибки
Пример:

    {
	    "ERROR":"Error Message"
    }
#### Изменение пароля
Чтобы зарегистрировать пользователя нужно отправить POST запрос на адрес serverip/changePassword
Тело запроса должно быть в JSON и иметь такой вид:

    {
	    "Login":"LoginExemple",
	    "Password":"PasswordExemple",
	    "ChangePassword":"ChangePasswordExemple"
    }
Ответ придет в виде JSON
Пример:

	{
	    "Client": {
	        "ID": 36,
	        "Login": "LoginExemple",
	        "Password": "PasswordExemple",
	        "FirstName": "FirstNameExemple",
	        "LastName": "LastNameExemple",
	        "UsedTariff": {
	            "ID": 4,
	            "Name": "Безлимит на все!",
	            "Price": 999,
	            "Description": "Безлимитные минуты, гигабайты и SMS!",
	            "Minutes": -1,
	            "Gigabytes": -1,
	            "SMS": -1
	        },
	        "Minutes": -1,
	        "Gigabytes": -1,
	        "SMS": -1,
	        "Balance": 1,
	        "PhoneNumber": 87778452434
	    }
	}

Коды ответа:
| Код | Значение |
|--|--|
| 500 | Серверная ошибка |
| 200 | Удачно |
| 405 | Был использован не POST запрос |
| 401 | Неверный логин или пароль (Или не валидный новый пароль) |
| 400 | Не валидное тело json запроса |
В любом случае при ошибке сервер отправит JSON с сообщением ошибки
Пример:

    {
	    "ERROR":"Error Message"
    }
