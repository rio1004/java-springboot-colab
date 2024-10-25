IF EXISTS (
    SELECT 1
    FROM information_schema.tables
    WHERE
        table_name = 'user'
) THEN
-- Check if the 'role_user' table does not exist
IF NOT EXISTS (
    SELECT 1
    FROM information_schema.tables
    WHERE
        table_name = 'role_user'
) THEN
-- Create the 'role_user' table
CREATE TABLE role_user (
    id SERIAL PRIMARY KEY,
    user_id INT UNIQUE,
    name VARCHAR(45),
    role INT UNIQUE,
    FOREIGN KEY (user_id) REFERENCES "user" (id) -- Quoted to handle reserved word
);

END IF;

END IF;