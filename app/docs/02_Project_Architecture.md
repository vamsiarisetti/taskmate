# Project Architecture

TaskMate follows MVVM architecture.

UI

↓

ViewModel

↓

Repository

↓

Room Database

↓

SQLite

---

## Responsibilities

### UI

Displays information to users.

### ViewModel

Contains business logic.

### Repository

Acts as the single source of truth.

### Room

Stores application data locally.

### WorkManager

Schedules reminder notifications.

---

## Why MVVM?

* Easier Testing
* Cleaner Code
* Better Separation of Concerns
* Scalable
