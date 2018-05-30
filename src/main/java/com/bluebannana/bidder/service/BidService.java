package com.bluebannana.bidder.service;

import com.bluebannana.bidder.domain.Campaign;
import com.bluebannana.bidder.web.rest.view.BidResponseView;

import java.util.Optional;

public interface BidService {

    /**
     * Get the highest matching campaign (if any)
     *
     * @param country the targeted country
     */
    Optional<Campaign> getMatchingCampaign(String country);

    /**
     * Build a bid response object from a bid request and a matching campaign
     *
     * @param campaign the matching campaign
     * @param requestId the bid request's id
     * @return the built bid response
     */
    BidResponseView buildResponse(Campaign campaign, String requestId);
}
