package coskun.ahmet.model.playground;

import java.util.Map;

public interface IPlayground {

    Map<Integer, PlaygroundTile> getPlaygroundTileList();

    public void updatePlayground(int position, char newChar);
}
