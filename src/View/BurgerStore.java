package View;

import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;

import CTRL.DAO;
import CTRL.arrayCTRL;
import CTRL.customerCTRL;
import CTRL.customerCharCTRL;
import CTRL.musicCTRL;

public class BurgerStore {
	static DAO dao = new DAO();
	static Scanner sc = new Scanner(System.in);
	static Random rd = new Random();
	static int i = 1;
	static arrayCTRL ac = new arrayCTRL();

	static String cusName = "";
	static String cusDia = "";
	static String cusIng = "";
	static String cusChar = "";
	static int Cntmeatpatty = 0;
	static int Cntshrimppatty = 0;
	static int Cntchickenpatty = 0;
	static int Cntbacon = 0;

	static String id;
	static int Cnttomato = 0;
	static int Cntpickle = 0;
	static int Cntcabbage = 0;

	static int Cntbread = 0;
	static int Cntmustard = 0;
	static int Cntketchup = 0;
	static int Cntcheese = 0;
	static int cntAppear = 0;
	static int cntEasy = 2;
	static int cntNormal = 3;
	static int cntHard = 4;
	static int score = 0;
	static int check = 0;
	static int check2 = 1;
	static int check3 = 1;
	static int bcheck = 0;
	static int endcheck = 1;
	static int level = 0;
	static String cond = "";
	static String[] easy;
	static String[] normal;
	static String[] hard;
	static ArrayList<String> ans = new ArrayList<>();

	static JFrame jframe = new JFrame();
	static Timer timer = new Timer();

	public static void main(String[] args) {
		musicCTRL mp3 = new musicCTRL();
		while (true) {
			
			mp3.playstop("mainbgm");
			startLogo();
			User();
			mp3.playstop("프롤로그");
			story();
			System.out.print("\t\t\t점장님! Tutorial을 볼까요? Y/N  ");
			escapeN(5);
			String tuto = sc.next();
			if (tuto.equals("Y") || tuto.equals("y")) {
				tutorial();
			}

			selectLevel();
			loading();
			startTimeGui(10);
			mp3.stop();
			musicCTRL mp4 = new musicCTRL();
			mp4.playstop("게임진행");
			play(mp3);
			mp4.stop();

			mp3.playstop("엔딩");

			System.out.println("\t\t\t게임이 끝났습니다. 정산결과 당신의 점수는 " + score + "점 입니다.");

			ranking();
			System.out.print("\t\t\t게임을 계속 하시겠습니까? Y/N");
			String choice = sc.next();
			mp3.stop();
			if (choice.equals("y") || choice.equals("Y")) {
				Cntmeatpatty = 0;
				Cntshrimppatty = 0;
				Cntchickenpatty = 0;
				Cntbacon = 0;
				Cnttomato = 0;
				Cntpickle = 0;
				Cntcabbage = 0;
				Cntbread = 0;
				Cntmustard = 0;
				Cntketchup = 0;
				Cntcheese = 0;
				cntEasy = 2;
				cntNormal = 3;
				cntHard = 4;

				score = 10000;
				check = 0;
				check2 = 1;
				check3 = 1;
				bcheck = 0;
				continue;
			} else {
				try {
					System.out.println("\t\t\t게임을 종료합니다.");
					System.out.print("\t\t\t3..");
					Thread.sleep(1000);
					System.out.print("2..");
					Thread.sleep(1000);
					System.out.print("1..");
					Thread.sleep(500);
					System.exit(0);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	// User 회원가입, 로그인, 탈퇴, 종료 등 시작 메소드
	public static void User() {
		while (true) {
			System.out.print("\t\t\t\t[1]회원가입 [2]로그인 [3]탈퇴 [4]랭킹확인 [5]종료 >> ");
			int enu = sc.nextInt();

			if (enu == 1) {
				// 회원가입 기능 연결
				System.out.println("\t\t\t\t==========회원등록==========");
				System.out.print("\t\t\t\tID : ");
				id = sc.next();
				System.out.print("\t\t\t\tPW : ");
				String pw = sc.next();
				System.out.print("\t\t\t\t닉네임(띄어쓰기 금지) : ");
				String nick = sc.next();

				int cnt = dao.join(id, pw, nick);
				if (cnt > 0) {
					System.out.println("\t\t\t\t회원등록 성공!");
				} else {
					System.out.println("\t\t\t\t등록 실패..");
				}
				System.out.println("\t\t\t\t=========================");

			} else if (enu == 2) {
				// 로그인 기능 연결
				System.out.println("\t\t\t\t==========로그인==========");
				System.out.print("\t\t\t\tID : ");
				id = sc.next();
				System.out.print("\t\t\t\tPW : ");
				String pw = sc.next();
				int cnt = dao.login(id, pw);
				if (cnt == 1) {
					escapeN(30);
					System.out.println("\t\t\t\t로그인 성공!\n");

					break;
				} else if (cnt == 0) {
					System.out.println("\t\t\t\t비밀번호가 틀렸습니다.");
				} else if (cnt == 2) {
					System.out.println("\t\t\t\tID가 틀렸습니다..");
				} else if (cnt == -1) {
					System.out.println("\t\t\t\t계정이 없습니다.");
				}

				System.out.println("\t\t\t\t=========================");

			} else if (enu == 3) {
				// 탈퇴 기능 연결
				System.out.println("\t\t\t\t==========회원조회==========");
				System.out.print("\t\t\t\tID : ");
				String id = sc.next();
				dao.delete(id);
				System.out.println("\t\t\t\t" + id + " 유저가 탈퇴되었습니다.");
				System.out.println("\t\t\t\t=========================");
			} else if (enu == 4) {
				rankView();
			} else if (enu == 5) {
				try {
					System.out.println("\t\t\t게임을 종료합니다.");
					System.out.print("\t\t\t3..");

					Thread.sleep(1000);

					System.out.print("2..");
					Thread.sleep(1000);
					System.out.print("1..");
					Thread.sleep(500);
					System.exit(0);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("\t\t\t\t잘못 입력하셨습니다.");
			}
		}
	}

	public static void story() {
		System.out.println(

				"\t\t\t ######  ########  #######  ########  ##    ## \n"
						+ "\t\t\t##    ##    ##    ##     ## ##     ##  ##  ##  \n"
						+ "\t\t\t##          ##    ##     ## ##     ##   ####   \n"
						+ "\t\t\t ######     ##    ##     ## ########     ##    \n"
						+ "\t\t\t      ##    ##    ##     ## ##   ##      ##    \n"
						+ "\t\t\t##    ##    ##    ##     ## ##    ##     ##    \n"
						+ "\t\t\t ######     ##     #######  ##     ##    ##    \n" + "\n\n\n");
		slowPrint("\t\t\tAI가 세상을 지배한지 벌써 20년이나 지났다.. 인류 절반이 타노스 당했고 AI의 인간통제와\n");
		slowPrint("\t\t\t문명말살로 인해 인간의 컴퓨터는 도스창을 사용할 수 밖에 없는 세상이 왔다.\n");
		slowPrint("\t\t\t수 많은 인간의 음식들이 사라졌고 유일하게 남은 요식업 기업이 바로 BURGER KING...\n");
		slowPrint("\t\t\t생존한 인간들은 외식을 할때면 BURGER KING의 버거만 먹어야 하는데...\n");
		slowPrint("\t\t\t항상 똑같은 메뉴에 질린 인간들은 분노하여 BURGER KING에 올때면 자기들 마음대로 레시피를 부르곤 한다.\n");
		slowPrint("\t\t\t(여기가 무슨 써브웨인줄 아나..)\n");
		slowPrint("\t\t\t그러다 갑자기 뼈저리게 가난한 우리의 주인공 \"너님\"은 우연히 비어있는 BURGER KING 점포를 발견하고\n");
		slowPrint("\t\t\t번뜩이는 아이디어를 떠올리며 들어가게 되는데.................\n\n");
		System.out.println("\t\t\tEnter키를 눌러 진행하세요.");
		String enter = sc.nextLine();
		String enter2 = sc.nextLine();

		escapeN(8);

	}

	public static void tutorial() {
		try {
			slowPrint("\t\t\t안녕하세요! 점장님! 당신은 이 버려진 2042년의 광주 동구에 있는 BURGER KING을 살리셨어요!!\n");
			Thread.sleep(500);
			slowPrint("\t\t\t저는 이전에 여기서 서빙로봇으로 일했던\n" + "\t\t\t  ^    ^    ^    ^       ^    ^    ^    ^  \n"
					+ "\t\t\t /Y|  /O|  /N|  /G|     /H|  /Y|  /U|  /N| \n"
					+ "\t\t\t<___><___><___><___>   <___><___><___><___>" + "이에요!!\n");
			Thread.sleep(500);
			slowPrint("\t\t\t지금부터 점장님께서 BURGER KING을 운영하면서 지켜야할 규칙들을 설명드릴게요!!^^7\n\n");
			Thread.sleep(500);
			slowPrint("\t\t\t[1]재료구매 [2]주방 및 제작 [3]버거 판매 [4]재료 재고파악\n\n");
			slowPrint("\t\t\t위에 보시는 것처럼 장사를 시작하면 숫자 버튼들이 보입니다! \n");
			Thread.sleep(500);
			slowPrint("\t\t\t숫자 버튼들을 입력하면 해당 업무를 처리하실 수 있어요! 차근차근 살펴볼까요??\n\n\n");
			Thread.sleep(500);
			slowPrint("\t\t\t[1]재료구매\n");
			slowPrint("\t\t\t[1]육류 [2]채소류 [3]기타류 [0]처음화면으로 이동\n\n");
			Thread.sleep(500);
			slowPrint("\t\t\t\"[1]재료구매\"를 누르시면 \"육류\", \"채소류\", \"기타류\"를 구매할 수 있습니다!\n");
			Thread.sleep(500);
			slowPrint("\t\t\t각 재료들의 가격을 잘~ 보시고 재료를 구매하세요!\n\n\n");
			Thread.sleep(500);
			slowPrint("\t\t\t[2]주방 및 제작\n");
			slowPrint("\t\t\t[1]육류 [2]채소류 [3]기타류 [4]버거 완성 [5]재료 버리기 [0]처음화면으로 이동\n");
			Thread.sleep(500);
			slowPrint("\t\t\t\"[2]주방 및 제작\" 에서는 구매한 재료를 이용하여 버거를 만들 수 있습니다.\n");
			Thread.sleep(500);
			slowPrint("\t\t\t\"[4]버거 완성\" 버튼을 눌러 직접만든 버거를 확인하세요!\n");
			Thread.sleep(500);
			slowPrint("\t\t\t이런! 버거를 잘못 만드셨다구요?? 그럼 \"[5]재료 버리기\" 버튼을 눌러 직접만든 버거를 버려보세요!\n");
			slowPrint("\t\t\t설마! 선생님에게 다른 메뉴를 주시려는건 아니겟죠??^^\n\n\n");
			Thread.sleep(500);
			slowPrint("\t\t\t[1]재료구매 [2]주방 및 제작 [3]버거 판매 [4]재료 재고파악\n");
			Thread.sleep(500);
			slowPrint("\t\t\t버거를 다 만들었으면 \"[3]버거판매\"를 통해 선생님에게 맛있는 버거를 주세요!\n\n");
			Thread.sleep(500);
			slowPrint("\t\t\t어머! 점장님! 버거를 잘못 판매하셨다구요? 걱정마세요 ^^ 점장님 지갑에서 뺄께요!^^\n\n");
			Thread.sleep(500);
			slowPrint("\t\t\t\"[4]재고파악\" 버튼을 통해 남은 버거재료를 확인할 수 있습니다!\n\n\n");
			Thread.sleep(500);
			slowPrint("\t\t\t어맛! 말을 많이해서 배터리가 다 되었어요! 저는 이만 충전하러 가볼게요! 점장님 화이팅!!☆^^☆\n\n");

			System.out.println("\t\t\tEnter키를 눌러 진행하세요.");
			String enter = sc.nextLine();
			String enter2 = sc.nextLine();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void selectLevel() {

		System.out.print("\t\t\t난이도를 선택하세요! >> ");
		System.out.print("[1]Easy  [2]Normal  [3]Hard   ");
		escapeN(2);
		int num = sc.nextInt();
		switch (num) {
		case 1: {
			level = 1;
			System.out.println("\t\t\tEasy 난이도를 선택하셨습니다. 곧 게임을 시작하겠습니다.");

			break;
		}
		case 2: {
			level = 2;
			System.out.println("\t\t\tNormal 난이도를 선택하셨습니다. 곧 게임을 시작하겠습니다.");

			break;
		}
		case 3: {
			level = 3;
			System.out.println("\t\t\tHard 난이도를 선택하셨습니다. 곧 게임을 시작하겠습니다.");

			break;
		}
		default:
			break;
		}
		Font font = new Font("맑은 고딕", Font.PLAIN, 20);
		cond = condition();
		escapeN(3);
		System.out.println("\t\t\t★오늘의 뉴스!★  :  " + cond);
		System.out.println("\t\t\t★오늘의 뉴스!★  :  " + cond);
		System.out.println("\t\t\t★오늘의 뉴스!★  :  " + cond);
		System.out.println("\t\t\t★오늘의 뉴스!★  :  " + cond);
		System.out.println("\t\t\t★오늘의 뉴스!★  :  " + cond);
		System.out.println("\n\n\n\n");

	}

	public static void startLogo() {
		try {
			System.out.println();
			Thread.sleep(2000);
			System.out.print(
					"\t                                                                                                    \n");
			Thread.sleep(50);
			System.out.print(
					"\t                                                                                                    \n");
			Thread.sleep(50);
			System.out.print(
					"\t                                          WWWWWWWWWWWWWWWW                                          \n");
			Thread.sleep(50);
			System.out.print(
					"\t                                  WWNXXKK00OOOkkkkkkkOOOO00KKXXNWW                                  \n");
			Thread.sleep(50);
			System.out.print(
					"\t                              WNXK0OkxxxxxxxxxxxxxxxxxxxxxxxxxxkO0KXNW                              \n");
			Thread.sleep(50);
			System.out.print(
					"\t                           WNKOkxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxk0KNW                           \n");
			Thread.sleep(50);
			System.out.print(
					"\t                         WX0kxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxk0XW                         \n");
			Thread.sleep(50);
			System.out.print(
					"\t                       WXOxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxk0XW                       \n");
			Thread.sleep(50);
			System.out.print(
					"\t                      WKkxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxkKW                      \n");
			Thread.sleep(50);
			System.out.print(
					"\t                     WKkxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxkKW                     \n");
			Thread.sleep(50);
			System.out.print(
					"\t                     NOxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxON                     \n");
			Thread.sleep(50);
			System.out.print(
					"\t                     WX000000000000000000000000000000000000000000000000000000KW                     \n");
			Thread.sleep(50);
			System.out.print(
					"\t                     WXOOkO0XWWXO0XWX0OXNXOOOO0XWW WNX0OkO0XWN0OOOO0XN0OOOOKNW                      \n");
			Thread.sleep(50);
			System.out.print(
					"\t                     Ko;;::;cOKd;;xXx;;d0o;;;;;cxXNKdc;;;;;o0k:;;;;:xx:;;;;:oKW                     \n");
			Thread.sleep(50);
			System.out.print(
					"\t                     0l,:do;;d0o,;dKd;,lkl,;od:,cxko;;cddookKx;,:lokKx;,cdc,;xN                     \n");
			Thread.sleep(50);
			System.out.print(
					"\t                     0l,;c:;;x0o,;dKd;,lkl,;::;:xkoc,:xxc:coOd;,;;:dKx;,;c;;lKW                     \n");
			Thread.sleep(50);
			System.out.print(
					"\t                     0l,:xd:,cxo,,lkl;,okl,;c:,:xOkl,;ldl:,;dd;,:ldkKx;,:c;;oKW                     \n");
			Thread.sleep(50);
			System.out.print(
					"\t                     Kl,;::;;o0kc;;;;;ck0o;;xkc,:xXOo;;;:;;o0x;,;;;cxd;,oko;;oX                     \n");
			Thread.sleep(50);
			System.out.print(
					"\t                     W0xddxk0NWN0xdxxOKNXkxkXN0xx0XNX0kxxxkKNXOkkkxk00xdOXKkk0N                     \n");
			Thread.sleep(50);
			System.out.print(
					"\t                     WOoccd0WWXxlclkXW0occoONW0dccloONWW0occo0W WN0xolccclox0N                      \n");
			Thread.sleep(50);
			System.out.print(
					"\t                     Kl,,,;dX0l;,,,cOKo,,,,c0Xd;,,,,:xNNd;,,,oXW0o:,,,,,,,,,c0W                     \n");
			Thread.sleep(50);
			System.out.print(
					"\t                     0l,,,,coc,,,;:xXKl,,,,cOXo,,,,,,:xKo,,,,lK0l,,,;cdxxdllxX                      \n");
			Thread.sleep(50);
			System.out.print(
					"\t                     0l,,,,,,,,,:o0WWKl,,,,cOXo,,,,,,,:lc,,,,l0d;,,;o00kxddx0N                      \n");
			Thread.sleep(50);
			System.out.print(
					"\t                     0l,,,,,,,,,:xXW Kl,,,,cOXo,,,,,;;,,,,,,,lOo;,,;x0o,,,,,:xN                     \n");
			Thread.sleep(50);
			System.out.print(
					"\t                     0l,,,,:c;,,,;o0NKl,,,,cOXo,,,,,lxl;,,,,,l0x;,,,ckkdl;,,,lKW                    \n");
			Thread.sleep(50);
			System.out.print(
					"\t                     Kl,,,,oOo;,,,,:k0l,,,,cOXo,,,,,oK0l;,,,,oKKd;,,;:llc;,,;dN                     \n");
			Thread.sleep(50);
			System.out.print(
					"\t                     Xo;,,;xNXxc;,,;xKd;,,;lKNx;,,,;dNW0l;,,;dNWXkl:;,,,,,;cxX                      \n");
			Thread.sleep(50);
			System.out.print(
					"\t                     WXkxxOXW WKkxxOXWXOxdkKWWXOxxxkXW WXkxxkXW  WN0kxxdxkOXW                       \n");
			Thread.sleep(50);
			System.out.print(
					"\t                     WXKK0KKKKKKKKKKKKKK00KKKKKK0KKKKKKKKKKKKKKKKKKKKKK000KKKX                      \n");
			Thread.sleep(50);
			System.out.print(
					"\t                     NOxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxO                      \n");
			Thread.sleep(50);
			System.out.print(
					"\t                     WNOkxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxkOX                      \n");
			Thread.sleep(50);
			System.out.print(
					"\t                       WX0OkxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxkO0XN                       \n");
			Thread.sleep(50);
			System.out.print(
					"\t                         WWNXK0OOkkkxxxxxxxxxxxxxxxxxxxxxxxxxxxxkkkOO0KXNWWX                        \n");
			Thread.sleep(50);
			System.out.print(
					"\t                               WWWNNXXXXKKKKKKKKKKKKKKKKKKKKXXXXNNWWWWN                             \n");
			Thread.sleep(50);
			System.out.print(
					"\t                                                                                                    \n");
			Thread.sleep(50);
			System.out.print(
					"\t                                                                                                    \n");
			Thread.sleep(50);
			System.out.println(
					"\t                                                                                                      ");
			System.out.println("\t\t\t    ____  __  ______  ________________  __ __ _____   ________\r\n"
					+ "\t\t\t   / __ )/ / / / __ \\/ ____/ ____/ __ \\/ //_//  _/ | / / ____/\r\n"
					+ "\t\t\t  / __  / / / / /_/ / / __/ __/ / /_/ / ,<   / //  |/ / / __  \r\n"
					+ "\t\t\t / /_/ / /_/ / _, _/ /_/ / /___/ _, _/ /| |_/ // /|  / /_/ /  \r\n"
					+ "\t\t\t/_____/\\____/_/ |_|\\____/_____/_/ |_/_/ |_/___/_/ |_/\\____/\n\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void escapeN(int n) {
		for (int i = 0; i < n; i++) {
			System.out.println();
		}
	}

	public static void play(musicCTRL mp3) {

		escapeN(30);
		endcheck = 1;
		while (endcheck == 1) {

			if (check == 0) {

				cusAppear(level, mp3);
				check = 1;
			}

			System.out.println("\t\t\t======================= Menu ========================");
			System.out.println("\t\t\t[1]재료구매 [2]주방 및 제작 [3]버거 판매 [4]재료 재고파악");
			System.out.print("\t\t\t<선택> : ");
			int choice = sc.nextInt();
			escapeN(15);

			if (choice == 1) {
				BuyIngredient(mp3);
			} else if (choice == 2) {
				MakeHamburger(mp3);
			} else if (choice == 3) {
				if (ans.size() == 0) {
					escapeN(35);
					System.out.println("\t\t\t버거가 준비되지 않았습니다!");
				} else
					SellHamburger();
			} else if (choice == 4) {
				CheckIngredient();
			} else {
				System.out.println("\t\t\t잘못 입력 하셨습니다. 재 입력 해주세요.");
				escapeN(2);
			}

		}

	}

	public static void loading() {
		try {
			escapeN(3);
			Thread.sleep(1000);
			System.out.print("\t\t문여는 중");
			Thread.sleep(500);
			System.out.print("-----");
			Thread.sleep(300);
			System.out.print("-----");
			Thread.sleep(100);
			System.out.print("테이블 닦는중");
			Thread.sleep(300);
			System.out.print("-----");
			Thread.sleep(200);
			System.out.print("-----");
			Thread.sleep(200);
			System.out.print("불판 달구는중");
			Thread.sleep(500);
			System.out.print("-----");
			Thread.sleep(500);
			System.out.print("-----");
			Thread.sleep(200);
			System.out.print("냉장고 정리중");
			Thread.sleep(300);
			System.out.print("-----");
			Thread.sleep(200);
			System.out.println("장사 준비 완료!!");
			escapeN(5);
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void BuyIngredient(musicCTRL mp3) {
		endcheck = 1;
		while (endcheck == 1) {

			if (score <= 0) {
				endcheck = 0;
				bcheck = 1;
			}

			cusAppear(level, mp3);
			System.out.println("\t\t\t[1]육류 [2]채소류 [3]기타류 [0]처음화면으로 이동");
			System.out.print("\t\t\t<선택> : ");
			int num1 = sc.nextInt();
			escapeN(15);
			if ((num1 == 1 && bcheck == 0)) {

				cusAppear(level, mp3);
				System.out.println("\t\t\t[1]고기패티-" + Cntmeatpatty + "개 [2]새우패티-" + Cntshrimppatty + "개 [3]치킨패티-"
						+ Cntchickenpatty + "개 [4]베이컨-" + Cntbacon + "개 [0]이전");
				System.out.println("\t\t\t현재 자산은 " + score + "원 입니다.");
				System.out.print("\t\t\t<선택> : ");
				int num2 = sc.nextInt();
				escapeN(15);
				if (bcheck == 0) {
					switch (num2) {
					case 1:

						escapeN(25);
						System.out.println("\t\t\t고기패티를 구매하였습니다.");
						System.out.println();
						if (cond.equals("이X박이 미쳤어요!! 광우병 발생!!(고기패티 인상)")) {
							score -= 500;
						} else if (cond.equals("러.우 전쟁 발발! 비료값 상승!(야채, 고기, 빵 균등 인상)")) {
							score -= 150;
						} else {
							score -= 40;
						}

						Cntmeatpatty++;
						System.out.println("\t\t\t현재 자산은 " + score + "원 입니다.");

						break;
					case 2:

						escapeN(25);

						System.out.println("\t\t\t새우패티를 구매하였습니다.");
						System.out.println();
						score -= 40;
						Cntshrimppatty++;
						System.out.println("\t\t\t현재 자산은 " + score + "원 입니다.");

						break;
					case 3:

						escapeN(25);

						System.out.println("\t\t\t치킨패티를 구매하였습니다.");
						System.out.println();
						if (cond.equals("러.우 전쟁 발발! 비료값 상승!(야채, 고기, 빵 균등 인상)")) {
							score -= 150;
						} else {
							score -= 40;
						}
						Cntchickenpatty++;
						System.out.println("\t\t\t현재 자산은 " + score + "원 입니다.");

						break;
					case 4:

						escapeN(25);

						System.out.println("\t\t\t베이컨를 구매하였습니다.");
						System.out.println();
						if (cond.equals("러.우 전쟁 발발! 비료값 상승!(야채, 고기, 빵 균등 인상)")) {
							score -= 150;
						} else {
							score -= 40;
						}

						Cntbacon++;
						System.out.println("\t\t\t현재 자산은 " + score + "원 입니다.");

						break;
					case 0:

						escapeN(25);

						System.out.println("\t\t\t이전으로 돌아가겠습니다.");
						System.out.println();

						continue;
					}

				}
			}

			if ((num1 == 2 && bcheck == 0)) {

				cusAppear(level, mp3);
				System.out.println(
						"\t\t\t[1]토마토-" + Cnttomato + "개 [2]피클-" + Cntpickle + "개 [3]양상추-" + Cntcabbage + "개 [0]이전");
				System.out.println("\t\t\t현재 자산은 " + score + "원 입니다.");
				System.out.print("\t\t\t<선택> : ");
				int num3 = sc.nextInt();
				escapeN(15);
				if (bcheck == 0) {
					switch (num3) {
					case 1:

						escapeN(25);

						System.out.println("\t\t\t토마토를 구매하였습니다.");
						System.out.println();
						if (cond.equals("러.우 전쟁 발발! 비료값 상승!(야채, 고기, 빵 균등 인상)")) {
							score -= 150;
						} else if (cond.equals("흰남노 북상중!(야채 인상)")) {
							score -= 200;
						} else {
							score -= 30;
						}
						Cnttomato++;
						System.out.println("\t\t\t현재 자산은 " + score + "원 입니다.");

						break;
					case 2:

						escapeN(25);

						System.out.println("\t\t\t피클을 구매하였습니다.");
						System.out.println();
						if (cond.equals("러.우 전쟁 발발! 비료값 상승!(야채, 고기, 빵 균등 인상)")) {
							score -= 150;
						} else if (cond.equals("흰남노 북상중!(야채 인상)")) {
							score -= 200;
						} else {
							score -= 30;
						}
						Cntpickle++;
						System.out.println("\t\t\t현재 자산은 " + score + "원 입니다.");

						break;
					case 3:

						escapeN(25);

						System.out.println("\t\t\t양상추를 구매하였습니다.");
						System.out.println();
						if (cond.equals("러.우 전쟁 발발! 비료값 상승!(야채, 고기, 빵 균등 인상)")) {
							score -= 150;
						} else if (cond.equals("흰남노 북상중!(야채 인상)")) {
							score -= 200;
						} else {
							score -= 30;
						}
						Cntcabbage++;
						System.out.println("\t\t\t현재 자산은 " + score + "원 입니다.");

						break;
					case 0:

						escapeN(25);

						System.out.println("\t\t\t이전으로 돌아가겠습니다.");
						System.out.println();

						continue;
					}
				}
			}

			if ((num1 == 3 && bcheck == 0)) {

				cusAppear(level, mp3);
				System.out.println("\t\t\t[1]빵-" + Cntbread + "개 [2]머스타드-" + Cntmustard + "개 [3]케찹-" + Cntketchup
						+ "개 [4]치즈-" + Cntcheese + "개 [0]이전");
				System.out.println("\t\t\t현재 자산은 " + score + "원 입니다.");
				System.out.print("\t\t\t<선택> : ");

				int num4 = sc.nextInt();

				if (bcheck == 0) {
					switch (num4) {
					case 1:

						escapeN(30);
						System.out.println("\t\t\t빵을 구매하였습니다.");
						System.out.println();
						if (cond.equals("러.우 전쟁 발발! 비료값 상승!(야채, 고기, 빵 균등 인상)")) {
							score -= 120;
						} else {
							score -= 30;
						}
						Cntbread++;
						System.out.println("\t\t\t현재 자산은 " + score + "원 입니다.");

						break;
					case 2:

						escapeN(30);
						System.out.println("\t\t\t머스타드을 구매하였습니다.");
						System.out.println();
						score -= 20;
						Cntmustard++;
						System.out.println("\t\t\t현재 자산은 " + score + "원 입니다.");

						break;
					case 3:

						escapeN(30);
						System.out.println("\t\t\t케찹을 구매하였습니다.");
						System.out.println();
						if (cond.equals("러.우 전쟁 발발! 비료값 상승!(야채, 고기, 빵 균등 인상)")) {
							score -= 120;
						} else if (cond.equals("흰남노 북상중!(야채 인상)")) {
							score -= 180;
						} else {
							score -= 20;
						}
						Cntketchup++;
						System.out.println("\t\t\t현재 자산은 " + score + "원 입니다.");

						break;
					case 4:

						escapeN(30);
						System.out.println("\t\t\t치즈를 구매하였습니다.");
						System.out.println();
						if (cond.equals("러.우 전쟁 발발! 비료값 상승!(야채, 고기, 빵 균등 인상)")) {
							score -= 120;
						} else {
							score -= 20;
						}
						Cntcheese++;
						System.out.println("\t\t\t현재 자산은 " + score + "원 입니다.");

						break;
					case 0:

						escapeN(30);
						System.out.println("\t\t\t이전으로 돌아가겠습니다.");
						System.out.println();

						continue;
					}

				}
			}

			if ((num1 == 0 && bcheck == 0)) {

				escapeN(25);
				System.out.println("\t\t\t처음화면으로 이동합니다.");
				System.out.println();
				escapeN(2);
				break;
			}

		}

	}

	public static void MakeHamburger(musicCTRL mp3) {
		endcheck = 1;
		while (endcheck == 1) {

			if (score <= 0) {
				endcheck = 0;
				bcheck = 1;
			}

			cusAppear(level, mp3);
			System.out.println("\t\t\t버거에 들어갈 재료를 골라주세요.");
			System.out.println("\t\t\t[1]육류 [2]채소류 [3]기타류 [4]버거 완성 [5]재료 버리기 [0]처음화면으로 이동");
			System.out.print("\t\t\t<선택> : ");

			int num1 = sc.nextInt();
			escapeN(15);
			if ((num1 == 1 && bcheck == 0)) {

				escapeN(10);
				cusAppear(level, mp3);
				System.out.println("\t\t\t[1]고기패티(" + Cntmeatpatty + ") [2]새우패티(" + Cntshrimppatty + ") [3]치킨패티("
						+ Cntchickenpatty + ") [4]베이컨(" + Cntbacon + ") [0]이전");
				System.out.print("\t\t\t<선택> : ");

				int num2 = sc.nextInt();
				escapeN(15);
				if (bcheck == 0) {
					switch (num2) {
					case 1:

						if (Cntmeatpatty <= 0) {
							escapeN(10);
							System.out.println("\t\t\t고기패티의 재고가 부족합니다.");
							System.out.println();
							escapeN(2);
						} else {
							escapeN(10);
							System.out.println("\t\t\t고기패티를 추가했습니다.");
							System.out.println();
							escapeN(2);
							ans.add("고기패티");
							Cntmeatpatty--;
						}
						break;
					case 2:

						if (Cntshrimppatty <= 0) {
							escapeN(10);
							System.out.println("\t\t\t새우패티의 재고가 부족합니다.");
							System.out.println();
							escapeN(2);
						} else {
							escapeN(10);
							System.out.println("\t\t\t새우패티를 추가했습니다.");
							System.out.println();
							escapeN(2);
							ans.add("새우패티");
							Cntshrimppatty--;
						}
						break;
					case 3:

						if (Cntchickenpatty <= 0) {
							escapeN(10);
							System.out.println("\t\t\t치킨패티의 재고가 부족합니다.");
							System.out.println();
							escapeN(2);
						} else {
							escapeN(10);
							System.out.println("\t\t\t치킨패티를 추가했습니다.");
							System.out.println();
							escapeN(2);
							ans.add("치킨패티");

							Cntchickenpatty--;
						}
						break;
					case 4:

						if (Cntbacon <= 0) {
							escapeN(10);
							System.out.println("\t\t\t베이컨의 재고가 부족합니다.");
							System.out.println();
							escapeN(2);
						} else {
							escapeN(10);
							System.out.println("\t\t\t베이컨를 추가했습니다.");
							System.out.println();
							escapeN(2);
							ans.add("베이컨");

							Cntbacon--;
						}
						break;
					case 0:

						escapeN(10);
						System.out.println("\t\t\t이전으로 돌아가겠습니다.");
						System.out.println();
						escapeN(2);
						continue;
					}

				}
			}

			if ((num1 == 2 && bcheck == 0)) {

				cusAppear(level, mp3);
				System.out.println(
						"\t\t\t[1]토마토(" + Cnttomato + ") [2]피클(" + Cntpickle + ") [3]양상추(" + Cntcabbage + ") [0]이전");
				System.out.print("\t\t\t<선택> : ");

				int num3 = sc.nextInt();
				escapeN(15);
				if (bcheck == 0) {
					switch (num3) {
					case 1:

						if (Cnttomato <= 0) {
							escapeN(10);
							System.out.println("\t\t\t토마토의 재고가 부족합니다.");
							System.out.println();
							escapeN(2);
						} else {
							escapeN(10);
							System.out.println("\t\t\t토마토를 추가했습니다.");
							System.out.println();
							ans.add("토마토");
							Cnttomato--;
							escapeN(2);
						}
						break;
					case 2:

						if (Cntpickle <= 0) {
							escapeN(10);
							System.out.println("\t\t\t피클의 재고가 부족합니다.");
							System.out.println();
							escapeN(2);
						} else {
							escapeN(10);
							System.out.println("\t\t\t피클을 추가했습니다.");
							System.out.println();
							escapeN(2);
							ans.add("피클");
							Cntpickle--;
						}
						break;
					case 3:

						if (Cntcabbage <= 0) {
							escapeN(10);
							System.out.println("\t\t\t양상추의 재고가 부족합니다.");
							System.out.println();
							escapeN(2);
						} else {
							escapeN(10);
							System.out.println("\t\t\t양상추를 추가했습니다.");
							System.out.println();
							escapeN(2);
							ans.add("양상추");

							Cntcabbage--;
						}
						break;
					case 0:

						escapeN(2);
						System.out.println("\t\t\t이전으로 돌아가겠습니다.");
						System.out.println();
						escapeN(2);
						continue;
					}
				}
			}

			if ((num1 == 3 && bcheck == 0)) {

				cusAppear(level, mp3);
				System.out.println("\t\t\t[1]빵(" + Cntbread + ") [2]머스타드(" + Cntmustard + ") [3]케찹(" + Cntketchup
						+ ") [4]치즈(" + Cntcheese + ") [0]이전");
				System.out.print("\t\t\t<선택> : ");

				int num4 = sc.nextInt();
				escapeN(15);
				if (bcheck == 0) {
					switch (num4) {
					case 1:

						if (Cntbread <= 0) {
							escapeN(2);
							System.out.println("\t\t\t빵의 재고가 부족합니다.");
							System.out.println();
							escapeN(2);
						} else {
							escapeN(2);
							System.out.println("\t\t\t빵을 추가했습니다.");
							System.out.println();
							ans.add("빵");
							Cntbread--;
							escapeN(2);
						}
						break;
					case 2:

						if (Cntmustard <= 0) {
							escapeN(2);
							System.out.println("\t\t\t머스타드의 재고가 부족합니다.");
							System.out.println();
							escapeN(2);
						} else {
							escapeN(2);
							System.out.println("\t\t\t머스타드를 추가했습니다.");
							System.out.println();
							escapeN(2);
							ans.add("머스타드");

							Cntmustard--;
						}
						break;
					case 3:

						if (Cntketchup <= 0) {
							escapeN(2);
							System.out.println("\t\t\t케찹이 부족합니다.");
							System.out.println();
							escapeN(2);
						} else {
							escapeN(2);
							System.out.println("\t\t\t케찹을 추가했습니다.");
							System.out.println();
							escapeN(2);
							ans.add("케찹");

							Cntketchup--;
						}
						break;
					case 4:

						if (Cntcheese <= 0) {
							escapeN(2);
							System.out.println("\t\t\t치즈의 재고가 부족합니다.");
							System.out.println();
							escapeN(2);
						} else {
							escapeN(2);
							System.out.println("\t\t\t치즈를 추가했습니다.");
							System.out.println();
							escapeN(2);
							ans.add("치즈");

							Cntcheese--;
						}
						break;
					case 0:
						escapeN(2);
						System.out.println("\t\t\t이전으로 돌아가겠습니다.");
						System.out.println();
						escapeN(2);
						continue;
					}

				}
			}
			if ((num1 == 4 && bcheck == 0)) {
				escapeN(2);
				System.out.println("\t\t\t버거를 완성하였습니다.");
				System.out.print("\t\t\t[");
				for (int i = 0; i < ans.size(); i++) {
					System.out.print(ans.get(i) + " ");
				}
				System.out.println("]");
				escapeN(2);

				break;
			}
			if ((num1 == 5 && bcheck == 0)) {

				escapeN(2);
				if (ans.size() == 0) {
					System.out.println("\t\t\t비어있습니다.");
				} else {
					System.out.println("\t\t\t모든 재료를 버렸습니다.");
					ans.clear();
				}

				escapeN(2);
			}
			if ((num1 == 0 && bcheck == 0)) {

				escapeN(2);
				System.out.println("\t\t\t처음화면으로 이동합니다.");
				System.out.println();
				escapeN(2);
				break;
			}

		}
	}

	public static void SellHamburger() {

		int cntlength = 0;

		if (score <= 0) {
			endcheck = 0;
			bcheck = 1;
		}
		if (level == 1) {
			if (ans.size() == easy.length) {
				for (int j = 0; j < easy.length; j++) {

					if (ans.get(j) != easy[j]) {

						escapeN(2);
						System.out.println("\t\t\t버거 판매에 실패하였습니다.");
						score -= 200;
						System.out.println("\t\t\t현재 자산은 " + score + "원 입니다.");

						check = 0;
						ans.clear();

						break;
					} else {
						++cntlength;
					}
				}

				if (ans.size() == 3) {
					if (cntlength == easy.length) {

						escapeN(2);
						System.out.println("\t\t\t버거 판매에 성공하였습니다.");
						score += 300;
						System.out.println("\t\t\t현재 자산은 " + score + "원 입니다.");

						check = 0;
						ans.clear();

					}
				}
				if (ans.size() == 4) {
					if (cntlength == easy.length) {

						escapeN(2);
						System.out.println("\t\t\t버거 판매에 성공하였습니다.");
						score += 400;
						System.out.println("\t\t\t현재 자산은 " + score + "원 입니다.");

						check = 0;
						ans.clear();

					}
				}
				if (ans.size() == 5) {
					if (cntlength == easy.length) {

						escapeN(2);
						System.out.println("\t\t\t버거 판매에 성공하였습니다.");
						score += 500;
						System.out.println("\t\t\t현재 자산은 " + score + "원 입니다.");

						check = 0;
						ans.clear();

					}
				}
			} else {
				System.out.println("\t\t\t버거가 완성되지 않았습니다!");
			}

		} else if (level == 2) {
			if (ans.size() == normal.length) {
				for (int j = 0; j < normal.length; j++) {
					if (ans.get(j) != normal[j]) {

						escapeN(2);
						System.out.println("\t\t\t버거 판매에 실패하였습니다.");
						score -= 400;
						System.out.println("\t\t\t현재 자산은 " + score + "원 입니다.");

						check = 0;
						ans.clear();
						escapeN(2);
						break;
					} else {
						++cntlength;
					}
				}
				if (ans.size() == 5) {
					if (cntlength == normal.length) {

						escapeN(2);
						System.out.println("\t\t\t버거 판매에 성공하였습니다.");
						score += 750;
						System.out.println("\t\t\t현재 자산은 " + score + "원 입니다.");

						check = 0;
						ans.clear();
						escapeN(2);
					}
				}
				if (ans.size() == 6) {
					if (cntlength == normal.length) {

						escapeN(2);
						System.out.println("\t\t\t버거 판매에 성공하였습니다.");
						score += 900;
						System.out.println("\t\t\t현재 자산은 " + score + "원 입니다.");

						check = 0;
						ans.clear();
						escapeN(2);
					}
				}
				if (ans.size() == 7) {
					if (cntlength == normal.length) {

						escapeN(2);
						System.out.println("\t\t\t버거 판매에 성공하였습니다.");
						score += 1050;
						System.out.println("\t\t\t현재 자산은 " + score + "원 입니다.");

						check = 0;
						ans.clear();
						escapeN(2);
					}
				}
			} else {
				System.out.println("\t\t\t버거가 완성되지 않았습니다!");
			}
		} else if (level == 3) {
			if (ans.size() == hard.length) {
				for (int j = 0; j < hard.length; j++) {
					if (ans.get(j) != hard[j]) {
						check2 = 0;

						escapeN(2);
						System.out.println("\t\t\t버거 판매에 실패하였습니다.");
						score -= 700;
						System.out.println("\t\t\t현재 자산은 " + score + "원 입니다.");

						check = 0;
						ans.clear();
						escapeN(2);
						break;
					} else {
						++cntlength;
					}
				}
				if (ans.size() == 7) {
					if (cntlength == hard.length) {
						check2 = 0;

						escapeN(2);
						System.out.println("\t\t\t버거 판매에 성공하였습니다.");
						score += 1400;
						System.out.println("\t\t\t현재 자산은 " + score + "원 입니다.");

						check = 0;
						ans.clear();
						escapeN(2);
					}
				}
				if (ans.size() == 8) {
					if (cntlength == hard.length) {
						check2 = 0;

						escapeN(2);
						System.out.println("\t\t\t버거 판매에 성공하였습니다.");
						score += 1600;
						System.out.println("\t\t\t현재 자산은 " + score + "원 입니다.");

						check = 0;
						ans.clear();
						escapeN(2);
					}
				}
				if (ans.size() == 9) {
					if (cntlength == hard.length) {
						check2 = 0;

						escapeN(2);
						System.out.println("\t\t\t버거 판매에 성공하였습니다.");
						score += 1800;
						System.out.println("\t\t\t현재 자산은 " + score + "원 입니다.");

						check = 0;
						ans.clear();

						escapeN(2);
					}
				}
			} else {
				System.out.println("\t\t\t버거가 완성되지 않았습니다!");
			}
		}

	}

	public static void CheckIngredient() {
		escapeN(30);
		System.out.println("\t\t\t<육류> --[1]고기패티 : " + Cntmeatpatty + "개, [2]새우패티 : " + Cntshrimppatty + "개, [3]치킨패티 : "
				+ Cntchickenpatty + "개, [4]베이컨 : " + Cntbacon + "개");
		System.out.println(
				"\t\t\t<야채류>--[1]토마토 : " + Cnttomato + "개, [2]피클 : " + Cntpickle + "개, [3]양상추 : " + Cntcabbage + "개");
		System.out.println("\t\t\t<기타류>--[1]빵 : " + Cntbread + "개, [2]머스타드 : " + Cntmustard + "개, [3]케찹 : " + Cntketchup
				+ "개, [4]치즈 : " + Cntcheese + "개");
		escapeN(3);
	}

	public static void cusAppear(int lev, musicCTRL mp3) {

		customerCTRL cus = new customerCTRL();
		customerCharCTRL ccv = new customerCharCTRL();
		if (check == 0 || check3 == 0) {
			check3 = 1;
			mp3.custbgm();
			cusName = cus.customer();
			cusDia = cus.dialogue();
			cusChar = ccv.customerchar();
			if (lev == 1) {
				easy = ac.Easy();
				cusIng = Arrays.toString(easy);
			} else if (lev == 2) {
				normal = ac.Normal();
				cusIng = Arrays.toString(normal);
			} else if (lev == 3) {
				hard = ac.Hard();
				cusIng = Arrays.toString(hard);
			}
//			if (easy.length == 3) {
//				cusTime(20);
//			} else if (easy.length == 4) {
//				cusTime(23);
//			} else if (easy.length == 5 || normal.length == 5) {
//				cusTime(26);
//			} else if (normal.length == 6) {
//				cusTime(29);
//			} else if (normal.length == 7 || hard.length == 7) {
//				cusTime(31);
//			} else if (hard.length == 8) {
//				cusTime(34);
//			} else if (hard.length == 9) {
//				cusTime(38);
//			}

		}

		System.out.println("\t\t\t" + cusChar);
		System.out.println("\t\t\t" + cusName);
		System.out.println("\t\t\t" + cusDia);
		System.out.println("\t\t\t" + cusIng);
		escapeN(1);

	}

	public static void cusTime(int time) {
		JFrame jframe = new JFrame();
		JLabel jLabel = new JLabel();
		Timer timer = new Timer();

		jframe.setLayout(new FlowLayout());
		jframe.setBounds(1200, 100, 270, 100);

		jframe.add(jLabel);
		jframe.setVisible(true);
		Font font = new Font("맑은 고딕", Font.PLAIN, 30);
		jLabel.setFont(font);

		timer.scheduleAtFixedRate(new TimerTask() {
			int i = time;

			public void run() {
				jLabel.setText(cusName + " : " + i);
				i--;

				if (i == 0 || check2 == 0) {

					jLabel.setText("\t\t\t" + cusName + "손님이 떠났습니다.");
					try {

						Thread.sleep(100);

						System.out.println("\t\t\t" + cusName + "손님이 떠났습니다.");
						check3 = 0;
//						cusAppear(level);

					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		}, 0, 1000);

	}

	public static void startTimeGui(int count) {

		JFrame jframe = new JFrame();
		JLabel jLabel = new JLabel();
		jframe.setLayout(new FlowLayout());
		jframe.setBounds(1200, 0, 170, 100);

		jframe.add(jLabel);
		jframe.setVisible(true);
		Font font = new Font("맑은 고딕", Font.PLAIN, 20);
		jLabel.setFont(font);
		Timer timer = new Timer();

		timer.scheduleAtFixedRate(new TimerTask() {
			int i = count;

			public void run() {
				jLabel.setText("게임시간 : " + (i / 60) + ":" + (i % 60));
				i--;

				if (i < 0) {
					jLabel.setText("게임종료!!!");
					bcheck = 1;
					try {
						Thread.sleep(1500);
						endcheck = 0;

						Thread.sleep(1000);
						jframe.setVisible(false);
					} catch (InterruptedException e) {
						System.out.println("startTimeGui 오류!");
					}

				}
			}
		}, 0, 1000);
	}

	public static void ranking() {

		System.out.println("\t\t\t랭킹에 등록하겠습니다...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int r = dao.ranking(id, score);
		if (r == 1) {
			System.out.println("\t\t\t랭킹등록에 성공하였습니다!");
		} else {
			System.out.println("\t\t\t랭킹등록에 실패하였습니다..");
		}
	}

	public static void rankView() {
		System.out.println("\n\t\t\t\t랭킹\t닉네임\t점수");
		for (int i = 0; i < dao.rankView().size(); i++) {
			System.out.println("\t\t\t\t" + (i + 1) + "\t" + dao.rankView().get(i).getNickname() + "\t"
					+ dao.rankView().get(i).getScore());
		}
		System.out.println();

	}

	public static String condition() {
		String[] condition = { "흰남노 북상중!(야채 인상)", "이X박이 미쳤어요!! 광우병 발생!!(고기패티 인상)",
				"러.우 전쟁 발발! 비료값 상승!(야채, 고기, 빵 균등 인상)", "평화로움~" };
		String result = condition[rd.nextInt(4)];
		return result;
	}

	public static void slowPrint(String str) {
		try {
			for (int i = 0; i < str.length(); i++) {
				System.out.print(str.charAt(i));

				Thread.sleep(30);
			}
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
}
