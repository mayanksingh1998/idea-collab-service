package com.finbox.idea_collab_service.helper;

import com.finbox.idea_collab_service.dto.enums.CollaborationRequestAction;
import com.finbox.idea_collab_service.entity.RequestStatus;
import org.springframework.stereotype.Component;

@Component
public class IdeaCollaborationServiceHelper {


    public RequestStatus getRequestStatusFromCollaborationActions(CollaborationRequestAction action) {
        if (action == null) {
            return null;
        }
        switch (action) {
            case PENDING:
                return RequestStatus.PENDING;
            case ACCEPTED:
                return RequestStatus.ACCEPTED;
            case REJECTED:
                return RequestStatus.REJECTED;
            default:
                throw new IllegalArgumentException("Invalid collaboration status: " + action);
        }
    }
}
