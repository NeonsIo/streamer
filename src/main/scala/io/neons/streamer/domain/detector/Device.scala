package io.neons.streamer.domain.detector

case class Device(browserName: String,
                  browserVersion: String,
                  platformName: String,
                  platformVersion: String,
                  deviceType: String,
                  isMobile: String)