package CTRL;

import java.util.ArrayList;

import Model.IngredientVO;

public class ingredientCTRL {
	ArrayList<IngredientVO> ic = new ArrayList<IngredientVO>();

	public ingredientCTRL() {

		ic.add(new IngredientVO("고기패티"));
		ic.add(new IngredientVO("새우패티"));
		ic.add(new IngredientVO("치킨패티"));
		ic.add(new IngredientVO("베이컨"));
		ic.add(new IngredientVO("토마토"));
		ic.add(new IngredientVO("피클"));
		ic.add(new IngredientVO("양상추"));
		ic.add(new IngredientVO("빵"));
		ic.add(new IngredientVO("머스타드"));
		ic.add(new IngredientVO("케찹"));
		ic.add(new IngredientVO("치즈"));

		// Controller
		

	}

	public String randomIngredient() {
		double random = Math.random();
		int i = (int) Math.round(random * (ic.size() - 1));

		return ic.get(i).getAny();

	}

}
