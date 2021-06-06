# Checking our home page:
GET http://localhost:8080
HTTP/1.1 200
[Asserts]
xpath "string(//head/title)" equals "Welcome to Quiz!"
xpath "//button" countEquals 2
xpath "string((//button)[1])" contains "Play"
xpath "string((//button)[2])" contains "Create"
# Testing content type:
header "Content-Type" equals "text/html;charset=UTF-8"
# Testing session cookie:
cookie "JSESSIONID" exists
cookie "JSESSIONID[HttpOnly]" exists

# Check that we have a 404 response for broken links:
GET http://localhost:8080/not-found
HTTP/1.1 404
[Asserts]
header "Content-Type" equals "text/html;charset=UTF-8"
xpath "string(//h1)" equals "Error 404, Page not Found!"

# Check our health api:
GET http://localhost:8080/api/health
HTTP/1.1 200
[Asserts]
header "Content-Type" equals "application/json"
jsonpath "$.status" equals "RUNNING"
jsonpath "$.healthy" equals true
jsonpath "$.operationId" exists

# Check question api:
GET http://localhost:8080/api/questions
[QueryStringParams]
offset: 0
size: 20
sort: oldest
HTTP/1.1 200
[Asserts]
header "Content-Type" equals "application/json"
jsonpath "$" countEquals 20
jsonpath "$[0].id" equals "c0d80047-4fbe-4d45-a005-91b5c7018b34"
jsonpath "$[0].title" equals "What is a pennyroyal?"