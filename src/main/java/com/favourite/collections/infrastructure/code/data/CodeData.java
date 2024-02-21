/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.infrastructure.code.data;

import com.favourite.collections.infrastructure.core.domain.AbstractPersistableCustom;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CodeData extends AbstractPersistableCustom {

	@NotNull(message = "Code data name is required")
	private String name;
	private boolean systemDefined;
	private Integer externalUse;
	private Set<CodeValueData> codeValueData;
}
