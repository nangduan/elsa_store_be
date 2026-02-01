package com.example.elsa_store.repository;

import com.example.elsa_store.entity.OrderItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("""
        select
            i.productVariant.id,
            i.productVariant.product.name,
            i.productVariant.imageUrl,
            coalesce(sum(i.quantity), 0),
            coalesce(sum(i.lineTotal), 0)
        from OrderItem i
        join i.order o
        where o.orderDate between :from and :to
          and o.status in :statuses
        group by i.productVariant.id, i.productVariant.product.name
        order by coalesce(sum(i.lineTotal), 0) desc
    """)
    List<Object[]> topProductRevenue(@Param("from") LocalDateTime from,
                                     @Param("to") LocalDateTime to,
                                     @Param("statuses") List<Integer> statuses,
                                     Pageable pageable);
}
