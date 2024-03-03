package com.backend.EEA.controllers.masterdata;


import com.backend.EEA.controllers.BaseRestController;
import com.backend.EEA.model.dto.masterdata.ComplainsDto;
import com.backend.EEA.model.dto.masterdata.RatingDto;
import com.backend.EEA.model.dto.search.ComplainsSearchForm;
import com.backend.EEA.model.dto.search.RatingSearchForm;
import com.backend.EEA.model.entity.masterdata.Complains;
import com.backend.EEA.model.entity.masterdata.Rating;
import com.backend.EEA.services.ComplainService;
import com.backend.EEA.services.RatingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portal-data/rating")
public class RatingController extends BaseRestController<Rating, RatingDto, RatingSearchForm> {

    private RatingService ratingService;

    public RatingController(RatingService ratingService) {
        super(ratingService);
        this.ratingService = ratingService;
    }
}
