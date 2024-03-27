package com.openpay.libreriamarvel.app;

import com.openpay.libreriamarvel.VO.CharactersByIdInVO;

public interface LibreriaMarvelApp {
	public String getCharacters(String publicKey, String privateKey);
	public String getCharactersById(CharactersByIdInVO charactersByIdInVO);

}
