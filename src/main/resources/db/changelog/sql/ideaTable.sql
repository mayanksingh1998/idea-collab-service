-- changeset mayank:create-idea-table
CREATE TABLE "idea" (
                        "id" VARCHAR(50) NOT NULL,
                        "title" VARCHAR(255),
                        "description" TEXT NOT NULL,
                        "created_by_id" VARCHAR(50),
                        "created_at" TIMESTAMP WITHOUT TIME ZONE NOT NULL,
                        "updated_at" TIMESTAMP WITHOUT TIME ZONE NOT NULL,
                        "status" VARCHAR(50) NOT NULL,
                        "votes_count" INTEGER NOT NULL DEFAULT 0,
                        CONSTRAINT "idea_id_pk" PRIMARY KEY ("id"),
                        CONSTRAINT "fk_idea_created_by" FOREIGN KEY ("created_by_id") REFERENCES "employee"("id")
);

CREATE INDEX idx_idea_created_by_id ON idea(created_by_id);
CREATE INDEX idx_idea_status ON idea(status);

-- changeset mayank:create-tag-table
CREATE TABLE "tag" (
                       "id" VARCHAR(50) NOT NULL,
                       "name" VARCHAR(100) UNIQUE NOT NULL,
                       CONSTRAINT "tag_id_pk" PRIMARY KEY ("id")
);

-- changeset mayank:create-idea-tag-table
CREATE TABLE "idea_tag" (
                            "idea_id" VARCHAR(50) NOT NULL,
                            "tag_id" VARCHAR(50) NOT NULL,
                            CONSTRAINT "idea_tag_pk" PRIMARY KEY ("idea_id", "tag_id"),
                            CONSTRAINT "fk_idea_tag_idea" FOREIGN KEY ("idea_id") REFERENCES "idea"("id") ON DELETE CASCADE,
                            CONSTRAINT "fk_idea_tag_tag" FOREIGN KEY ("tag_id") REFERENCES "tag"("id") ON DELETE CASCADE
);

CREATE INDEX idx_idea_tag_idea_id ON idea_tag(idea_id);
CREATE INDEX idx_idea_tag_tag_id ON idea_tag(tag_id);
