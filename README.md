TUTORIAL 1
---
Reflection 1

Clean code principles and secure coding practices that have been applied to my code:
- I have used meaningful variable and function names to make the code more readable and understandable.
- Functions are short and do only one thing.
- Functions that have no side effects.
- Functions do not return null.
- I don't use comments because the code is self-explanatory.

Because there is no authorization or authentication, anyone can brute force the URL link and change the list. To improve the source code, i would add some security so that other random people dont easily change the list

Reflection 2

1. After making the unit test, i feel confident in my code's functionality. The number of unit tests in a class can vary depending on the complexity of the class. It is best to have enough tests to cover different scenarios and edge cases. While having 100% code coverage is good, it does not guarantee that there are no bugs or errors in the code. It only indicates that all lines of code have been executed at least once during testing without errors or bugs.

2. I dont think the code is considered clean and could possibly reduce the code quality. If it were to happen, there could be code duplication, Low cohesion between each class, and high coupling. Code duplication can lead to harder maintenance. Low cohesion is If a class is doing too much or not well-defined, it can become harder to understand and difficult to maintain. High Coupling is when you change something in one class and the other class needs to change because of you changed something in the first class. High coupling can make the code more fragile and harder to be developed. I would suggest using encapsulation, single responsibility principle, descriptive names, code formatting, and refactoring. These practices can help to make code cleaner, readable and easy to maintain.

TUTORIAL 2
---
Reflection 
1. Code quality issues that i have encountered and fixed during the tutorial:
    - There were unused imports in the code. I fixed it by removing the unused imports.
    - There was an error in ProductControllerTest.java because i have 2 classes (HomeController and ProductController). I fixed it by seperating them into 2 files.

2. In my opinion, my CI/CD workflow implementation has met the definition of CI/CD because when pushed to the github repository, the code will be automatically built and tested using unit test. The code will also be checked for cleanliness. If the code is clean and passes the tests, it will be deployed to the koyeb server. This process is done automatically and continuously.

TUTORIAL 3
---
Reflection
1. SOLID principles that i have applied in my code:
    - Single Responsibility Principle
    - Interface Segregation Principle
    - Dependency Inversion Principle
2. The benefits of using SOLID principles in my code:
    - The code is easier to maintain and extend.
        * I can easily add new features to the application without changing the existing code.
    - The code is easier to understand and read.
        * I can understand the code that i have made more easily because the code is more organized and structured.
    - The code is more flexible and reusable.
        * I can reuse FindById method in different parts of the application.
    - The code is more testable and has fewer bugs.
        * I have seperated testing for HomeController and ProductController.
3. The disadvantage of not using SOLID principles in my code:
    - The code is harder to maintain and extend.
        * I have to change the existing code to add new features to the application.
    - The code is harder to understand and read.
        * I have to spend more time to understand the code that i have made.
    - The code is less flexible and reusable.
        * I can't reuse FindById method in different parts of the application.
    - The code is less testable and has more bugs.
        * There will be error because if i didn't seperate them, one of them wouldn't be found when testing.

TUTORIAL 4
---
Reflection
1. In my opinion, this TDD flow is useful because it helps me to write the test first and then write the code. This way, i can focus on the requirements and the expected behavior of the code. It also helps me to write the code that is easy to test and maintain. I can also make sure that the code is working as expected by running the test. This way, i can avoid writing unnecessary code and make sure that the code is working as expected. It helps to do refactoring and make the code cleaner and more readable. It also helps me to find and fix the bugs early in the development process.
2. In my opinion, the tests have successfully followed the F.I.R.S.T principles because the tests are fast, independent, repeatable, self-validating, and timely. The tests are fast because they are designed with algorithms to be executed quickly. The tests are independent because each tests don't depend on the other tests. The tests are repeatable because they produce the same result every time they are run in any environment. The tests are self-validating because each tests have assertions for boolean output, either they pass or fail. The tests are timely because they are written before the code and run frequently.