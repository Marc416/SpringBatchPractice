# Use the official MariaDB image as the base image
FROM mariadb:latest

# Set the root password for MariaDB
ENV MYSQL_ROOT_PASSWORD=root1234

# Create a database and user
ENV MYSQL_DATABASE=batch_database
ENV MYSQL_USER=root

# Expose the MySQL port
EXPOSE 3306

#docker build -t spring-batch-practice-image .
#docker run -d --name spring-batch-practice-container -p 2306:3306 spring-batch-practice-image
