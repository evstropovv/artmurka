package com.github.scribejava.core.extractors;

import com.github.scribejava.core.model.OAuthRequest;

/**
 * Simple command object that extracts a base string from a {@link OAuthRequest}
 */
public interface BaseStringExtractor {

    /**
     * Extracts an url-encoded base string from the {@link OAuthRequest}.
     *
     * See <a href="http://oauth.net/core/1.0/#anchor14">the oauth spec</a> for more info on this.
     *
     * @param request the OAuthRequest
     * @return the url-encoded base string
     */
    String extract(OAuthRequest request);
}
