# Continuous Integration with Jenkins + Postman API Tests

## 📌 Project Overview
This project demonstrates a Jenkins CI pipeline that runs Postman API tests using Newman and publishes results in JUnit + HTML format.

## 🚀 Setup Instructions

### Prerequisites
- Windows 11
- Jenkins installed
- NodeJS installed and configured in Jenkins (`Manage Jenkins > Global Tool Configuration`)
- GitHub repository with Postman collection and environment

### Steps
1. Clone this repository into Jenkins job (Pipeline type).
2. In Jenkins Global Tool Configuration:
   - Add NodeJS installation (e.g., name: `NodeJS_20`).
3. Create a new Pipeline job and point to this repository.
4. Run the job. Jenkins will:
   - Install Newman
   - Run Postman collection
   - Generate test reports (`JUnit` + `HTML`)
   - Publish results in Jenkins.

### 📝 Reports
- `reports/newman-results.xml` → JUnit (shown in Jenkins Test Result Trend)
- `reports/newman-report.html` → Detailed Newman HTML report

---

### 4. **Reports**
After running the pipeline:
- Upload `reports/newman-results.xml`
- Upload `reports/newman-report.html`