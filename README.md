# Sport Management Event System

## Кіріспе
**Sport Management Event System** — спортшылар мен спорттық іс-шараларды ұйымдастырушылар үшін арналған басқару жүйесі. Бұл қосымша спортшыларға өздеріне ыңғайлы жарыстарды іздеуге мүмкіндік береді және ұйымдастырушыларға жарыстарды құрып, қатысушыларды басқаруға мүмкіндік жасайды.

## Мазмұны
1. [Жоба туралы](#жоба-туралы)
2. [Қажеттіліктер](#қажеттіліктер)
3. [Қолданушы интерфейсі](#қолданушы-интерфейсі)
4. [Технологиялар](#технологиялар)
5. [Архитектура](#архитектура)
6. [Болашақ мүмкіндіктер](#болашақ-мүмкіндіктер)
7. [Қорытынды](#қорытынды)

## Жоба туралы
**Sport Management Event System** — бұл спортшылар, ұйымдастырушылар және әкімшілер үшін арналған жүйе. Спортшылар өздеріне қолайлы жарыстарды іздеп, тіркеле алады, ұйымдастырушылар жаңа жарыстарды құрып, қатысушыларды басқара алады, ал әкімшілер жүйенің жалпы басқаруын қамтамасыз етеді. Бұл жүйе спорттық іс-шараларды ұйымдастыруды жеңілдетіп, барлық қолданушылар арасындағы байланысты жақсартады.

## Қажеттіліктер
Жобаны іске қосу үшін келесі құралдар қажет:
- **Web сервер**: MAMP — PHP серверін іске қосу үшін.
- **PHP**: PHP 7.4 немесе одан жоғары нұсқасы.
- **Деректер қоры**: MySQL — деректер қоры phpMyAdmin арқылы басқарылатын болады.
- **Frontend**: HTML, CSS, JavaScript — пайдаланушы интерфейсі үшін.

## Қолданушы интерфейсі
Жүйеде үш негізгі рөл бар:
1. **Спортшылар**:
   - Жарыстарды іздеу және қатысуға тіркелу мүмкіндігі.
   - Жарыс туралы мәліметтерді көру және қатысу шарттарын анықтау.
   
2. **Ұйымдастырушылар**:
   - Жаңа жарыстарды құрып, қатысушыларды тіркеу мүмкіндігі.
   - Жарыстардың нәтижелерін енгізу және қатысушылардың тіркелуін қадағалау.

3. **Админдар**:
   - Қателерді түзетіп жарыстар тізімін өзгерте алады.
   - Қолданбанын қауіпсіздігін тексеріп отырады.
   - Қолданушылар деректерін өшіріп не өзгерте алады

## Технологиялар
Бұл жоба келесі технологиялармен жасалған:
- **Frontend**: HTML, CSS, JavaScript
- **Backend**: PHP
- **Деректер қоры**: MySQL (phpMyAdmin арқылы басқару)

### MAMP және phpMyAdmin
- **MAMP** — бұл PHP және MySQL серверін қосуға арналған құрал.
- **phpMyAdmin** — MySQL деректер қорын басқару үшін қолданылатын веб-қосымша.

### Maven файлындағы конфигурация
Жобаның бекендіде қолданылған **Maven** файлы арқылы тәуелділіктерді басқаруға болады. Төменде жобаның маңызды бөліктерін түсіндіре кетейін:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <version>8.0.31</version> <!-- Актуалды MySQL версиясы -->
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

1.**Бұл кодтың ішінде пайдаланылған тәуелділіктерді түсіндірейін**:
   --spring-boot-starter-thymeleaf — HTML беттерін динамикалық түрде құру үшін пайдаланылады.
   --spring-boot-starter-web — веб-қосымшаларды жасаудың негізі.
   --spring-boot-devtools — қосымшаның жылдам дамуы үшін пайдаланылады (қосымшаны қайта іске қосу).
   --spring-boot-starter-data-jpa — деректермен жұмыс істеуге арналған JPA пайдалану.
   --mysql-connector-j — MySQL деректер қорына қосылу үшін қажетті драйвер.

Архитектура
1.**Жүйе келесі архитектуралық үлгі бойынша жұмыс істейді**:

   --Frontend (HTML, CSS, JS): Пайдаланушы интерфейсі, ол қолданушының қажеттіліктеріне жауап береді.
   --Backend (PHP): Сервер тарапындағы бизнес логикасы.
   --Database (MySQL): Деректер қорында барлық ақпарат сақталады, оның ішінде қатысушылар мен жарыстар туралы мәліметтер.

*Болашақ мүмкіндіктер*:
   Жүйеде қолданушыларды тіркеу және кіру мүмкіндігі қосылады.
   Жарыс нәтижелерін қатысушылар үшін қадағалау.
   Пайдаланушы пікірлері мен бағалауларды қосу.
   Қатысушылар үшін ақпараттық ескертулер (электрондық пошта арқылы хабарлама).
   
*Қорытынды*:
   Sport Management Event System спортшылар мен ұйымдастырушылар арасында қарым-қатынасты жақсартуға арналған тиімді құрал болып табылады. Жоба спорттық іс-шараларды басқаруды жеңілдетіп, қатысушыларды тіркеу      процесін автоматтандыруға мүмкіндік береді.
