package CTRL;

import java.util.ArrayList;

import Model.CustomerVO;
import Model.DialogueVO;

public class customerCTRL {
	
	ArrayList<DialogueVO> di = new ArrayList<DialogueVO>();
	ArrayList<CustomerVO> cus = new ArrayList<CustomerVO>();

	public customerCTRL() {
		cus.add(new CustomerVO("[정세연쌤]"));
		cus.add(new CustomerVO("[이수환쌤]"));
		cus.add(new CustomerVO("[임승환쌤]"));
		cus.add(new CustomerVO("[김미희쌤]"));
		cus.add(new CustomerVO("[김용현쌤]"));
		cus.add(new CustomerVO("[채수민쌤]"));
		cus.add(new CustomerVO("[김동원쌤]"));
		cus.add(new CustomerVO("[이수현쌤]"));
		cus.add(new CustomerVO("[조자연쌤]"));
		cus.add(new CustomerVO("[임경남쌤]"));

		di.add(new DialogueVO("프로젝트 열심히 하는 친구들 사줘야지!"));
		di.add(new DialogueVO("나는 오늘도 예민하다."));
		di.add(new DialogueVO("다이어트 하는 날.."));
		di.add(new DialogueVO("치팅데이 즐기는 날"));
		di.add(new DialogueVO("우기 닮은 친구랑 먹을거야"));
		di.add(new DialogueVO("기프티콘 써야지~"));
		di.add(new DialogueVO("내기에서 졌다 ㅠㅠ..."));
		di.add(new DialogueVO("감튀와 콜라는 빼주세요."));
		di.add(new DialogueVO("햄버거는 역시 세트지"));
		di.add(new DialogueVO("지각하지 마세요, 우리반."));
		di.add(new DialogueVO("결석금지"));
		di.add(new DialogueVO("출퇴근시 지문확인하세요!"));
		di.add(new DialogueVO("스인재 청순가련 정세연"));
		di.add(new DialogueVO("스인재 기염둥이 포로리 채수민><"));
		di.add(new DialogueVO("월급을 받고싶니? 그렇다면 내게 말하렴..."));
		di.add(new DialogueVO("제가 TMI를 좋아합니다. 제 TMI는...."));
		di.add(new DialogueVO("저는 과제 안내드립니다.... 다음주 월요일까지 과제 해오세요."));
		di.add(new DialogueVO("여러분! 오늘이 저를 보는 마지막 시간이에요."));
		di.add(new DialogueVO("햄버거는 다이어트 식품입니다."));
		di.add(new DialogueVO("저기요, 빨리 만들어주실래요? 시간이 없거든요."));

	}

	public String customer() {
		double random = Math.random();
		int i = (int) Math.round(random * (cus.size() - 1));
		
		return cus.get(i).getCustomer();

	}

	public String dialogue() {
		double random = Math.random();
		int i = (int) Math.round(random * (di.size() - 1));
		
		return di.get(i).getDialogue();

	}
}

//		String [] person = {"[정세연]", "[이수환]", "[임승환]", "[김미희]", "[김용현]", "[채수민]", "[김동원]", "[이수현]", "[조자연]", "[임경남]"};  // 총 10개
//		double random = Math.random();
//		int i = (int)Math.round(random*(person.length -1));
//		
//		
//		System.out.println(person[i]);

//	public void dialogue() {
//		String [] dia = {"프로젝트 열심히 하는 친구들 사줘야지!", "나는 오늘도 예민하다.", "다이어트 하는 날..",
//				"치팅데이 즐기는 날", "우기 닮은 친구랑 먹을거야", "기프티콘 써야지~", "내기에서 졌다 ㅠㅠ...",
//				"감튀와 콜라는 빼주세요.", "햄버거는 역시 세트지", "지각하지 마세요, 우리반.", "결석금지", "출퇴근시 지문확인하세요!",
//				"스인재 청순가련 정세연", "스인재 기염둥이 포로리 채수민><", "월급을 받고싶니? 그렇다면 내게 말하렴...", "제가 TMI를 좋아합니다. 제 TMI는....",
//				"저는 과제 안내드립니다.... 다음주 월요일까지 과제 해오세요.", "여러분! 오늘이 저를 보는 마지막 시간이에요.", "햄버거는 다이어트 식품입니다.",
//				"저기요, 빨리 만들어주실래요? 시간이 없거든요."};  // 총 20개
//			double random1 = Math.random();
//			int j = (int)Math.round(random1 * (dia.length -1));
//			
//			System.out.println(dia[j]);
//	}
//}
