-- changeset mayank:drop-unique-constraint-collaboration-request
ALTER TABLE collaboration_request
DROP CONSTRAINT uk_collab_request_idea_employee;
