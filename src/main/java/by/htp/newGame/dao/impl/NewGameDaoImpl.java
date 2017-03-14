package by.htp.newGame.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import by.htp.newGame.dao.NewGameDao;
import by.htp.newGame.dao.NewGameDaoException;

public class NewGameDaoImpl implements NewGameDao {

	private static final String READ_CITIES_FROM_FILE = "cities.txt";

	@Override
	public Set<String> readCitiesFromFile() throws NewGameDaoException {
		Set<String> result = new HashSet<>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(
				new File(getClass().getClassLoader().getResource(READ_CITIES_FROM_FILE).getFile()))))) {
			String city;
			while ((city = br.readLine()) != null) {
				result.add(city.toUpperCase());
			}
		} catch (IOException e) {
			throw new NewGameDaoException("Reading: there is problem with data file (dao layer)", e);
		}
		return result;
	}

	@Override
	public String readCityNameUserInput() {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		return str.toUpperCase();
	}

}
