### Запуск тестов в IntelliJ IDEA
```
Пример: mvn clean test "-Dgroups=test" "-Dbrowser=firefox" "-Dmode=fulscreen"


### Запуск в bash
```
mvn clean test \
  "-Dgroups=test" \
  "-Dbrowser=firefox" \
  "-Dmode=fullscreen" \
  "-DbrowserVersion=125.0" \
  "-Dremote.url=http://193.104.57.173/wd/hub"
