package com.bluebannana.bidder.domain;

import java.util.HashSet;
import java.util.Set;

public class Campaign {

    private String id;
    private String name;
    private Float price;
    private String adm;
    private Set<String> targetedCountries;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<String> getTargetedCountries() {
        if (targetedCountries == null) {
            targetedCountries = new HashSet<>();
        }
        return targetedCountries;
    }

    public void setTargetedCountries(Set<String> targetedCountries) {
        this.targetedCountries = targetedCountries;
    }
}
