package com.bluebannana.bidder.web.rest.view;

import java.util.Objects;

public class BidResponseView {

    private String id;
    private Bid bid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Bid getBid() {
        return bid;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BidResponseView that = (BidResponseView) o;
        return Objects.equals(id, that.id)
                && Objects.equals(bid, that.bid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bid);
    }

    @Override
    public String toString() {
        return "BidResponseView{"
                + "id='" + id + '\''
                + ", bid=" + bid
                + '}';
    }

    public static class Bid {

        private String campaignId;
        private Float price;
        private String adm;

        public String getCampaignId() {
            return campaignId;
        }

        public void setCampaignId(String campaignId) {
            this.campaignId = campaignId;
        }

        public Float getPrice() {
            return price;
        }

        public void setPrice(Float price) {
            this.price = price;
        }

        public String getAdm() {
            return adm;
        }

        public void setAdm(String adm) {
            this.adm = adm;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Bid bid = (Bid) o;
            return Objects.equals(campaignId, bid.campaignId)
                    && Objects.equals(price, bid.price)
                    && Objects.equals(adm, bid.adm);
        }

        @Override
        public int hashCode() {

            return Objects.hash(campaignId, price, adm);
        }

        @Override
        public String toString() {
            return "Bid{"
                    + "campaignId='" + campaignId + '\''
                    + ", price=" + price
                    + ", adm='" + adm + '\''
                    + '}';
        }
    }
}
