/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.portfolio.product.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.favourite.collections.portfolio.product.data.ProductData;
import com.favourite.collections.portfolio.product.util.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.favourite.collections.infrastructure.core.data.SearchParameters;
import com.favourite.collections.portfolio.product.domain.Product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {
	@PersistenceContext
	private final EntityManager em;
	private final ModelMapper modelMapper = new ModelMapper();

	@Override
	public Page<ProductData> findBy(SearchParameters searchParameters) {
		//log.info("SEARCH_PARAMETERS: {}", searchParameters);
		Long id = searchParameters.getId();
		Integer limit = searchParameters.getLimit();

		String name = searchParameters.getName();
		Integer offset = searchParameters.getOffset();
		String sortOrder = searchParameters.getSortOrder();
		String orderBy = searchParameters.getOrderBy();

		LocalDateTime requestStartDate = searchParameters.getStartDate();
		LocalDateTime requestEndDate = searchParameters.getEndDate();

		offset = offset <= 0 ? 1 : offset;
		Sort.Direction direction = sortOrder.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
		PageRequest pageRequest = PageRequest.of((offset - 1), limit, Sort.by(direction, orderBy));

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);

		Root<Product> nxConnectDBroot = cq.from(Product.class);
		List<Predicate> predicates = new ArrayList<>();
		if (id != null) {
			predicates.add(cb.equal(nxConnectDBroot.get("id"), id));
		}

		if (name != null) {
			predicates.add(cb.equal(nxConnectDBroot.get("name"), name));
		}

		if (requestEndDate != null && requestStartDate != null) {
			predicates.add(cb.between(nxConnectDBroot.get("created_date"), requestStartDate, requestEndDate));
		}

		cq.where(cb.and(predicates.toArray(new Predicate[0])));
		TypedQuery<Product> typedQuery = em.createQuery(cq);

		List<Product> result = typedQuery.getResultList();
		List<ProductData> dataResults = result.stream().map(modelMapper::fromProductToData).toList();

		log.info("RESULT: {}", dataResults);
		final int start = (int) pageRequest.getOffset();
		final int end = Math.min((start + pageRequest.getPageSize()), result.size());
		final Page<ProductData> page = new PageImpl<>(dataResults.subList(start, end), pageRequest, result.size());
		return page;
	}
}
