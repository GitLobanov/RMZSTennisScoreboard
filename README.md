Выполнение задачи "Табло теннисного матча"  из роадмапа Сергея Жукова

## Содержание
- [Описание проекта](#Описание_проекта)
- [Описание интерфейса](#Описание_интерфейса)
- [Используемая литература](#Литература)

# Описание проекта

- Java - коллекции, ООП
- Паттерн MVC(S)
- Maven/Gradle
- Backend: Java Servlets, JSP
- HTTP - GET и POST запросы, формы
- Базы данных - SQL, Hibernate, H2 (in-memory SQL database)
- Frontend - HTML/CSS, блочная вёрстка
- Тесты - юнит тестирование, JUnit 5
- Деплой - облачный хостинг, командная строка Linux, Tomcat

### Полное ТЗ - [click](https://zhukovsd.github.io/java-backend-learning-course/projects/tennis-scoreboard/)
### Автор проекта - [Сергей Жуков](https://github.com/zhukovsd)

# Описание_интерфейса

1. **index.jsp**: Главная страница, переход к созданию матча или просмотру завершенных матчей

![image](https://github.com/user-attachments/assets/1ab99004-c84b-4b1b-9d5e-fa2344d97ddd)

#### задействовано:
- IndexController (IndexServlet)

2. **match.jsp**: Создание нового матча

![image](https://github.com/user-attachments/assets/1dc01f18-890c-4a82-8547-b09a45fae2cf)

#### задействовано:
- ProcessMatchServlet

3. **match.jsp**: Ход матча

![image](https://github.com/user-attachments/assets/cae80bb8-2f02-443f-b65a-86f4aae05a5b)

![image](https://github.com/user-attachments/assets/b78ace8f-e633-4b72-aeca-3b36f2f8e26c)

#### задействовано:
- ProcessMatchServlet

4. **match.jsp**: Завершение матча, переход к списку матчей или создание нового

![image](https://github.com/user-attachments/assets/89d19366-83ba-45f6-a9e0-dc0bbe96fb97)

#### задействовано:
- ProcessMatchServlet

5. **matches.jsp**: Завершенные матчи

![image](https://github.com/user-attachments/assets/3ceb0d4d-e20d-4602-958e-af285e52b89b)

#### задействовано:
- ListMatchServlet

7. **matches.jsp**: Результат поиска по игроку

![image](https://github.com/user-attachments/assets/f5df33c7-6b59-425b-9362-8cc8c4b98d08)

#### задействовано:
- ListMatchServlet

8. Деплой проекта

![image](https://github.com/user-attachments/assets/ee318123-24c5-4530-97a5-bc50d8750b5c)

Деплой осуществлялся в сервис Selectel

# Литература

- Hibernate H2 Database Example Tutorial / https://www.javaguides.net/2019/11/hibernate-h2-database-example-tutorial.html
- Guide to Java Servlet / https://howtodoinjava.com/java/servlets/complete-java-servlets-tutorial/
- How to send Java Servlet to JSP / https://initialcommit.com/blog/how-to-send-data-from-servlet-to-jsp
- Guide to JAVA Servlets / https://howtodoinjava.com/java/servlets/complete-java-servlets-tutorial/
- How to Install Apache Tomcat 8 on Linux? / https://www.geeksforgeeks.org/how-to-install-apache-tomcat-8-on-linux/?ysclid=lytnyxvqh640412217
- 100 commands to use Linux / https://tproger.ru/articles/100-komand-linux-dlya-ezhednevnoj-raboty?ysclid=lz6ebt92ve823170842

