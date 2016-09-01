package com.liferay.training.service.builder.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.training.service.builder.model.TrackerEntry;

public class TrackerEntryPermission {

    public static void check(PermissionChecker permissionChecker, long groupId,
        String actionId) throws PortalException {

        if (!contains(permissionChecker, groupId, actionId)) {
            throw new PrincipalException();
        }
    }

    public static boolean contains(PermissionChecker permissionChecker,
        long groupId, String actionId) {

        return permissionChecker.hasPermission(
        	groupId, _RESOURCE_NAME, groupId, actionId);
    }

    public static final String _RESOURCE_NAME = TrackerEntry.class.getName();
}
