# Insider QA Automation Project

This is a UI automation project targeting the [Insider Careers](https://useinsider.com/careers/quality-assurance/) page.

It verifies:
- Navigation flows and dropdown behavior
- QA job listing filters by department and location
- External redirection to Lever job application platform

---

## 🛠 Tech Stack

- **Language:** Java 17+
- **Test Framework:** TestNG
- **Browser Automation:** Selenium WebDriver
- **Design Pattern:** Page Object Model (POM)
- **Build Tool:** Maven
- **Driver Manager:** WebDriverManager
- **Reports:** TestNG built-in
- **IDE:** IntelliJ IDEA (recommended)

---

## 🚀 How to Run Tests

### 🔧 Prerequisites

- Java 17+ installed
- Maven installed (`mvn -v` to verify)
- IntelliJ IDEA (recommended for execution)

---

### ▶️ Option 1: Run via IntelliJ IDEA

#### ✅ Run all tests via `testng.xml`

1. Open the project in IntelliJ
2. Locate `testng.xml` in the Project panel (in the root folder or `/src/test/resources`)
3. Right-click on the file → **Run 'testng.xml'**
4. All tests in the suite will run through TestNG

#### ✅ Run an individual test class

1. Open any test class from the `tests` package (e.g., `QaJobsPageTest.java`)
2. Click the **▶️** icon next to the class or method
3. Select **Run** to execute that specific test

---

### ▶️ Option 2: Run via Maven

Run all tests from the terminal using:

```bash
mvn clean test
```

![web-automation-screen-record.gif](screen-recordings/web-automation-screen-record.gif)