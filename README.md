1. Создать свой репозиторий в git
2. В браузере chrome записать рекордером Katalon studio recorder 2 теста(При этом необязательно проверять воспроизведение кейса в этом рекордере – он сбоит.  ):
    * Открыть сайт https://www.tinkoff.ru/career/vacancies/
  Внизу страницы в форме «Заполните анкету» прокликать все поля анкеты, не заполняя, чтоб получить все имеющиеся сообщения об ошибке.
  Добавить шаги на проверку текста ошибок.
    * Открыть сайт https://www.tinkoff.ru/career/vacancies/
Внизу страницы в форме «Заполните анкету» заполнить поля анкеты (фамилия и имя, дата рождения, электронная почта, мобильный телефон) невалидными значениями, чтоб получить все сообщения об ошибке. 
Добавить шаги на проверку текста ошибок
3. Создать новый проект со сборщиком Maven, java 1.8
4. С сайта https://www.seleniumhq.org/download/ скачать драйвера Google Chrome Driver и Mozilla GeckoDriver последней версии для вашей ОС (сами браузеры должны быть предустановлены). Разместить драйвера в какой-нибудь отдельной директории и прописать эту директорию в переменную окружения PATH
5. В проекте в директории src/test/java создать java класс под тесты 
6. В рекордере (Katalon) из верхнего меню выполнить нажатие на export выбрать формат java (WebDriver+JUnit). (лучше сразу тест в рекордере назвать так же как имя класса в проекте). Скопировать содержимое экспорта и вставить в проект. Оба теста разместить в одном классе (по аналогии классу SecondTest [учебного проекта](https://github.com/vchuchkalov/web-qa-training/tree/lesson1/src/test/java))
7. Отредактировать тестовый класс
    * Удалить строку “package com.example.tests;”, неиспользуемые методы и все throws Exception. 
    * В переменных класса оставить только WebDriver driver, String baseUrl. В методе tearDown() оставить только driver.quit(). 
    * Значение baseUrl заменить на https://www.tinkoff.ru/career/vacancies/
8. В pom-файле добавить зависимости:
 https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java/3.14.0, https://mvnrepository.com/artifact/junit/junit/4.12
9. Проверить работу теста запуском теста из класса (Зеленая кнопка play слева от кода у аннотации @Test())
10. Выделить BaseRunner, BrowserFactory (ff и chrome, случайный выбор делать не нужно), а сам тест поместить в отдельный класс по аналогии с [учебным проектом](https://github.com/vchuchkalov/web-qa-training/tree/lesson1/src/test/java)
11. Создать maven-строку запуска с возможностью явно указать используемый браузер (Пример строк есть в учебном проекте README.md)
12. Локально на основе учебного проекта потренироваться запускать тесты параллельно
13. \* Дополнительное задание для желающих. Добавить поддержку браузера Opera

В ответе на домашнее задание присылайте ссылку на последний коммит
<hr>

## Запуск: ##

``mvn test -Dtest=Vacancies -Dbrowser=[chrome|firefox]``

По умолчанию тесты запускаются в Google Chrome.
