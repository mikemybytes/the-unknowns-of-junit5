package com.mikemybytes.junit5.duration;

import java.time.Duration;

class DurationEncoder {

    String secondsToIso8601(long seconds) {
        // imagine that's a DYI implementation worth testing ;)
        return Duration.ofSeconds(seconds).toString();
    }

}
