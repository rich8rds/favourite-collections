/* Collections #2024 */
package com.favourite.collections.commons.useradmin.data;


import com.favourite.collections.commons.core.domain.AbstractPersistableCustom;
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
public class CodeValueData extends AbstractPersistableCustom {

	private String label;
	private Integer position;
	private String description;
	private String codeId;
	private Boolean isActive;
	private Boolean mandatory;
}
