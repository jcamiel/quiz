# First, get the quiz creation page to capture
# the CSRF token (see https://en.wikipedia.org/wiki/Cross-site_request_forgery)
GET http://localhost:8080/new-quiz
HTTP/1.1 200
[Captures]
csrf_token: xpath "string(//input[@name='_csrf']/@value)"

# Create a new quiz, using the captured CSRF token.
POST http://localhost:8080/new-quiz
[FormParams]
name: Simpson
question0: 16f897ab
question1: dd894cca
question2: 4edc1fdb
question3: 37b9eff3
question4: 0fec576c
_csrf: {{csrf_token}}
HTTP/1.1 302
[Captures]
detail_url: header "Location"
[Asserts]
header "Location" matches "/quiz/detail/[a-f0-9]{8}"

# Open the newly created quiz detail page:
GET {{detail_url}}
HTTP/1.1 200



POST http://localhost:8080/new-quiz
[FormParams]
# Use of a too short name
name: xx
question0: 16f897ab
question1: dd894cca
question2: 4edc1fdb
question3: 37b9eff3
question4: 0fec576c
_csrf: {{csrf_token}}
HTTP/1.1 302


# Security check: we try to create a new quiz without a csrf
# token. Ou app should respond with a 403:
#POST http://localhost:8080/new-quiz
#[FormParams]
#question0: 16f897ab
#question1: dd894cca
#question2: 4edc1fdb
#question3: 37b9eff3
#question4: 0fec576c
#HTTP/1.1 403
