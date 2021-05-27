# Our first Hurl file, just checking
# that our server is up and running.
GET http://localhost:8080
HTTP/1.1 200
[Asserts]
xpath "string(//head/title)" equals "Welcome to Quiz!"
xpath "//button" countEquals 2
xpath "string((//button)[1])" contains "Play"
xpath "string((//button)[2])" contains "Create"

# Check our health api:
GET http://localhost:8080/api/health
HTTP/1.1 200
[Asserts]
jsonpath "$.status" equals "RUNNING"

# Check that we have a 404 response for broken links
GET http://localhost:8080/not-found
HTTP/1.1 404

