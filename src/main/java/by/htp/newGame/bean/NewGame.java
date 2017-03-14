package by.htp.newGame.bean;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NewGame {

	Character lastLetterLastWord = null;
	Set<String> gameSet = new HashSet<>();
	Map<Character, Set<String>> cityMap;

	public NewGame() {

	}

	public Character getLastLetterLastWord() {
		return lastLetterLastWord;
	}

	public void setLastLetterLastWord(Character lastLetterLastWord) {
		this.lastLetterLastWord = lastLetterLastWord;
	}

	public Set<String> getGameSet() {
		return gameSet;
	}

	public void setGameSet(Set<String> gameSet) {
		this.gameSet = gameSet;
	}

	public Map<Character, Set<String>> getCityMap() {
		return cityMap;
	}

	public void setCityMap(Map<Character, Set<String>> cityMap) {
		this.cityMap = cityMap;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cityMap == null) ? 0 : cityMap.hashCode());
		result = prime * result + ((gameSet == null) ? 0 : gameSet.hashCode());
		result = prime * result + ((lastLetterLastWord == null) ? 0 : lastLetterLastWord.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewGame other = (NewGame) obj;
		if (cityMap == null) {
			if (other.cityMap != null)
				return false;
		} else if (!cityMap.equals(other.cityMap))
			return false;
		if (gameSet == null) {
			if (other.gameSet != null)
				return false;
		} else if (!gameSet.equals(other.gameSet))
			return false;
		if (lastLetterLastWord == null) {
			if (other.lastLetterLastWord != null)
				return false;
		} else if (!lastLetterLastWord.equals(other.lastLetterLastWord))
			return false;
		return true;
	}

}
