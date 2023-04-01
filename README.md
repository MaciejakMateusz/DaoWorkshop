# DAO Database Management (CRUD)

Java program for managing users database through console.

## Features

1. Create users
2. Read - search database by ID, email, username or read all users
3. Update users
4. Delete users
5. Hashing passwords that enters the database

## Usage

Program starts with the main menu, in which command has to be entered:

```bash
Main menu
Choose one of the following commands:
create
read
update
delete
exit
```

### create

When 'create' is entered, the program will ask for email, username and password:

```bash
create
Enter email: dao@crud.com
Enter username: DatabaseManagement
Enter password: JDBC123
Confirm password: JDBC123
```

### read

When 'read' is entered, submenu will show up:

```bash
read
Reading the database function.
Enter one of the following commands:
read-all
id
email
username
back
```

Command 'read-all' will show every user in the database. The other commands are 
for searching the database by ID, email, username. Command 'back' will bring
to the main menu of course.

### update

When 'update' is entered, the program will ask for ID of the user that has to
be updated:

```bash
update
Enter id of the user you wish to update.
Enter id: 
```

When correct ID is entered, the program will read the user from database, and ask
what should be updated:

```bash
update
Enter id of the user you wish to update.
Enter id: 12
User id = 12 | email = 'dao@crud.com' | userName = 'DatabaseManagement' | password = '$2a$10$vF58ntr/xj5olFMLkOHGrOVwwIxLMTH7hwsP33.7GySQn/TS7oONm'
What data should be updated?  Enter one of the following:
email
username
password
```

### delete

When 'delete' is entered, the program will ask for ID of the user that has to
be deleted:

```bash
delete
Delete user from database function
Enter id of the user to delete: 
```

When the right ID is given, program will print confirmation.

## Author
*Mateusz Maciejak*