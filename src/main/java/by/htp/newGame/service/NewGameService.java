package by.htp.newGame.service;

import java.util.Map;
import java.util.Set;

import by.htp.newGame.bean.NewGame;

public interface NewGameService {

	Character getFirstChar(String city);

	Character getLastChar(String city);

	Map<Character, Set<String>> createCityMap(Set<String> cities);

	boolean inputWordCorrect(Character firstLetterCityName, NewGame game);

	boolean checkUniqueness(String inputWord, NewGame game);

	Set<String> readCitiesFromFile() throws NewGameServiceException;

	String readCityNameUserInput();

	NewGame initializationGame() throws NewGameServiceException;

	String returnCityNameByLastLetterFromMap(NewGame game);

}
