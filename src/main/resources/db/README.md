### DATABASE MIGRATION
## Tech Stack

    Liquibase - For managing and tracking database schema changes.
    SQL - Structured Query Language for defining, modifying, and querying data.
    MySQL - Database management system.

## Usage

    Automatically migrate or create databases based on changelog files.
    Manage database schema history to maintain consistency across environments.

## Example Folder Structure

Below is an example of how to organize Liquibase changelog files for tracking changes to specific tables.

## sql

```
db/changelog/
├── db.changelog-master.yml
├── table-user/
│   ├── changeset-002.sql
│   ├── changeset-001.sql
│   └── changelog-master.yml
├── table-role/
│   ├── changeset-001.sql
│   └── changelog-master.yml
```

<!-- Replace with the actual path to your image -->
### How to Add a Table
```sql
    Create SQL Changeset File:
        Create a new SQL file for the table, such as changeset-003.sql, in the respective folder (e.g., table-user/).
        Define the CREATE TABLE statement in the SQL file.

    sql

-- changeset author:id
CREATE TABLE user (
    id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);
```

### Update changelog-master.yml for the Table:

    In table-user/changelog-master.yml, add a reference to the new SQL file.

```yaml

databaseChangeLog:
  - include:
      file: changeset-003.sql
      relativeToChangelogFile: true
```
Reference in Main Changelog:

    Ensure db.changelog-master.yml includes table-user/changelog-master.yml to apply the changes.

```yaml

    databaseChangeLog:
      - include:
          file: table-user/changelog-master.yml
      - include:
          file: table-role/changelog-master.yml
```
### How to Modify/Alter a Table

    Create SQL Changeset for Alteration:
        In the same folder for the table, add a new SQL file, like changeset-004.sql, with the ALTER TABLE command.

```sql

-- changeset author:id
ALTER TABLE user ADD COLUMN age INT;

### Update the Table’s changelog-master.yml:

    Add the new changeset in table-user/changelog-master.yml.
```
```yaml

databaseChangeLog:
  - include:
      file: changeset-004.sql
      relativeToChangelogFile: true

Run the Migration:

    Execute the Liquibase CLI command:
```
```bash

    liquibase --changeLogFile=db/changelog/db.changelog-master.yml update
```
This will add and alter tables in the database based on the changes defined in the SQL files.

- Author -Tavie