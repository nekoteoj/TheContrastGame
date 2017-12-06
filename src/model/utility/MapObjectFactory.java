package model.utility;

import exception.MapObjectNotFoundException;
import model.Entity;
import model.NormalHero;
import model.Soldier;
import model.Tank;
import model.map.*;

/**
 * 
 * Map Id : 
 * 1 : Floor 
 * 2 : FloorBox 
 * 3 : Box 
 * 20 : Soldier 
 * 21 : Tank 
 * 22 : Boss 
 * 23 : NormalHero
 * 24 : BuffHero 
 * 100 : TestFloor
 *
 */

public class MapObjectFactory {

	public static Entity getMapObjectById(int id, int... param) throws MapObjectNotFoundException {
		if (id == 1 && param.length == 4) {
			return new Floor(param[0], param[1], param[2], param[3]);
		} else if (id == 2 && param.length == 3) {
			return new FloorBox(param[0], param[1], param[2]);
		} else if (id == 3 && param.length == 3) {
			return new Box(param[0], param[1], param[2]);
		} else if (id == 20 && param.length == 2) {
			return new Soldier(param[0], param[1]);
		} else if (id == 21 && param.length == 2) {
			return new Tank(param[0], param[1]);
		} else if (id == 22 && param.length == 2) {
			return null; // TODO
		} else if (id == 23 && param.length == 2) {
			return new NormalHero(param[0], param[1]);
		} else if (id == 24) {
			return null; // TODO
		} else if (id == 100 && param.length == 4) {

			return new TestFloor(param[0], param[1], param[2], param[3]);
		}
		throw new MapObjectNotFoundException();
	}

}
