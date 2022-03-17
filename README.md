# EER 
Для курсового проекта была создана база данных. 

| Client                   |              |              |                                 |
|--------------------------|--------------|--------------|---------------------------------|
| Ключ                     | Имя          | Тип данных   | Описание                        |
| Y                        | ID           | int          | Первичный ключ                  |
|                          | Login        | varchar(256) | Логин                           |
|                          | Password     | varchar(256) | Пароль                          |
|                          | FirstName    | varchar(256) | Имя                             |
|                          | LastName     | varchar(256) | Фамилия                         |
|                          | UsedTariffID | int          | ID используемого тарифа         |
|                          | Minutes      | int          | Количество минут                |
|                          | Gigabytes    | int          | Количество гигабайт             |
|                          | SMS          | int          | Количество сообщений            |
|                          | Balance      | int          | Баланс                          |
|                          | Phonenumber  | int          | Номер телефона                  |

| Tariff                   |              |              |                                 |
|--------------------------|--------------|--------------|---------------------------------|
| Ключ                     | Имя          | Тип данных   | Описание                        |
| Y                        | ID           | int          | Первичный ключ                  |
|                          | Name         | varchar(256) | Название тарифа                 |
|                          | Price        | double       | Цена тарифа                     |
|                          | Description  | longtext     | Описание                        |
|                          | Minutes      | int          | Количество минут                |
|                          | Gigabytes    | int          | Количество гигабайт             |
|                          | SMS          | int          | Количество сообщений            |

| Staff                    |              |              |                                 |
|--------------------------|--------------|--------------|---------------------------------|
| Ключ                     | Имя          | Тип данных   | Описание                        |
| Y                        | ID           | int          | Первичный ключ                  |
|                          | TypeID       | int          | ID типа професии                |
|                          | Login        | varchar(256) | Логин                           |
|                          | Password     | varchar(256) | Пароль                          |
|                          | FirstName    | varchar(256) | Имя                             |
|                          | LastName     | varchar(256) | Фамилия                         |
|                          | MiddleName   | varchar(256) | Отчество                        |
|                          | Location     | varchar(256) | Прописка                        |
|                          | Passport     | int          | Паспорт                         |
|                          | Salary       | double       | Оклад                           |
|                          | Date         | Date         | С какого времени начал работать |

| StaffType                |              |              |                                 |
|--------------------------|--------------|--------------|---------------------------------|
| Ключ                     | Имя          | Тип данных   | Описание                        |
| Y                        | ID           | int          | Первичный ключ                  |
|                          | Name         | varchar(256) | Название должности              |

| ChangePasswordClientLogs |              |              |                                 |
|--------------------------|--------------|--------------|---------------------------------|
| Ключ                     | Имя          | Тип данных   | Описание                        |
| Y                        | ID           | int          | Первичный ключ                  |
|                          | ClientID     | INT          | ID клиента                      |
|                          | NewPassword  | varchar(256) | Новый пароль                    |
|                          | Date         | DateTime     | Дата изменения                  |

Также эта таблица есть в формате xlsx в документе База данных для курсача.xlsx в этой ветке.
![Скриншот диаграммы](img.png)