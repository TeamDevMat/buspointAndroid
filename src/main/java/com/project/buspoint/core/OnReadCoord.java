package com.project.buspoint.core;

import com.project.buspoint.core.Coord;
import java.util.List;


public interface OnReadCoord{

        public List<Coord> all(String query);
        public List<Coord> all();
}
