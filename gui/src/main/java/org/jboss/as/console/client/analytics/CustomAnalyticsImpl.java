package org.jboss.as.console.client.analytics;

/**
 * Copyright 2011 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.user.client.Window;
import com.gwtplatform.mvp.client.googleanalytics.GoogleAnalytics;


/**
 * Default {@link com.gwtplatform.mvp.client.googleanalytics.GoogleAnalytics} implementation that uses JSNI to
 * expose Google Analytics javascript methods.
 *
 * @author Christian Goudreau
 */
public class CustomAnalyticsImpl implements GoogleAnalytics {
  @Override
  public void init(String userAccount) {
    Element firstScript = Document.get().getElementsByTagName("script").getItem(
        0);

    String domain = "jboss.org"; //GWT.getHostPageBaseURL();

    ScriptElement config = Document.get().createScriptElement(
        "var _gaq = _gaq || [];_gaq.push(['_setAccount', '" + userAccount
            + "']);_gaq.push(['_setDomainName', '"+domain+"']);_gaq.push(['_setAllowLinker', true]);_gaq.push(['_trackPageview']);");

    firstScript.getParentNode().insertBefore(config, firstScript);

    ScriptElement script = Document.get().createScriptElement();

    // Add the google analytics script.
    script.setSrc(("https:".equals(Window.Location.getProtocol())
        ? "https://ssl" : "http://www") + ".google-analytics.com/ga.js");
    script.setType("text/javascript");
    script.setAttribute("async", "true");

    firstScript.getParentNode().insertBefore(script, firstScript);
  }

  @Override
  public native void addAccount(String trackerName, String userAccount) /*-{
    $wnd._gaq.push([ '" + trackerName + "._setAccount', '" + userAccount + "' ]);
  }-*/;

  public native void trackPageview(String pageName) /*-{
    if (!pageName.match("^/") == "/") {
      pageName = "/" + pageName;
    }

    $wnd._gaq.push([ '_trackPageview', pageName ]);
  }-*/;

  @Override
  public native void trackPageview() /*-{
    $wnd._gaq.push([ '_trackPageview' ]);
  }-*/;

  @Override
  public native void trackPageview(String trackerName, String pageName) /*-{
    if (!pageName.match("^/") == "/") {
      pageName = "/" + pageName;
    }

    $wnd._gaq.push([ '" + trackerName + "._trackPageview', pageName ]);
  }-*/;

  @Override
  public native void trackEvent(String category, String action) /*-{
    $wnd._gaq.push([ '_trackEvent', category, action ]);
  }-*/;

  @Override
  public native void trackEvent(String category, String action, String optLabel) /*-{
    $wnd._gaq.push([ '_trackEvent', category, action, optLabel ]);
  }-*/;

  @Override
  public native void trackEvent(String category, String action,
                                String optLabel, int optValue) /*-{
    $wnd._gaq.push([ '_trackEvent', category, action, optLabel, optValue ]);
  }-*/;

  @Override
  public native void trackEvent(String category, String action, String optLabel, int optValue, boolean optNonInteraction) /*-{
    $wnd._gaq.push([ '_trackEvent', category, action, optLabel, optValue, optNonInteraction ]);
  }-*/;

  @Override
  public native void trackEventWithTracker(String trackerName, String category, String action) /*-{
    $wnd._gaq.push([ '" + trackerName + "._trackEvent', category, action ]);
  }-*/;

  @Override
  public native void trackEventWithTracker(String trackerName, String category, String action, String optLabel) /*-{
    $wnd._gaq.push([ '" + trackerName + "._trackEvent', category, action, optLabel ]);
  }-*/;

  @Override
  public native void trackEventWithTracker(String trackerName, String category, String action,
                                           String optLabel, int optValue) /*-{
    $wnd._gaq.push([ '" + trackerName + "._trackEvent', category, action, optLabel, optValue ]);
  }-*/;

  @Override
  public native void trackEventWithTracker(String trackerName, String category, String action, String optLabel, int optValue, boolean optNonInteraction) /*-{
    $wnd._gaq.push([ '" + trackerName + "._trackEvent', category, action, optLabel, optValue, optNonInteraction ]);
  }-*/;
}
