# Linux Resource Cluster Monitoring Agent

# Introduction
The Linux resource cluster monitoring agent is a minimum viable product (MVP) that will monitor the resources across a cluster environment. Many bash scripts will execute in order to perform tasks such as registering a host container's information as well as frequently updating a host container's usage. The users of this program will be the Linux Cluster Administration team (LCA). The LCA will use this program to manage the many nodes in our Linux cluster. Technologies that are explored in this program include Git (Version Control), Linux bash script, docker (hosting and containerizing database), and PostgreSQL (storing and querying data).

# Quick Start
1. Start a psql instance using psql_docker.sh

      How do we create a psql instance if we do not have one?
      ``` bash
     ./scripts/psql_docker.sh create [username] [password]
      ```
      If a psql docker instance has been created, start/stop the instance using the following command.
     ``` bash
     ./scripts/psql_docker.sh (start | stop)
     ```
2. Create host_info and host_usage table in DB using ddl.sql bash script
     ``` bash
     psql -h localhost -U postgres -p 5432 -d host_agent -f sql/ddl.sql
     ```
3. Insert hardware specs data into the DB using host_info.sh bash script
    ``` bash
     ./scripts/psql_docker.sh (start | stop)
    ```
4. Insert hardware usage data into the DB using host_usage.sh bash script
