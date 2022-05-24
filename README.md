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

 ![enter image description here](https://pngimg.com/uploads/sim_card/sim_card_PNG9302.png "Пример продукции")

<code>
Евсеев      | Вадим | Сервеевич
</code>

# Описание предметной области

Наша компания занимается исключительно распространением своих [услуг](https://ru.wikipedia.org/wiki/%D0%A3%D1%81%D0%BB%D1%83%D0%B3%D0%B0) ([Тарифы](https://ru.wikipedia.org/wiki/%D0%A2%D0%B0%D1%80%D0%B8%D1%84)) и производством пластиково продукта ([Сим-карт](https://ru.wikipedia.org/wiki/%D0%A1%D0%B8%D0%BC-%D0%BA%D0%B0%D1%80%D1%82%D0%B0)). Реализация продажи выглядит так: С начало клиент регистрируется в системе через мобильное приложение вводя свои данные(ФИО, логин, пароль), чтобы получить услугу нужжно выбрать раздел "тарифы", после в разделе будут отображены весь перечень тарифов который есть в базе данных, на виджете с тарифом отображена цена, описание и колиество гигабайт,минут,смс . После этого клиент может нажать на кнопку "Подключить" и перейти к подключанию тарифа. Также пользователь может посмотреть свой тариф по кнопке "Мой тариф".
Пользователь может пополнить баланс нажав по кнопке "Пополнить баланс", после чего его перенесет на форму с пополнением бланса.
Пользователь может посмотреть свой профиль и изменить пароль от своего аккаунта.

### Небольшая статистика за 2020 г.
<code>Продукт      | Общая цена р. | Объём
:-------- |:-----:| -------:
Услуга | 160 000  | 213
Пластиковый продукт| 5 700    | 59
</code>

![USE CASE IMG](https://www.plantuml.com/plantuml/png/VLFDRjD04BxlKunoeeUMEuUg1OvKGWIu6sSJLZXsPA-5UgDDWGCHaH0dL45vWz36hPiqpYkCtuXlryLhNC8XykxCzpCxCzbBj1VhT1WGd_AC5rqr548h7KXwaSXjtqia-LoKnrpmENuNN10-QyxATpmpOVEbJitk2178lYOTKQm6hpNrL2nzhQ9G2C_NKGpiDrvopXclokCEUGaz2fGCTPq-DIdyvdnfR2pam0kzWOnhp4_EooFOPtm3cKzyJV2VuvA3QbEkFYlGFKpsUqE5Aj94scTr-0pqYmRw0yvJubNphMiSbkM4hqpGAcQuJwtcCpbGYOuzyxg3w9KAP4FyI_aUb3dn-IQHINd42ywhsXS8Z-kEF8a6AiG0OYVOx9eb5fmHt-3p2s6Zlgf2Qxm50Lmk3Tfmh6PLuVverD3y0Mm1DSYO7X0gcnbwUM8gjRINIhvzxiMgdpZUOvduRtGqscm6X07yxVRTo8A0AS5jqd2pd8VfeJNwJtsS4o9O3TCnq7FSR4ihQp8jAZ_ogfwUvP_O1athvpoDetvhM21S6pqphd8CYGot5KwVYQyWlxPhCg5gyr5H0PCPx6PkNbtjzVj2LDjCsxkDZj-kBuBDplpPoLPOr3pQsTdznvRH0rAX7wGzsOBMQzD0tALlXBeQ7CG-oa64u-sa8u3SUhHJRfxjH7C6mhdSPk_nsUO9tqS26sz3Iy2gO3asU1TexV5MtUdQukjTbVOVlpteBI7sPDXBXy5l)
```
@startuml МакSim

title Use-case cотового оператора "МакSim"

left to right direction

actor "Клиент" as Client
actor "Менеджер" as Manager
actor "Системный администратор" as SysAdmin

usecase "Регистрация нового пользователя" as RegistrationMobile
usecase "Вход в пользовательский акаунт" as LoginUser
usecase "Клиентское мобильное приложение" as MobileApp
usecase "Просмотр тарифов" as ViewTarifs
usecase "Descktop приложение" as DesktopApp
usecase "Оформить тариф" as BuyTarif
usecase "Просмотр и редактирование личного профиля" as ViewProfile
usecase "Войти в учетную запись персонала" as LoginStaff

Client -> MobileApp
Manager -> DesktopApp
SysAdmin -> DesktopApp

MobileApp ..> RegistrationMobile : include
MobileApp ..> LoginUser : include
RegistrationMobile ..> LoginUser : include
DesktopApp ..> LoginStaff: include

LoginUser .up.> ViewTarifs
LoginUser .up.> BuyTarif
LoginUser .up.> ViewProfile

LoginStaff .up.> (Редактирование/добовление/удаление тарифов)
SysAdmin .up.> (Добовление нового персонала)

@enduml
```
