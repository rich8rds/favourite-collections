/* Collections #2024 */
package com.favourite.collections.infrastructure.core.service;

import com.favourite.collections.infrastructure.core.domain.AppUser;

public interface PlatformContextService {
	AppUser authenticatedUser();

	AppUser authenticatedUserForAudit();
}
