package by.htp.newGame.dao;

import java.util.Set;

public interface NewGameDao {

	Set<String> readCitiesFromFile() throws NewGameDaoException;

	String readCityNameUserInput();
}
