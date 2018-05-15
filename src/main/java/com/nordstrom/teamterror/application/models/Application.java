package com.nordstrom.teamterror.application.models;

import java.time.ZonedDateTime;
import org.springframework.format.annotation.DateTimeFormat;

public class Application {

    public String applicationId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public ZonedDateTime startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public ZonedDateTime endDate;
    public String accountMemberId;
    public String listingId;

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public String getAccountMemberId() {
        return accountMemberId;
    }

    public void setAccountMemberId(String accountMemberId) {
        this.accountMemberId = accountMemberId;
    }

    public String getListingId() {
        return listingId;
    }

    public void setListingId(String listingId) {
        this.listingId = listingId;
    }
}
