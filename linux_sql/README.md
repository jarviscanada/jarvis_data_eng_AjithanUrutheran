# Linux Resource Cluster Monitoring Agent

# Introduction
The Linux resource cluster monitoring agent is a minimum viable product (MVP) that will monitor the resources across a cluster environment. Many bash scripts will execute in order to perform tasks such as registering a host container's information as well as updating a host container's usage in real time. The users of this program will be the Linux Cluster Administration team (LCA). The LCA will use this program to monitor the nodes in our Linux cluster and collect information to make load- and storage-balancing decisions. Technologies that are explored in this program include Git (version control), Linux bash script, docker (hosting and containerizing database), and PostgreSQL (storing and querying data).

# Quick Start
1. Start a psql instance using psql_docker.sh

      How do we create a psql instance if we do not have one?
      ``` bash
     ./scripts/psql_docker.sh create [username] [password]
      ```
      If a psql docker instance has been created, start or stop the instance using the following command:
     ``` bash
     ./scripts/psql_docker.sh (start | stop)
     ```
2. Create host_info and host_usage table in DB using ddl.sql bash script
     ``` bash
     psql -h localhost -U postgres -p 5432 -d host_agent -f sql/ddl.sql
     ```
3. Insert hardware specs data into the DB using the host_info.sh bash script
    ``` bash
     ./scripts/host_info.sh psql_host psql_port db_name psql_user psql_password
    ```
4. Insert hardware usage data into the DB using the host_usage.sh bash script
   ``` bash
    ./scripts/host_usage.sh psql_host psql_port db_name psql_user psql_password
   ```
5. Setup Crontab to periodically collect hardware usage data for current psql instance in one-minute intervals

      Create a crontab job using the following command:
      ``` bash
     crontab -e
      ```
      Edit the crontab job and insert the following command:
     ``` bash
     * * * * * bash /home/centos/dev/jrvs/bootcamp/linux_sql/host_agent/scripts/host_usage.sh
     ```
      How do we stop the crontab job?
      >Stop the crontab job by editting and removing the job or stopping the crond service.

      Why are the files not executable?
      >Use chmod to alter permissions of files and add executable permissions if they are missing.
# Implementation
The Linux resource cluster monitoring agent program was created through containerizing a psql instance using docker. Bash scripts we're built to provide the psql instance in the container with functionalities. Once the container was running, ddl.sql was utilized for table creation (host_info and host_usage). The host's hardware information was inserted into the DB by running the host_info.sh script. The host's resource usage was inserted and stored as well by running the host_usage.sh script. In order to automate collecting the host's resource usage, a crontab job was created to run the host_usage.sh script at minute intervals. This allowed for real time population and updates to our psql instance.
# Architecture
![Linux resource cluster monitoring agent architecture](./assets/architecture.png)


# Scripts

## psql_docker.sh
      -Used to create postgres database instance inside a docker container
      -List of arguements
                  -Create (creates the psql container provided a username and password)
                  -Start (starts the psql container)
                  -Stop (stops the psql container)

## ddl.sql
      -Used to create the schema of the host_info and host_usage tables to be used in application
      -Allows for user to store data collected from bash scripts (host_info.sh and host_usage.sh)
## host_info.sh
      -Collects and inserts hardware specifications of host device into host_info table in database
      -Only collected once per container
      -Must specify host, port, db_name, username and password as arguements when running
## host_usage.sh
      -Periodically collects and inserts hardware usage information of host device into database
      -Automated using crontab job as to monitor usage data in real time
      -Must specify host, port, db_name, username and password as arguements when running
## Crontab
      -Automates running of host_usage.sh script to collect hardware usage data every minute

# Database Modeling

### Host Info Table
| Column Name | Data Type | Constraint |
|-------------|-----------|------------|
|id           |SERIAL     | Primary Key|
|hostname     |VARCHAR    | NOT NULL, UNIQUE |
|cpu_number | INT2 | NOT NULL |
|cpu_architecture | VARCHAR | NOT NULL |
|cpu_model | VARCHAR | NOT NULL |
|cpu_mhz | FLOAT8 | NOT NULL |
|l2_cache | INT4 | NOT NULL |
|timestamp | TIMESTAMP | NULL |
|total_mem | INT4 | NULL |

### Host Usage Table
| Column Name | Data Type | Constraint |
|-------------|-----------|------------|
|timestamp | TIMESTAMP | NOT NULL |
|host_id | SERIAL | FOREIGN KEY |
|memory_free | INT4 | NOT NULL |
|cpu_idle | INT2 | NOT NULL |
|cpu_kernel | INT2 | NOT NULL |
|disk_io | INT4 | NOT NULL |
|disk_available | INT4 | NOT NULL |


# Test
      Testing was performed for this project by first inserting sample queries into the database to test the schema. 
      Once the schema proved to support test queries, the tables were cleared, and the host_info.sh script was run. 
      Updates to the table were checked by running SELECT * FROM HOST_INFO;. This command was successful as it
      displayed the current host's hardware specifications. A crontab job was created and ran periodically to test
      if the host usage data was inserted into the host_usage table. This also proved to be successful as
      SELECT * FROM HOST_USAGE; returned updated usage data with recent time stamps.
# Deployment
      The program was deployed using Docker, Crontab, Git and Github.
      -Docker (Utilized for hosting PostgreSQL databases)
      -Crontab (Used to periodically execute bash scripts)
      -Git (Used for version control locally)
      -Github (Used to store release branch that is ready to be installed on any peers on the network)
# Improvements
- **Cleaning up old data:** Find the average of old usage data and combine them into an average tuple and delete old records to save space on the device. Allows for very old data to be stored in 1 tuple rather than 1440 tuples for every minute of the old day.
- **Providing or taking stray resources:** Create an upper and lower bound threshold for hardware usage. If usage is under lowerbound, take away resources from the container and update bounds. If usage is above the upperbound, increase the resources supplied to the container and update the bounds.
- **Alerting if usage is nearing full capacity**: Allows the LCA team to be notified and to take appropriate measures to prevent any faults in the program.


