-- changeset mayank:create-collaboration-request-table
CREATE TABLE "collaboration_request" (
                                         "id" VARCHAR(50) NOT NULL,
                                         "idea_id" VARCHAR(50) NOT NULL,
                                         "employee_id" VARCHAR(50) NOT NULL,
                                         "request_at" TIMESTAMP WITHOUT TIME ZONE NOT NULL,
                                         "description" TEXT NOT NULL,
                                         "status" VARCHAR(50) NOT NULL,
                                         CONSTRAINT "collaboration_request_id_pk" PRIMARY KEY ("id"),
                                         CONSTRAINT "fk_collab_request_idea" FOREIGN KEY ("idea_id") REFERENCES "idea"("id") ON DELETE CASCADE,
                                         CONSTRAINT "fk_collab_request_employee" FOREIGN KEY ("employee_id") REFERENCES "employee"("id") ON DELETE CASCADE,
                                         CONSTRAINT "uk_collab_request_idea_employee" UNIQUE ("idea_id", "employee_id")
);

-- Indexes for performance
CREATE INDEX idx_collab_request_idea_id ON collaboration_request(idea_id);
CREATE INDEX idx_collab_request_employee_id ON collaboration_request(employee_id);
CREATE INDEX idx_collab_request_status ON collaboration_request(status);
