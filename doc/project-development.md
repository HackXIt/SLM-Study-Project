# Our project development

In the beginning of our project we sat down in a meeting and translated our given project requirements into user-stories.

The given project requirements read as follows:

> Use GitHub for the project and follow the correct DevOps procedure. Use a Kanban board to manage your User Stories and use a git branching model (preferable gitflow) to manage your code. Track your development process by updating your Kanban board and write at least one unit tests for every requirement. A Continuous Integration pipeline that produces the finished software artifact should be implemented as well.
>
> Document
>
> 
>
> - the whole process
> - the user stories
> - **the repository URL (it has to be public)**
> - the usage of the software
>
> in a PDF file with screenshots and explanatory text. Submit the code (including the .git folder) as a .zip file.
>
> You can use external resources as long as you mark them: “ // taken from: <URL> ”

With that in mind, we wrote down the following 14 User stories:

- [As a maintainer, I want to avoid unnecessary binary files in the repository](https://github.com/HackXIt/SLM-Study-Project/issues/15)
- [As a developer, I want the project to be automatically built and tested on each uploaded commit](https://github.com/HackXIt/SLM-Study-Project/issues/14)
- [As a student, I want to document the project in markdown files](https://github.com/HackXIt/SLM-Study-Project/issues/13)
- [As a lecturer, I want to check the documentation of the project](https://github.com/HackXIt/SLM-Study-Project/issues/12)
- [As a developer, I want to see the latest test status of the package](https://github.com/HackXIt/SLM-Study-Project/issues/11)
- [As a user, I want to receive the latest stable package via the main-branch](https://github.com/HackXIt/SLM-Study-Project/issues/10)
- ~~[As a developer, I want to test and then implement the API path `/configure`](https://github.com/HackXIt/SLM-Study-Project/issues/8)~~
- ~~[As a tester, I want to have a simple page to configure the maintenance message](https://github.com/HackXIt/SLM-Study-Project/issues/7)~~
- [As a user, I want to call `/api/message/set` on the API to configure the current maintenance message](https://github.com/HackXIt/SLM-Study-Project/issues/6)
- [As a developer, I want to test and then implement the API path `/api/message/set`](https://github.com/HackXIt/SLM-Study-Project/issues/5)
- [As a developer, I want to test and then implement `/api/message/reset`](https://github.com/HackXIt/SLM-Study-Project/issues/4)
- [As a developer, I want to test and then implement `/api/message`](https://github.com/HackXIt/SLM-Study-Project/issues/3)
- [As a user, I want to call `/api/message/reset` on the API to reset the maintenance message](https://github.com/HackXIt/SLM-Study-Project/issues/2)
- [As a user, I want to call `/api/message` on the API to receive the current maintenance message](https://github.com/HackXIt/SLM-Study-Project/issues/1)

**Please note:**
We scratched 2 of our user stories as they were too ambitious and irrelevant to the grading of the project.

When writing those user-stories, we tried to stay close to a real life project scenario, assuming that all of those features are much more work, than they actually were.
We also used specific wording in the summary itself, to reflect our test-driven-development process, which we agreed upon early in the project.

There's more info about that process in the *Testing* section of this documentation.

Obviously, there is a lot more we could have done to reflect a real-life scenario, but we decided to keep it at that. Just to mention, here are a few examples of what we purposefully didn't do: 

*(to avoid unnecessary strain on our student life)*

- Write acceptance criteria for all of our user-stories *(Some of them have some sort of requirement definition)*
- Add milestones to the project
- Add individual tasks corresponding to user-stories for implementation

To document what was done in our development, we decided on submitting screenshots of the final state of all those user-stories.

# Final state of user-stories

1. ![user-story-1](https://github.com/HackXIt/SLM-Study-Project/blob/main/doc/attachments/user-story-1.png?raw=true)
2. ![user-story-2](https://github.com/HackXIt/SLM-Study-Project/blob/main/doc/attachments/user-story-2.png?raw=true)
3. ![user-story-3](https://github.com/HackXIt/SLM-Study-Project/blob/main/doc/attachments/user-story-3.png?raw=true)
4. ![user-story-4](https://github.com/HackXIt/SLM-Study-Project/blob/main/doc/attachments/user-story-4.png?raw=true)
5. ![user-story-5](https://github.com/HackXIt/SLM-Study-Project/blob/main/doc/attachments/user-story-5.png?raw=true)
6. ![image-20230105094935958](https://github.com/HackXIt/SLM-Study-Project/blob/main/doc/attachments/user-story-6.png?raw=true)
7. ![user-story-7](https://github.com/HackXIt/SLM-Study-Project/blob/main/doc/attachments/user-story-7.png?raw=true)
8. ![user-story-8](https://github.com/HackXIt/SLM-Study-Project/blob/main/doc/attachments/user-story-8.png?raw=true)
9. ![user-story-9](https://github.com/HackXIt/SLM-Study-Project/blob/main/doc/attachments/user-story-9.png?raw=true)
10. ![user-story-10](https://github.com/HackXIt/SLM-Study-Project/blob/main/doc/attachments/user-story-10.png?raw=true)
11. ![user-story-11](https://github.com/HackXIt/SLM-Study-Project/blob/main/doc/attachments/user-story-11.png?raw=true)
12. ![user-story-12](https://github.com/HackXIt/SLM-Study-Project/blob/main/doc/attachments/user-story-12.png?raw=true)
13. ![user-story-13](https://github.com/HackXIt/SLM-Study-Project/blob/main/doc/attachments/user-story-13.png?raw=true)
14. ![user-story-14](https://github.com/HackXIt/SLM-Study-Project/blob/main/doc/attachments/user-story-14.png?raw=true)