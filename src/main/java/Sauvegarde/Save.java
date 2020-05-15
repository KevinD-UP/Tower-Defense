package Sauvegarde;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.*;

public class Save {
	private static int length;
	private static JSONArray list = new JSONArray();

	public Save(int i) {
		length = i;
		//creation de notre tableau qui va stocker si les levels sont faits
		for(int j=0; j<length; j++) {
			if(j == 1) {
				list.put(1);
			}
			else {
				list.put(0);
			}
		}

		// CREATION DE NOTRE FICHIER JSON SI IL N EXISTE PAS 

		File test = new File("save.json");
		boolean b = false;
		try {
			b = test.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if(b) {
			try {
				createJSON();
			} catch (JSONException | IOException e) {
				e.printStackTrace();
			}
		}
	}


	//Methode pour creer un fichier json
	public static void createJSON() throws JSONException, IOException
	{

		FileWriter file = new FileWriter("save.json", false);
		//false pour peremettre la reecriture du fichier
		file.flush();

		JSONObject obj = new JSONObject();
		obj.put("LEVELS", list);

		String jsonText = obj.toString();
		file.write(jsonText);
		file.write("\n");
		file.close();
	}

	//Methode pour lire le fichier json pour retourner un JSONArray
	public static JSONArray lire(String fileName) throws IOException, JSONException{

		FileReader reader = new FileReader(fileName);
		JSONTokener jsonT = new JSONTokener(reader);
		JSONObject jo = new JSONObject(jsonT);
		JSONArray jsonArray = jo.optJSONArray("LEVELS");
		reader.close();
		return jsonArray;
	}

	

	//Methode pour decoder le JSONArray et recuperer un tableau de boolean
	public static boolean[] decoder(JSONArray jsonArray){
		boolean[] b = new boolean[jsonArray.length()];
		for (int i = 0; i < b.length; ++i) {
			if(jsonArray.optInt(i) == 0)
				b[i] = false;
			else if (jsonArray.optInt(i) == 1) {
				b[i] = true;
			}
			else {
				new Exception("erreur json");
			}
		}
		return b;
	}

	//methode permettant de retourner le contenu du fichier JSON en un tableau de boolean
	public static boolean[] sauvegarde() throws IOException, JSONException {

		JSONArray c = Save.lire("save.json") ;
		boolean[] b = Save.decoder(c);	
		return b;
	}

	//methode permettant de mettre 	a jour le fichier JSON
	public static void miseAjour(boolean[] tab) {
		//on converti tab en tableau de int
		int[] t = new int[tab.length];
		for(int i=0; i<t.length; i++) {
			if(tab[i]) {
				t[i] = 1;
			}
			else {
				t[i] = 0;
			}
		}
		//on met a jour le JSONArray de Save
		list = new JSONArray();
		for(int i=0; i<length; i++) {
			list.put(t[i]);
		}
		//on met a jour le fichier json
		try {
			createJSON();
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//methode qui va reinitialiser le fichier JSON
	public static void newGame() {
		//on met a jour le JSONArray de Save
		list = new JSONArray();
		for(int i=0; i<length; i++) {
			if(i == 1) {
				list.put(1);
			}
			else {
				list.put(0);
			}
		}
		//on met a jour le fichier json
		try {
			createJSON();
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}