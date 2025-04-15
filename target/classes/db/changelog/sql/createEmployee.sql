-- changeset mayank:create-employee-table
CREATE TABLE "employee" (
                            "id" VARCHAR(50) NOT NULL,
                            "name" VARCHAR(100) NOT NULL,
                            "email" VARCHAR(100) UNIQUE NOT NULL,
                            "department" VARCHAR(100),
                            "location" VARCHAR(100),
                            "manager_id" VARCHAR(50),
                            "employee_role" VARCHAR(100),
                            "end_date" DATE,
                            "created_at" TIMESTAMP WITHOUT TIME ZONE NOT NULL,
                            "updated_at" TIMESTAMP WITHOUT TIME ZONE NOT NULL,
                            CONSTRAINT "employee_id_pk" PRIMARY KEY ("id")
);

-- Indexes (except for id, since PK already covers it)
CREATE INDEX idx_employee_email ON employee(email);
CREATE INDEX idx_employee_department ON employee(department);
CREATE INDEX idx_employee_manager_id ON employee(manager_id);
