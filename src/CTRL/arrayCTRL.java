package CTRL;

import java.util.Random;

public class arrayCTRL {
	static Random rd = new Random();
	static ingredientCTRL ic = new ingredientCTRL();
	private static String[] recipe;

	public static String[] Easy() {
		int num = rd.nextInt(3) + 3;
		recipe = new String[num];
		recipe[0] = "빵";
		recipe[recipe.length - 1] = "빵";
		for (int i = 1; i < recipe.length - 1; i++) {
			recipe[i] = ic.randomIngredient();
			if (recipe[i] == "빵") {
				--i;
			}
		}
		return recipe;
	}

	public static String[] Normal() {
		int num = rd.nextInt(3) + 5;
		recipe = new String[num];
		recipe[0] = "빵";
		recipe[recipe.length - 1] = "빵";
		for (int i = 1; i < recipe.length - 1; i++) {
			recipe[i] = ic.randomIngredient();
			if (recipe[i] == "빵") {
				--i;
			}
		}
		return recipe;
	}

	public static String[] Hard() {

		int num = rd.nextInt(3) + 7;

		recipe = new String[num];
		recipe[0] = "빵";
		recipe[recipe.length - 1] = "빵";
		for (int i = 1; i < recipe.length - 1; i++) {
			recipe[i] = ic.randomIngredient();
			if (recipe[i] == "빵") {
				--i;
			}
		}
		return recipe;
	}


}
