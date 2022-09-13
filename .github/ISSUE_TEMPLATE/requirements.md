---
name: Requirements
about: To describe the projects requirements
title: ''
labels: ''
assignees: ''

---

As a logged-out user
I want to be able to sign in to a website
So that I can find acess my personal profile

Scenario: System user signs in with valid credentials
“Given I’m a logged-out system user
and I’m on the Sign-In page
When I fill in the “Username” and “Password” fields with my authentication credentials
and I click the Sign-In button
Then the system signs me in”
