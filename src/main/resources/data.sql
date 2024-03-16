CREATE TABLE IF NOT EXISTS Patient (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    age VARCHAR(50),
    gender VARCHAR(50),
    disease VARCHAR(255)
);
INSERT INTO Patient (name, age, gender, disease) VALUES
('John Doe', '30', 'Male', 'Fever'),
('Jane Smith', '25', 'Female', 'Headache'),
('Michael Johnson', '40', 'Male', 'Back pain');