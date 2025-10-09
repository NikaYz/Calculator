# 🧮 Scientific Calculator CI/CD Pipeline

This project automates the **build, test, Dockerization, and deployment** of a Java-based Scientific Calculator using **Jenkins**, **Docker**, and **Ansible**.

---

## ⚙️ Pipeline Stages
1. **Checkout** code from GitHub  
2. **Build & Test** using Maven  
3. **Package** the `.jar` file  
4. **Build Docker Image**  
5. **Run & Verify** container  
6. **Push Image** to Docker Hub  
7. **Deploy** using Ansible  

---

## 🧰 Jenkinsfile Highlights
- Uses **Maven** (`clean compile test package`)  
- Builds Docker image `scientific-calculator:1.0`  
- Pushes to Docker Hub as `nikayz/scientific-calculator:1.0`  
- Deploys with Ansible  
- Sends email notifications on success/failure  

---

## 🐧 Ansible Playbook
- Installs Docker (if not present)  
- Pulls the latest Docker image  
- Runs container and executes calculator commands  
- Displays output on terminal  

---
