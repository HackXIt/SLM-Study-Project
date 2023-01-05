# SLM-Study-Project

A group project for the topic of software-lifecycle-management.
The goal is to implement a very simple REST API in JAVA, but configure and manage a whole CI/CD structure around the
project, as well as using proper development procedures in git. (User-Stories, Requirements, etc.)

# Project-prompt

> We are a small hydro-power electricity supplier near Vienna. Our customers expect electricity around the clock with a
> very high service level agreement. It is easy to derive that service times are very important to us. Huge monitors were
> installed that should show the current service message.
>
> "Everything operates as expected" is the default message. But a service operator can set the message manually to
> something else (e.g., "Service checks: No power until 5:00 pm"). A service operator can also reset the message to its
> default message.

# Project-Team

- @HackXIt
- @thorinaboenke
- @funkeydow

# REST API Goals

- [x] The service should be able to manage a centrally stored message.
- [x] The service should be able to deliver the message to a client.
- [x] The service should be able to set _it_ to a specific message. (it = centrally stored message)
- [x] The service should be able to reset the message.

# General Project Requirements

- [x] Documentation of "the whole process" regarding the project
- [x] Documentation of "the user stories" (i.e. have user-stories in general)
- [x] Documentation of "the usage of the software" (i.e. manual of the API)
- [x] The repository URL needs to be documented and be publicy viewable

# Grading criteria

- 20% Documentations of the process
- 15% Requirement Definition (User Stories)
- 15% Correct Status / Linking / Branching (Kanban, git)
- 15% Implementation
- 15% Testing
- 10% Continuous Integration (Pipeline Testing)
- 10% Continuous Delivery (Pipeline Artifact)

# Example Reference

| API-Path                                                  | Example-Result                           |
|-----------------------------------------------------------|------------------------------------------|
| /api/message                                              | "Everything works as expected"           |
| /api/message/set?m=Service+checks:+No+power+until+5:00+pm | "ok"                                     |
| /api/message                                              | "Service checks: No power until 5:00 pm" |
| /api/message/reset                                        | "ok"                                     |
| /api/message                                              | "Everything works as expected"           |

