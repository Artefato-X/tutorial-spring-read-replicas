# 🎬 Spring JPA Read-Write Splitting Tutorial

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-LATEST-blue)](https://www.postgresql.org/)
[![Java](https://img.shields.io/badge/Java-17%2B-orange)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/license-MIT-important)](LICENSE)

## 📺 Assista ao Passo a Passo no YouTube
[![Assista no YouTube](https://img.shields.io/badge/YouTube-Assistir%20Tutorial-red?style=for-the-badge&logo=youtube)](URL_DO_SEU_VIDEO_AQUI)

### 🚀 O que é este projeto?

Este repositório é um guia prático para implementar a separação de tráfego de **Escrita (Primary)** e **Leitura (Replica)** em aplicações Spring Boot. 

O objetivo é demonstrar como escalar horizontalmente sua camada de dados, direcionando consultas pesadas para réplicas de leitura e preservando o banco de dados principal para operações críticas de escrita.

---

## 🏗️ Arquitetura de Roteamento

A lógica central utiliza o `AbstractRoutingDataSource` do Spring. O sistema identifica o contexto da transação e decide dinamicamente qual conexão entregar ao Hibernate:

* ✍️ **@Transactional** (padrão/readOnly=false) ➡️ Direciona para o banco **PRIMARY**.
* 📖 **@Transactional(readOnly = true)** ➡️ Direciona para o banco **REPLICA**.



---

## 🛠️ Tecnologias e Configurações

### Stack Tecnológica
* **Java 17+** & **Spring Boot 3**
* **Spring Data JPA** & **Hibernate**
* **PostgreSQL** (Configurado como Primary & Replica)
* **Lombok** (Produtividade)
* **MDC (Mapped Diagnostic Context)** para Logs inteligentes

### Log Inteligente com MDC
Configuramos um pattern de log customizado que injeta o identificador do banco de dados em uso. Isso permite validar o roteamento visualmente no console:

**Pattern no `application.properties`:**
```yaml