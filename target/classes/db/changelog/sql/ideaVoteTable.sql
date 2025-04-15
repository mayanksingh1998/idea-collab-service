-- changeset mayank:create-idea-vote-table
CREATE TABLE "idea_vote" (
                             "id" VARCHAR(50) NOT NULL,
                             "idea_id" VARCHAR(50) NOT NULL,
                             "employee_id" VARCHAR(50) NOT NULL,
                             "created_at" TIMESTAMP WITHOUT TIME ZONE NOT NULL,
                             "vote_status" VARCHAR(50) NOT NULL,
                             CONSTRAINT "idea_vote_id_pk" PRIMARY KEY ("id"),
                             CONSTRAINT "fk_idea_vote_idea" FOREIGN KEY ("idea_id") REFERENCES "idea"("id") ON DELETE CASCADE,
                             CONSTRAINT "fk_idea_vote_employee" FOREIGN KEY ("employee_id") REFERENCES "employee"("id") ON DELETE CASCADE,
                             CONSTRAINT "uk_idea_vote_idea_employee" UNIQUE ("idea_id", "employee_id")
);

-- Indexes for performance
CREATE INDEX idx_idea_vote_idea_id ON idea_vote(idea_id);
CREATE INDEX idx_idea_vote_employee_id ON idea_vote(employee_id);
CREATE INDEX idx_idea_vote_vote_status ON idea_vote(vote_status);
