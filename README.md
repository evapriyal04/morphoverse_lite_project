# Morphoverse Lite: Client Project Tracker

A REST API backend for tracking client projects. A project moves through a
lifecycle and can have milestones with an original date and a current date.

This is a practice project based on a business requirements document from a
consulting company. It is a simplified version of one part of that document.

## Tech used

- Java 17
- Spring Boot
- Spring Data JPA (Hibernate)
- PostgreSQL
- Postman for API testing
- A basic HTML, CSS and JavaScript page for a demo 

## How to run

1. Create a PostgreSQL database named `morphoverse`.
2. Open `src/main/resources/application.properties` and set your database
   username and password.
3. Run `MorphoverseLiteApplication`.
4. The app runs on `http://localhost:8181`.
5. Open `http://localhost:8181/` in a browser for the demo page.

## Endpoints

| Method | URL | What it does |
| --- | --- | --- |
| POST | /api/projects | Create a project |
| GET | /api/projects | List all projects |
| GET | /api/projects/{id} | Get one project |
| PUT | /api/projects/{id} | Update a project |
| DELETE | /api/projects/{id} | Delete a project |
| PUT | /api/projects/{id}/state | Change a project's state |
| POST | /api/projects/{projectId}/milestones | Add a milestone |
| GET | /api/projects/{projectId}/milestones | List a project's milestones |

## Rules

- A new project starts in SETUP.
- Allowed state moves: SETUP to ACTIVE, ACTIVE to PAUSED, ACTIVE to CLOSED,
  PAUSED to ACTIVE. Any other move returns 409 Conflict.
- Project name and client name are required, and the target end date must be
  in the future. Invalid input returns 400 Bad Request.
- A missing project returns 404 Not Found.
- A milestone has a baseline date (the original date) and a current date.
  Both are set when the milestone is created.

## Project structure

- `controller`: receives HTTP requests and returns responses
- `service`: business logic, such as the allowed state moves
- `repository`: database access with Spring Data JPA
- `entity`: Project and Milestone, mapped to database tables
- `dto`: request and response classes, so entities are not exposed
- `exception`: custom exceptions and a global exception handler

## Testing

All endpoints, including the failure cases (400, 404, 409), are tested with the
Postman collection in the `postman` folder.

## What I would improve

- Change a milestone's current date with a required reason, and keep a history
- Add Spring Security with roles
- Add unit tests with JUnit and Mockito
- Add pagination to the project list
