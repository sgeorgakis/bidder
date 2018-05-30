package com.bluebannana.bidder.service.impl;

import com.bluebannana.bidder.config.ApplicationProperties;
import com.bluebannana.bidder.domain.Campaign;
import com.bluebannana.bidder.service.BidService;
import com.bluebannana.bidder.service.RestService;
import com.bluebannana.bidder.web.rest.view.BidResponseView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class BidServiceImpl implements BidService {

    private final Logger log = LoggerFactory.getLogger(BidServiceImpl.class);

    private final ApplicationProperties applicationProperties;

    private final RestService restService;

    public BidServiceImpl(RestService restService, ApplicationProperties applicationProperties) {
        this.restService = restService;
        this.applicationProperties = applicationProperties;
    }

    /**
     * Get the highest matching campaign (if any)
     *
     * @param country the targeted country
     */
    @Override
    public Optional<Campaign> getMatchingCampaign(String country) {

        return getCampaigns().stream()
            .filter(c -> c.getTargetedCountries().contains(country))
            .max(Comparator.comparing(Campaign::getPrice));

    }

    /**
     * Build a bid response object from a bid request and a matching campaign
     *
     * @param campaign the matching campaign
     * @param requestId the bid request's id
     * @return the built bid response
     */
    public BidResponseView buildResponse(Campaign campaign, String requestId) {
        BidResponseView.Bid bid = new BidResponseView.Bid();
        bid.setCampaignId(campaign.getId());
        bid.setPrice(campaign.getPrice());
        bid.setAdm(campaign.getAdm());

        BidResponseView bidResponse = new BidResponseView();
        bidResponse.setId(requestId);
        bidResponse.setBid(bid);

        return bidResponse;
    }


    /**
     * Make a request to the campaign API to get
     * all the available campaigns
     *
     * @return a set containing the campaigns
     */
    private Set<Campaign> getCampaigns() {
        Campaign[] campaigns = restService.getRequest(applicationProperties.getCampaign().getUrl(), Campaign[].class);
        return new HashSet<>(Arrays.asList(campaigns));
    }
}
