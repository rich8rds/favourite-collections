/* Collections #2024 */
package com.favourite.collections.commons.useradmin.data;

import java.util.Set;

import com.favourite.collections.commons.core.domain.AbstractPersistableCustom;
import jakarta.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CodeData extends AbstractPersistableCustom {

	@NotNull(message = "Code data name is required") private String name;

	private boolean systemDefined;
	private Integer externalUse;
	private Set<CodeValueData> codeValueData;
}
