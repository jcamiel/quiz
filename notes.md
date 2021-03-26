# Hurl Tutorial Scenario 


- Test home page

```
GET http://localhost:8080
HTTP/1.1 200
````

HTML asserts:

```
GET http://localhost:8080 
HTTP/1.1 200
[Asserts]
xpath "string(//head/title)" equals "Welcome to Quiz!"
````


- Test health api

```
GET http://localhost:8080/api/health
HTTP/1.1 200
[Asserts]
jsonpath "$.status" "OK"
```

- Testing existing quiz, going to `/quizz/1` for instance
