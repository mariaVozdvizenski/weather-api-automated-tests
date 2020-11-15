# Theory

## 1. Which of the following activities cannot be automated
   
  - [ ] Test execution
  - [x] Exploratory testing
  - [x] Discussing testability issues
  - [ ] Test data generation
  
## 2. How do we describe a good unit test?

  - [ ] Flawless, Ready, Self-healing, True, Irresistible
  - [ ] Red, Green, Refactor
  - [x] Fast, Repeatable, Self-validating, Timely, Isolated
  - [ ] Tests should be dependent on other tests
  
## 3. When is it a good idea to use XPath selectors

  - [ ] When CSS or other selectors are not an option or would be brittle and hard to maintain   
  - [ ] When we need to find an element based on parent/child/sibling relationship  
  - [ ] When an element is located deep within the HTML (or DOM) structure  
  - [x] All the above
  
## 4. Describe the TDD process
   
   TDD involves writing tests first and code after.<br/>
   For example, a test is written for some kind of function that doesn't have an implementation yet.
   As expected, the test fails when running it. The next step is to write the function in a way that makes the test pass.
   Then refactor the code after the test has passed to make it better.

## 5. Write 2 test cases or scenarios for a String Calculator application, which takes a string of two numbers separated by a comma as input.
   
   **Given** the input "1" **When** the method calculate() is called **Then** I should see "1" as a result. <br/>
   **Given** the input "2, &nbsp;&nbsp;7&nbsp;" **When** the method calculate() is called **Then** I should see "9" as a result.



      
