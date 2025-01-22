/* Collections #2024 */
package com.favourite.collections.commons.core.service;


import com.favourite.collections.commons.useradmin.domain.AppUser;

public interface PlatformContextService {
	AppUser authenticatedUser();

	AppUser authenticatedUserForAudit();
}
