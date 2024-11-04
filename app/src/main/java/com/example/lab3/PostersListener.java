package com.example.lab3;

/**
 * Interface for listening to actions related to poster selection in the application
 * Classes implementing this interface can respond to changes in poster selection states
 */
public interface PostersListener {
    /**
     * Called when the selection state of a poster changes
     * @param isSelected Boolean value indication if at least one poster is selected
     *                   If true, it signifies that a poster is selected ortherwise, not
     */
    void onPosterAction(Boolean isSelected);
}
