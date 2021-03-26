# hurl title.hurl --output /dev/null
GET http://localhost:8080
HTTP/1.1 200
[Asserts]
xpath "string(//head/title)" equals "Welcome to Quiz!"