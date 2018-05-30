package com.bluebannana.bidder.service.impl.mock;

import com.bluebannana.bidder.domain.Campaign;
import com.bluebannana.bidder.service.RestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Service
public class MockRestServiceImpl implements RestService {

    private final Logger log = LoggerFactory.getLogger(MockRestServiceImpl.class);

    private Campaign[] availableCampaigns;

    public Campaign[] getAvailableCampaigns() {
        if (availableCampaigns == null) {
            availableCampaigns = new Campaign[0];
        }
        return availableCampaigns;
    }

    public void setAvailableCampaigns(Campaign[] availableCampaigns) {
        this.availableCampaigns = availableCampaigns;
    }

    /**
     * Mock a GET request to the campaigns API
     * and return the available campaigns
     *
     * @param url the url to make the request
     * @param object the class to cast the response
     * @param variables path variables
     * @param <T> the class to cast
     * @return
     */
    @Override
    public <T> T getRequest(String url, Class<T> object, Object... variables) {
        if (object.equals(Campaign[].class)) {
            log.debug("Mocking REST call to campaign API");
            return (T) getAvailableCampaigns();
        } else {
            return null;
        }
    }

    @PostConstruct
    private void loadData() {
        try (InputStream stream = ClassLoader.getSystemResourceAsStream("classpath:campaigns.json")) {
            ObjectMapper mapper = new ObjectMapper();
            setAvailableCampaigns(mapper.readValue(stream, Campaign[].class));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
