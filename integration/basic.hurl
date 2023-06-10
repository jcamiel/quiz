# Checking our home page:
GET http://localhost:8080
HTTP 200
[Asserts]
xpath "string(//head/title)" == "Welcome to Quiz!"
xpath "//button" count == 2
xpath "string((//button)[1])" contains "Play"
xpath "string((//button)[2])" contains "Create"
# Testing content type:
header "Content-Type" == "text/html;charset=UTF-8"
# Testing session cookie:
cookie "JSESSIONID" exists
cookie "JSESSIONID[HttpOnly]" exists


# Check that we have a 404 response for broken links:
GET http://localhost:8080/not-found
HTTP 404
[Asserts]
header "Content-Type" == "text/html;charset=UTF-8"
xpath "string(//h1)" == "Error 404, Page not Found!"


# Check our health api:
GET http://localhost:8080/api/health
HTTP 200
[Asserts]
header "Content-Type" == "application/json"
jsonpath "$.status" == "RUNNING"
jsonpath "$.healthy" == true
jsonpath "$.operationId" exists


# Check question api:
GET http://localhost:8080/api/questions
[QueryStringParams]
offset: 0
size: 20
sort: oldest
HTTP 200
[Asserts]
header "Content-Type" == "application/json"
jsonpath "$" count == 20
jsonpath "$[0].id" == "c0d80047"
jsonpath "$[0].title" == "What is a pennyroyal?"