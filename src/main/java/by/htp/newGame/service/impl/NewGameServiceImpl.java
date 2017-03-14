package by.htp.newGame.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import by.htp.newGame.bean.NewGame;
import by.htp.newGame.dao.NewGameDao;
import by.htp.newGame.dao.NewGameDaoException;
import by.htp.newGame.dao.impl.NewGameDaoImpl;
import by.htp.newGame.service.NewGameService;
import by.htp.newGame.service.NewGameServiceException;

public class NewGameServiceImpl implements NewGameService {

	NewGameDao dao = new NewGameDaoImpl();

	@Override
	public Character getFirstChar(String city) {
		return city.charAt(0);
	}

	@Override
	public Character getLastChar(String city) {
		int pos = city.length() - 1;
		char lastChar = city.toUpperCase().charAt(pos);
		if (city.toUpperCase().charAt(pos) == 'Й') {
			return 'И';
		} else if (lastChar == 'Ь' || lastChar == 'Ы' || lastChar == 'Ъ') {
			pos--;
		}
		return city.toUpperCase().charAt(pos);
	}

	@Override
	public Map<Character, Set<String>> createCityMap(Set<String> cities) {
		Map<Character, Set<String>> cityMap = new HashMap<Character, Set<String>>();
		for (String city : cities) {
			Set<String> cs = cityMap.get(getFirstChar(city));
			if (cs == null) {
				cityMap.put(getFirstChar(city), cs = new HashSet<String>());
			}
			cs.add(city);
		}
		return cityMap;
	}

	@Override
	public String returnCityNameByLastLetterFromMap(NewGame game) {
		Map<Character, Set<String>> cityMap = game.getCityMap();
		String cityName = null;
		Set<String> citySet = cityMap.get(game.getLastLetterLastWord());
		if (citySet != null) {
			for (String city : citySet) {
				if (checkUniqueness(city, game)) {
					cityName = city;
					break;
				}
			}
		}
		return cityName;
	}

	@Override
	public boolean inputWordCorrect(Character firstLetterCityName, NewGame game) {
		return game.getGameSet().isEmpty()
				|| (!game.getGameSet().isEmpty() && firstLetterCityName.equals(game.getLastLetterLastWord()));
	}

	@Override
	public boolean checkUniqueness(String inputWord, NewGame game) {
		return !game.getGameSet().contains(inputWord);
	}

	@Override
	public Set<String> readCitiesFromFile() throws NewGameServiceException {
		try {
			return dao.readCitiesFromFile();
		} catch (NewGameDaoException e) {
			throw new NewGameServiceException("Error with data (service layer): " + e.getMessage(), e);
		}
	}

	@Override
	public String readCityNameUserInput() {
		return dao.readCityNameUserInput();
	}

	@Override
	public NewGame initializationGame() throws NewGameServiceException {
		NewGame game = new NewGame();
		Set<String> citiesSet = readCitiesFromFile();
		game.setCityMap(createCityMap(citiesSet));
		System.out.println();
		return game;
	}

}
