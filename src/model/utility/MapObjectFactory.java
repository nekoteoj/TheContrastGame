package model.utility;

import exception.MapObjectNotFoundException;
import model.map.*;

public class MapObjectFactory {
	
	public static MapObject getMapObjectById(int id, int... param) throws MapObjectNotFoundException {
		if (id == 1 && param.length == 4) {
			return new Floor(param[0], param[1], param[2], param[3]);
		} else if (id == 100 && param.length == 4) {
			return new TestFloor(param[0], param[1], param[2], param[3]);
		}
		throw new MapObjectNotFoundException();
	}
	
}
