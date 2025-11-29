package org.cowary.arttrackerback.dbCase;

import java.util.List;

public interface MediaCrud<T> {

    public List<T> getAll(long userId, String status);
}
