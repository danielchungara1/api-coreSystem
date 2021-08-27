package com.tplate.coresystem.catalog.product;

import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductSpecification implements Specification<ProductModel> {

    private String text;

    @Override
    public Predicate toPredicate(Root<ProductModel> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        if (this.getText()!= null){
            List<Predicate> predicateList = new ArrayList<>();

            predicateList.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("code")), "%" + this.getText().toLowerCase() + "%"));
            predicateList.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + this.getText().toLowerCase() + "%"));

            Predicate[] array = new Predicate[predicateList.size()];
            predicates.add(criteriaBuilder.or(predicateList.toArray(array)));
        }

//        if (this.getBrandId() != null){
//            Join<User, Brand> join = root.join("brand", JoinType.INNER);
//            predicates.add(criteriaBuilder.equal(join.get("id"), this.getBrandId()));
//        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));

    }

}
