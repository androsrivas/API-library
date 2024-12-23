
# API Library

This is an API Rest for a library developed with Spring Boot. The application allows to manage books, members, and loans. 




## Main functionalities

- Register members.
- Manage members.
- Add books to catalog.
- Manage books from catalog.
- Loan books to members.
- Return borrowed books.
- Check loan history of each member.

## Main entities
- Book: id, title, authors, isbn, description, genres. 
- Member: id, fullName, email, password.
- Loan: id, member, book, borrwedDate, returnDate. 


## Installation

Clone this project

```bash
  git clone https://github.com/androsrivas/API-library.git
```

Config ```application.properties``` file: 
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/api_library
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

Compile and execute application:
```bash
mvn spring-boot:run
```

Application will be available in:
```bash
http://localhost:8080
```
    
## API endpoints

#### Get all books
```http
  GET /api/v1/books
```


#### Get a book by id
```http
  GET /api/v1/books/{id}
```

#### Create a book
```http
  POST /api/v1/books
```

#### Update a book
```http
  PUT /api/v1/books/{id}
```

#### Delete a book
```http
  DELETE /api/v1/books/{id}
```

#### Get all members
```http
  GET /api/v1/members
```

#### Get a member by id
```http
  GET /api/v1/members/{id}
```

#### Update a member
```http
  PUT /api/v1/members/{id}
```

#### Delete a member
```http
  DELETE /api/v1/members/{id}
```



## Authors

- [@androsrivas](https://www.github.com/androsrivas)

