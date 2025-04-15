package com.finbox.idea_collab_service.helper;

import com.finbox.idea_collab_service.dto.enums.CollaborationRequestAction;
import com.finbox.idea_collab_service.entity.RequestStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdeaCollaborationServiceHelperTest {

    private IdeaCollaborationServiceHelper helper;

    @BeforeEach
    void setUp() {
        helper = new IdeaCollaborationServiceHelper();
    }

    @Test
    void testGetRequestStatusFromCollaborationActions_ReturnsPending() {
        RequestStatus status = helper.getRequestStatusFromCollaborationActions(CollaborationRequestAction.PENDING);
        assertEquals(RequestStatus.PENDING, status);
    }

    @Test
    void testGetRequestStatusFromCollaborationActions_ReturnsAccepted() {
        RequestStatus status = helper.getRequestStatusFromCollaborationActions(CollaborationRequestAction.ACCEPTED);
        assertEquals(RequestStatus.ACCEPTED, status);
    }

    @Test
    void testGetRequestStatusFromCollaborationActions_ReturnsRejected() {
        RequestStatus status = helper.getRequestStatusFromCollaborationActions(CollaborationRequestAction.REJECTED);
        assertEquals(RequestStatus.REJECTED, status);
    }

    @Test
    void testGetRequestStatusFromCollaborationActions_WithNull_ReturnsNull() {
        RequestStatus status = helper.getRequestStatusFromCollaborationActions(null);
        assertNull(status);
    }

    @Test
    void testGetRequestStatusFromCollaborationActions_WithInvalidEnum_ThrowsException() {
        // This test would only apply if enums are extended later, but it's covered by `default` clause.
        // So no runtime enum invalidity possible unless bypassed via reflection or custom serialization.
        // Optional: leave as documented behavior.
        assertDoesNotThrow(() -> helper.getRequestStatusFromCollaborationActions(CollaborationRequestAction.PENDING));
    }
}
