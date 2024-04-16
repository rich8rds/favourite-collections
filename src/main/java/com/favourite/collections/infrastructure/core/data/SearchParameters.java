/* Richards-Favour #2024 */
package com.favourite.collections.infrastructure.core.data;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearchParameters {
	private Long id;
	private Boolean showCodeValues;
	private String name;
	private Integer offset;
	private Integer limit;
	private String sortOrder;
	private String orderBy;
	private String category;
	private String subcategory;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
}
