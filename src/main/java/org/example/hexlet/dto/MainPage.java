package org.example.hexlet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class MainPage extends Page{
    private Boolean visited;

    public boolean isVisited() {
        return visited;
    }
}
