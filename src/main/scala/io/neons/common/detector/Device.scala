package io.neons.common.detector

case class Device(browserName: String,
                  browserVersion: String,
                  platformName: String,
                  platformVersion: String,
                  deviceType: String,
                  isMobile: String)