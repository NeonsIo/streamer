package io.neons.streamer.application.flink;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Table;
import io.neons.streamer.domain.session.Session;

import java.io.Serializable;
import java.sql.Timestamp;

@Table(keyspace= "Neons", name = "sessions")
public class SessionPojo implements Serializable {

    private static final long serialVersionUID = 1038054554690916991L;

    @Column(name = "sessionStartedAt")
    private String sessionStartedAt;

    @Column(name = "sessionEndedAt")
    private String sessionEndedAt;

    @Column(name = "lastEventEndedAt")
    private String lastEventEndedAt;

    @Column(name = "firstEventStartedAt")
    private String firstEventStartedAt;

    @Column(name = "numberOfEvents")
    private Integer numberOfEvents;

    @Column(name = "requestVisitorId")
    private String requestVisitorId;

    @Column(name = "userTrackerId")
    private String userTrackerId;

    @Column(name = "userVisitorId")
    private String userVisitorId;

    @Column(name = "userLanguage")
    private String userLanguage;

    @Column(name = "deviceScreenResolution")
    private String deviceScreenResolution;

    @Column(name = "deviceScreenViewport")
    private String deviceScreenViewport;

    @Column(name = "deviceColorDepth")
    private String deviceColorDepth;

    @Column(name = "deviceBrowserName")
    private String deviceBrowserName;

    @Column(name = "deviceBrowserVersion")
    private String deviceBrowserVersion;

    @Column(name = "devicePlatformName")
    private String devicePlatformName;

    @Column(name = "devicePlatformVersion")
    private String devicePlatformVersion;

    @Column(name = "deviceType")
    private String deviceType;

    @Column(name = "deviceIsMobile")
    private String deviceIsMobile;

    @Column(name = "localizationCountryIsoCode")
    private String localizationCountryIsoCode;

    @Column(name = "localizationCountryName")
    private String localizationCountryName;

    @Column(name = "localizationSubdivisionIsoCode")
    private String localizationSubdivisionIsoCode;


    @Column(name = "localizationSubdivisionName")
    private String localizationSubdivisionName;

    @Column(name = "localizationCityName")
    private String localizationCityName;

    @Column(name = "localizationPostalCode")
    private String localizationPostalCode;

    public SessionPojo() {}

    public SessionPojo(Session session) {
        this.sessionStartedAt = session.sessionStartedAt().toString();
        this.sessionEndedAt = session.sessionEndedAt().toString();
        this.lastEventEndedAt = session.lastEventEndedAt().toString();
        this.firstEventStartedAt = session.firstEventStartedAt().toString();
        this.numberOfEvents = session.numberOfEvents();
        this.requestVisitorId = session.requestVisitorId();
        this.userTrackerId = session.userTrackerId();
        this.userVisitorId = session.userVisitorId();
        this.userLanguage = session.userLanguage();
        this.deviceScreenResolution = session.deviceScreenResolution();
        this.deviceScreenViewport = session.deviceScreenViewport();
        this.deviceColorDepth = session.deviceColorDepth();
        this.deviceBrowserName = session.deviceBrowserName();
        this.deviceBrowserVersion = session.deviceBrowserVersion();
        this.devicePlatformName = session.devicePlatformName();
        this.devicePlatformVersion = session.devicePlatformVersion();
        this.deviceType = session.deviceType();
        this.deviceIsMobile = session.deviceIsMobile();
        this.localizationCountryIsoCode = session.localizationCountryIsoCode();
        this.localizationCountryName = session.localizationCountryName();
        this.localizationSubdivisionIsoCode = session.localizationSubdivisionIsoCode();
        this.localizationSubdivisionName = session.localizationSubdivisionName();
        this.localizationCityName = session.localizationCityName();
        this.localizationPostalCode = session.localizationPostalCode();
    }

    public String getSessionStartedAt() {
        return sessionStartedAt;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSessionEndedAt() {
        return sessionEndedAt;
    }

    public String getLastEventEndedAt() {
        return lastEventEndedAt;
    }

    public String getFirstEventStartedAt() {
        return firstEventStartedAt;
    }

    public Integer getNumberOfEvents() {
        return numberOfEvents;
    }

    public String getRequestVisitorId() {
        return requestVisitorId;
    }

    public String getUserTrackerId() {
        return userTrackerId;
    }

    public String getUserVisitorId() {
        return userVisitorId;
    }

    public String getUserLanguage() {
        return userLanguage;
    }

    public String getDeviceScreenResolution() {
        return deviceScreenResolution;
    }

    public String getDeviceScreenViewport() {
        return deviceScreenViewport;
    }

    public String getDeviceColorDepth() {
        return deviceColorDepth;
    }

    public String getDeviceBrowserName() {
        return deviceBrowserName;
    }

    public String getDeviceBrowserVersion() {
        return deviceBrowserVersion;
    }

    public String getDevicePlatformName() {
        return devicePlatformName;
    }

    public String getDevicePlatformVersion() {
        return devicePlatformVersion;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public String getDeviceIsMobile() {
        return deviceIsMobile;
    }

    public String getLocalizationCountryIsoCode() {
        return localizationCountryIsoCode;
    }

    public String getLocalizationCountryName() {
        return localizationCountryName;
    }

    public String getLocalizationSubdivisionIsoCode() {
        return localizationSubdivisionIsoCode;
    }

    public String getLocalizationSubdivisionName() {
        return localizationSubdivisionName;
    }

    public String getLocalizationCityName() {
        return localizationCityName;
    }

    public String getLocalizationPostalCode() {
        return localizationPostalCode;
    }

    public void setSessionStartedAt(String sessionStartedAt) {
        this.sessionStartedAt = sessionStartedAt;
    }

    public void setSessionEndedAt(String sessionEndedAt) {
        this.sessionEndedAt = sessionEndedAt;
    }

    public void setLastEventEndedAt(String lastEventEndedAt) {
        this.lastEventEndedAt = lastEventEndedAt;
    }

    public void setFirstEventStartedAt(String firstEventStartedAt) {
        this.firstEventStartedAt = firstEventStartedAt;
    }

    public void setNumberOfEvents(Integer numberOfEvents) {
        this.numberOfEvents = numberOfEvents;
    }

    public void setRequestVisitorId(String requestVisitorId) {
        this.requestVisitorId = requestVisitorId;
    }

    public void setUserTrackerId(String userTrackerId) {
        this.userTrackerId = userTrackerId;
    }

    public void setUserVisitorId(String userVisitorId) {
        this.userVisitorId = userVisitorId;
    }

    public void setUserLanguage(String userLanguage) {
        this.userLanguage = userLanguage;
    }

    public void setDeviceScreenResolution(String deviceScreenResolution) {
        this.deviceScreenResolution = deviceScreenResolution;
    }

    public void setDeviceScreenViewport(String deviceScreenViewport) {
        this.deviceScreenViewport = deviceScreenViewport;
    }

    public void setDeviceColorDepth(String deviceColorDepth) {
        this.deviceColorDepth = deviceColorDepth;
    }

    public void setDeviceBrowserName(String deviceBrowserName) {
        this.deviceBrowserName = deviceBrowserName;
    }

    public void setDeviceBrowserVersion(String deviceBrowserVersion) {
        this.deviceBrowserVersion = deviceBrowserVersion;
    }

    public void setDevicePlatformName(String devicePlatformName) {
        this.devicePlatformName = devicePlatformName;
    }

    public void setDevicePlatformVersion(String devicePlatformVersion) {
        this.devicePlatformVersion = devicePlatformVersion;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public void setDeviceIsMobile(String deviceIsMobile) {
        this.deviceIsMobile = deviceIsMobile;
    }

    public void setLocalizationCountryIsoCode(String localizationCountryIsoCode) {
        this.localizationCountryIsoCode = localizationCountryIsoCode;
    }

    public void setLocalizationCountryName(String localizationCountryName) {
        this.localizationCountryName = localizationCountryName;
    }

    public void setLocalizationSubdivisionIsoCode(String localizationSubdivisionIsoCode) {
        this.localizationSubdivisionIsoCode = localizationSubdivisionIsoCode;
    }

    public void setLocalizationSubdivisionName(String localizationSubdivisionName) {
        this.localizationSubdivisionName = localizationSubdivisionName;
    }

    public void setLocalizationCityName(String localizationCityName) {
        this.localizationCityName = localizationCityName;
    }

    public void setLocalizationPostalCode(String localizationPostalCode) {
        this.localizationPostalCode = localizationPostalCode;
    }
}
