
Scenario: As a User I would like to be able to login successfully

Given the user goes to the website
When the user populates the username and password fields with 'pasta' and 'macaroni'
And the user submits the form
Then the user should see login success message