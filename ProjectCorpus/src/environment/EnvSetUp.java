package environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import util.FileUtil;

public class EnvSetUp {
	
	public static final String user_home = System.getProperty("user.home");
	public static final String witness = user_home + "/YYXWitness";
	public static final String data = user_home + "/YYXData";
	public static final String test = user_home + "/YYXTest";
	
	public static void main(String[] args) {
		JsonParser parser = new JsonParser();
		JsonObject object = null;
		try {
			object = (JsonObject) parser.parse(new FileReader("config.json"));
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		PrepareDir(object, "witness", witness);
		PrepareDir(object, "data", data);
		PrepareDir(object, "test", test);
		
		String ast_meta = user_home + "/AST_Metas";
		File dir = new File(ast_meta);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		FileUtil.CopyFile(new File("config.json"), new File(ast_meta + "/" + "config.json"));
		System.out.println("Environment has been set up successfully.");
	}
	
	private static void PrepareDir(JsonObject object, String dir_hint, String dir) {
		File witness_dir = new File(dir);
		if (witness_dir.exists()) {
			FileUtil.DeleteFile(witness_dir);
		}
		witness_dir.mkdirs();
		
		JsonArray array = object.get(dir_hint).getAsJsonArray();
		ArrayList<File> wit_handles = new ArrayList<File>();
		int as = array.size();
		for (int a=0;a<as;a++) {
			JsonElement ele = array.get(a);
			String name = ele.getAsString();
			File f = new File("all_projects" + "/" + name);
			wit_handles.add(f);
		}
		for (File f : wit_handles) {
			try {
				FileUtil.CopyDirUnderSpecificDir(f.getAbsolutePath(), witness_dir.getAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
	
}
