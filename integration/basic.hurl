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

# Check that we have a 404 response for broken links
GET http://localhost:8080/not-found
HTTP/1.1 404

# Check our health api:
GET http://localhost:8080/api/health
HTTP/1.1 200
[Asserts]
jsonpath "$.status" equals "RUNNING"

