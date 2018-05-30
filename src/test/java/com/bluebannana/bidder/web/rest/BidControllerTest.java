package com.bluebannana.bidder.web.rest;

import com.bluebannana.bidder.WebApp;
import com.bluebannana.bidder.domain.Campaign;
import com.bluebannana.bidder.service.BidService;
import com.bluebannana.bidder.service.impl.mock.MockRestServiceImpl;
import com.bluebannana.bidder.web.rest.view.BidRequestView;
import com.bluebannana.bidder.web.rest.view.BidResponseView;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.util.Strings;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApp.class)
public class BidControllerTest {


    @Autowired
    private BidService bidService;

    @Autowired
    private HttpMessageConverter[] httpMessageConverters;

    @Autowired
    private ResponseEntityExceptionHandler exceptionHandler;

    @Autowired
    MockRestServiceImpl mockRestService;

    private MockMvc restMvc;

    @Before
    public void setup() {

        BidController bidController = new BidController(bidService);

        this.restMvc = MockMvcBuilders.standaloneSetup(bidController)
                .setMessageConverters(httpMessageConverters)
                .setControllerAdvice(exceptionHandler)
                .build();
    }

    @Test
    public void makeBidRequestWithResponse() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        BidRequestView bidRequest;
        try (InputStream request = ClassLoader.getSystemResourceAsStream("notEmptyResponseTest/request.json")) {
            bidRequest = mapper.readValue(request, BidRequestView.class);
        }

        try (InputStream campaigns = ClassLoader.getSystemResourceAsStream("notEmptyResponseTest/campaignApi.json")) {
            mockRestService.setAvailableCampaigns(mapper.readValue(campaigns, Campaign[].class));
        }

        BidResponseView expectedResponse;
        try (InputStream response = ClassLoader.getSystemResourceAsStream("notEmptyResponseTest/response.json")) {
            expectedResponse = mapper.readValue(response, BidResponseView.class);
        }

        String response = restMvc.perform(post("/api/bid")
                .contentType(APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsBytes(bidRequest)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        BidResponseView bidResponse = mapper.readValue(response, BidResponseView.class);

        assertThat(bidResponse).isEqualTo(expectedResponse);
    }

    @Test
    public void makeBidRequestWithouResponse() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        BidRequestView bidRequest;
        try (InputStream request = ClassLoader.getSystemResourceAsStream("emptyResponseTest/request.json")) {
            bidRequest = mapper.readValue(request, BidRequestView.class);
        }

        try (InputStream campaigns = ClassLoader.getSystemResourceAsStream("emptyResponseTest/campaignApi.json")) {
            mockRestService.setAvailableCampaigns(mapper.readValue(campaigns, Campaign[].class));
        }

        String response = restMvc.perform(post("/api/bid")
                .contentType(APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsBytes(bidRequest)))
                .andExpect(status().isNoContent())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(response).isEqualTo(Strings.EMPTY);
    }
}
