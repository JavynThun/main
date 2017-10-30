package seedu.address.ui;

import java.net.URL;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.web.WebView;
import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.PersonPanelSelectionChangedEvent;
import seedu.address.model.person.ReadOnlyPerson;

/**
 * The Profile Panel of the App.
 */
public class ProfilePanel extends UiPart<Region> {
    public static final String DEFAULT_PAGE = "default_profile.html";
    public static final String HTML_SUFFIX = ".html";
    public static final String PROFILE_DIRECTORY_PREFIX = FXML_FILE_FOLDER + "profiles/";

    private static final String FXML = "ProfilePanel.fxml";

    private final Logger logger = LogsCenter.getLogger(this.getClass());

    @javafx.fxml.FXML
    private WebView profile;

    public ProfilePanel() {
        super(FXML);

        // To prevent triggering events for typing inside the loaded Web page.
        getRoot().setOnKeyPressed(Event::consume);

        loadDefaultPage();
        registerAsAnEventHandler(this);
    }

    private void loadPersonProfile(ReadOnlyPerson person) {
        URL defaultPage = MainApp.class.getResource(PROFILE_DIRECTORY_PREFIX + person.getPhone().value + HTML_SUFFIX);
        loadPage(defaultPage.toExternalForm());
    }

    public void loadPage(String url) {
        Platform.runLater(() -> profile.getEngine().load(url));
    }

    /**
     * Loads a default HTML file with a background that matches the general theme.
     */
    private void loadDefaultPage() {
        URL defaultPage = MainApp.class.getResource(PROFILE_DIRECTORY_PREFIX + DEFAULT_PAGE);
        loadPage(defaultPage.toExternalForm());
    }

    /**
     * Frees resources allocated to the browser.
     */
    public void freeResources() {
        profile = null;
    }

    @Subscribe
    private void handlePersonPanelSelectionChangedEvent(PersonPanelSelectionChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        loadPersonProfile(event.getNewSelection().person);
    }
}
