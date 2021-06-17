# First, get the quiz creation page to capture
# the CSRF token (see https://en.wikipedia.org/wiki/Cross-site_request_forgery)
GET http://localhost:8080/new-quiz
HTTP/1.1 200
[Captures]
csrf_token: xpath "string(//input[@name='_csrf']/@value)"
[Asserts]
xpath "//comment" countEquals 0     # Check that we don't leak comments

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

# Test various server-side validations:

# Invalid form name value: too short
POST http://localhost:8080/new-quiz
[FormParams]
name: x
question0: 16f897ab
question1: dd894cca
question2: 4edc1fdb
question3: 37b9eff3
question4: 0fec576c
_csrf: {{csrf_token}}
HTTP/1.1 200
[Asserts]
xpath "//label[@for='name'][@class='invalid']" exists

# Invalid email parameter:
POST http://localhost:8080/new-quiz
[FormParams]
name: Barth
email: barthsimpson
question0: 16f897ab
question1: dd894cca
question2: 4edc1fdb
question3: 37b9eff3
question4: 0fec576c
_csrf: {{csrf_token}}
HTTP/1.1 200
[Asserts]
xpath "//label[@for='email'][@class='invalid']" exists

# No CSRF token:
POST http://localhost:8080/new-quiz
[FormParams]
name: Barth
email: barth.simpson@provider.net
question0: 16f897ab
question1: dd894cca
question2: 4edc1fdb
question3: 37b9eff3
question4: 0fec576c
HTTP/1.1 403
