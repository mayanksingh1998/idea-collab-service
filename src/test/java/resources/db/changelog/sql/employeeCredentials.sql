-- changeset mayank:create-employee-credential-table
CREATE TABLE employee_credential (
                                     id VARCHAR(36) PRIMARY KEY,
                                     employee_email VARCHAR(100) NOT NULL,
                                     employee_id VARCHAR(50),
                                     password_hash VARCHAR(255) NOT NULL,
                                     is_active BOOLEAN NOT NULL,
                                     created_at TIMESTAMP NOT NULL,
                                     updated_at TIMESTAMP NOT NULL,
                                     CONSTRAINT fk_employee_credential_employee
                                         FOREIGN KEY (employee_id) REFERENCES employee(id)
);

-- Index on employee_email
CREATE INDEX idx_employee_credential_email ON employee_credential(employee_email);
