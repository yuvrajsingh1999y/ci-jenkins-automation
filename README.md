# Continuous Integration and Delivery with Jenkins for Automated Tests

## 🔧 Tools Used
- Java, Maven, Selenium, TestNG
- Jenkins (CI)
- GitHub (Version Control)
- Postman (API Testing)

## ✅ How to Run
### 1. Selenium Tests
- Open the project in IntelliJ
- Run `mvn clean test`
- ChromeDriver must be installed and in your system path

### 2. Postman Tests
- Import `ci-api-tests.postman_collection.json` into Postman
- Run the collection manually or via Newman (CLI)

### 3. Jenkins CI
- Create a Pipeline job
- Paste the contents of `Jenkinsfile`
- Connect to your GitHub repo
- Run the pipeline and view reports
