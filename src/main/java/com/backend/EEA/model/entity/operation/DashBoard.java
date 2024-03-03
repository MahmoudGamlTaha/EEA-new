package com.backend.EEA.model.entity.operation;

import net.jcip.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Immutable
@Subselect("select ROW_NUMBER() over(partition By status order by status) as id,count(*) acceptedRequests, status from request_header rh where rh .requester = 20053\n" +
        "  group by status")
public class DashBoard {
    @Id
    private Long id;

    @Column
    private Long acceptedRequests;

    @Column
    private Long rejectedRequests;

    @Column
    private Double totalPaid;
    @Column
    private Long underReviewRequests;

    private String type; //yearly . monthly
}
