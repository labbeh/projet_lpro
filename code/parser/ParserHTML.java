import org.jsoup.*;
import org.jsoup.nodes.*;
import java.io.*;
/**
* Classe pour parser du code HTML avec
* l'API Jsoup
* Le but étant de compléter la balise main à partir d'un code
* d'une autre page
* @author labbeh
*/
public class ParserHTML {
	/**
	* @param [0] fichier source au main vide
	* @param [1] fichier qui contient le main à insérer
	* @param [2] chemin du fichier destination
	*/
	public static void main(String[] args) throws IOException{
		if(args.length < 3){
			log("Erreur usage: java ParserHTML src codeAinserer dest");
			System.exit(0);
		}
		Document doc = Jsoup.parse(new File(args[0]), null);
		Document src = Jsoup.parse(new File(args[1]), null);

		doc.title(src.title());
		Element mainArea = doc.getElementsByTag("main").first();
		Element mainSrc  = src.getElementsByTag("main").first();

		mainArea.prepend(mainSrc.html());

		// sauvegarde dans le fichier
		save(doc.html(), args[2]);
		log("fin d'exécution");

	}

	/**
	* Affiche les messages dans la console
	* @param msg message à afficher
	*/
	private static void log(Object msg){
		System.out.println(msg.toString());
	}

	/**
	* Ecrit le code html produit dans un fichier de sortie
	* @param html code source html à écrire
	* @param path chemin vers le fichier
	*/
	private static void save(String html, String path){
		try{
			FileWriter  fw = new FileWriter(path);
			PrintWriter pw = new PrintWriter(fw) ;

			pw.println(html);
			pw.close();
			fw.close();
		}
		catch(Exception evt){evt.printStackTrace();}
	}
}
