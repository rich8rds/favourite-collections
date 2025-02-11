/* Collections #2024 */
package com.favourite.collections.commons.useradmin.data;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.favourite.collections.commons.core.domain.AbstractPersistableCustom;
import jakarta.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CodeData extends AbstractPersistableCustom {

	@NotNull(message = "Code data name is required") private String name;

	private boolean systemDefined;
	private Integer externalUse;
	private Set<CodeValueData> codeValueData;
}
