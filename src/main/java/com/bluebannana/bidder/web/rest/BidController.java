package com.bluebannana.bidder.web.rest;

import com.bluebannana.bidder.domain.Campaign;
import com.bluebannana.bidder.service.BidService;
import com.bluebannana.bidder.web.rest.view.BidRequestView;
import com.bluebannana.bidder.web.rest.view.BidResponseView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BidController {

    private final BidService bidService;

    private final Logger log = LoggerFactory.getLogger(BidController.class);

    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    /**
     * Submit a request for bid
     *
     * @param bidRequest the request containing the campaign criteria
     * @return the highest bid found matching the criteria with HTTP code 200
     *         or an empty response with HTTP code 204 if no matching campaign found
     */
    @PostMapping(path = "/bid", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<BidResponseView> getBid(@RequestBody BidRequestView bidRequest) {
        log.debug("Request for bid: {}", bidRequest.getId());
        Optional<Campaign> campaign = bidService.getMatchingCampaign(bidRequest.getDevice().getGeo().getCountry());
        if (campaign.isPresent()) {
            return ResponseEntity.ok(bidService.buildResponse(campaign.get(), bidRequest.getId()));
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
