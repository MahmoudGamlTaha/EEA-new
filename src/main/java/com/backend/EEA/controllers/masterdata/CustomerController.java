package com.backend.EEA.controllers.masterdata;

import com.backend.EEA.controllers.BaseRestController;
import com.backend.EEA.model.dto.masterdata.DashBoardDto;
import com.backend.EEA.model.dto.masterdata.UserDto;
import com.backend.EEA.model.dto.search.UserSearchForm;
import com.backend.EEA.model.entity.masterdata.User;
import com.backend.EEA.model.pojos.ResponsePojo;
import com.backend.EEA.services.CustomerService;
import com.backend.EEA.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/basic-data/customer")
@CrossOrigin(origins = "*")
public class CustomerController extends BaseRestController<User, UserDto, UserSearchForm> {

    @Autowired
    CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        super(customerService);
    }
    @RequestMapping(value = "/dashboard",method = RequestMethod.GET)
    public ResponseEntity<ResponsePojo> dashBoard(){
        List<DashBoardDto> dashBoardDtos = new LinkedList<>();
        DashBoardDto dashBoardDto = new DashBoardDto();
        dashBoardDto.setType("ALL");
        dashBoardDto.setRejectedRequests(100L);
        dashBoardDto.setAcceptedRequests(50L);
        dashBoardDto.setTotalPaid(1250.5);
        dashBoardDto.setUnderReviewRequests(25L);
        dashBoardDtos.add(dashBoardDto);

        dashBoardDto = new DashBoardDto();
        dashBoardDto.setType("Monthly");
        dashBoardDto.setRejectedRequests(200L);
        dashBoardDto.setAcceptedRequests(100L);
        dashBoardDto.setTotalPaid(10250.5);
        dashBoardDto.setUnderReviewRequests(205L);
        dashBoardDtos.add(dashBoardDto);

        dashBoardDto = new DashBoardDto();
        dashBoardDto.setType("Weekly");
        dashBoardDto.setRejectedRequests(10L);
        dashBoardDto.setAcceptedRequests(5L);
        dashBoardDto.setTotalPaid(1000.5);
        dashBoardDto.setUnderReviewRequests(20L);
        dashBoardDtos.add(dashBoardDto);
        return buildResponseEntity(true, "success",dashBoardDtos, HttpStatus.OK);
    }
}
